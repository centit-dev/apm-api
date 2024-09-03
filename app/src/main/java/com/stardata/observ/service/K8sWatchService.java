package com.stardata.observ.service;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.stardata.observ.mapper.pg.ApplicationStructureMapper;
import com.stardata.observ.model.pg.ApplicationStructure;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.openapi.models.V1ServiceList;
import io.kubernetes.client.util.Config;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Slf4j
@Component
public class K8sWatchService {
    private final Cache<String, Map<String, String>> podLabels = CacheBuilder.newBuilder()
        .maximumSize(10_000)
        .build();
    private final Cache<String, Instant> podStartTime = CacheBuilder.newBuilder()
        .maximumSize(10_000)
        .build();
    private final Cache<String, String> podPerService = CacheBuilder.newBuilder()
        .maximumSize(10_000)
        .build();

    private final ApplicationStructureMapper applicationStructureMapper;

    public K8sWatchService(ApplicationStructureMapper applicationStructureMapper) throws IOException {
        this.applicationStructureMapper = applicationStructureMapper;

        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);

        Flux.interval(Duration.ofSeconds(5))
            .doOnNext(v -> watchPod(client))
            .retry()
            .subscribe();
        Flux.interval(Duration.ofSeconds(5))
            .doOnNext(v -> watchService(client))
            .retry()
            .subscribe();
        Flux.interval(Duration.ofSeconds(5))
            .doOnNext(v -> upsertPodByService())
            .retry()
            .subscribe();
        Flux.interval(Duration.ofMinutes(5))
            .doOnNext(v -> deleteOutdated())
            .retry()
            .subscribe();
    }

    public List<ApplicationStructure> getPods(Date from, Date to) {
        QueryWrapper<ApplicationStructure> wrapper = new QueryWrapper<>();
        wrapper.eq("level", 3);
        return applicationStructureMapper.selectList(wrapper)
            .stream()
            .collect(Collectors.toList());
    }

    @SuppressWarnings("null")
    private void watchPod(ApiClient client) {
        CoreV1Api api = new CoreV1Api();
        try {
            V1PodList pods = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null, null);
            pods.getItems().stream()
                .forEach(pod -> {
                    if (pod.getMetadata() == null) {
                        return;
                    }
                    String podName = pod.getMetadata().getName();
                    Map<String, String> labels = pod.getMetadata().getLabels();
                    if (MapUtils.isEmpty(labels)) {
                        return;
                    }
                    podLabels.put(podName, labels);
                    podLabels.put(pod.getStatus().getPodIP(), labels);
                    podStartTime.put(podName, pod.getStatus().getStartTime().toInstant());
                    podStartTime.put(pod.getStatus().getPodIP(), pod.getStatus().getStartTime().toInstant());
                });
        } catch (ApiException e) {
            log.error("watch pod error", e);
        }
    }

    @SuppressWarnings("null")
    private void watchService(ApiClient client) {
        CoreV1Api api = new CoreV1Api();
        try {
            V1ServiceList services = api.listServiceForAllNamespaces( null, null, null, null, null, null, null, null, null, null);
            services.getItems().forEach(service -> {
                if (service.getMetadata() == null) {
                    return;
                }
                String serviceName = service.getMetadata().getName();
                Map<String, String> selector = service.getSpec().getSelector();
                if (MapUtils.isEmpty(selector)) {
                    return;
                }

                List<String> matchingPods = podLabels.asMap()
                    .entrySet().stream()
                    .filter(entry -> {
                        Map<String, String> labels = entry.getValue();
                        if (MapUtils.isEmpty(labels)) {
                            return false;
                        }
                        return selector.entrySet().stream()
                            .allMatch(e -> e.getValue().equals(labels.get(e.getKey())));
                    })
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

                if (matchingPods.isEmpty()) {
                    return;
                }
                matchingPods.stream().forEach(podName -> podPerService.put(podName, serviceName));
                podPerService.put(service.getSpec().getClusterIP(), serviceName);
                podStartTime.put(service.getSpec().getClusterIP(), service.getMetadata().getCreationTimestamp().toInstant());
            });
        } catch (ApiException e) {
            log.error("watch service error", e);
        }
    }

    private void upsertPodByService() {
        Set<String> podNames = podPerService.asMap().keySet();
        QueryWrapper<ApplicationStructure> wrapper = new QueryWrapper<>();
        wrapper.select("code");
        wrapper.in("code", podNames);
        List<ApplicationStructure> saved = applicationStructureMapper.selectList(wrapper);
        Set<String> savedPodNames = saved.stream().map(ApplicationStructure::getCode).collect(Collectors.toSet());
        List<ApplicationStructure> newPods = podPerService.asMap().entrySet().stream()
            .filter(entry -> !savedPodNames.contains(entry.getKey()))
            .map(entry -> {
                Instant startTime = podStartTime.getIfPresent(entry.getKey());
                if (startTime == null) {
                    return null;
                }

                ApplicationStructure data = new ApplicationStructure();
                data.setCode(entry.getKey());
                data.setParentCode(entry.getValue());
                data.setValidDate(Date.from(startTime.plus(30, ChronoUnit.DAYS)));
                data.setLevel(3);
                return data;
            })
            .collect(Collectors.toList());
        if (!newPods.isEmpty()) {
            applicationStructureMapper.largeInsert(newPods);
        }
    }

    private void deleteOutdated() {
        QueryWrapper<ApplicationStructure> wrapper = new QueryWrapper<>();
        wrapper.eq("level", 3);
        wrapper.le("valid_date", new Date());
        applicationStructureMapper.delete(wrapper);
    }
}
