package com.stardata.observ.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.stardata.observ.common.MetricNames;
import com.stardata.observ.mapper.ch.MetricsGaugeMapper;
import com.stardata.observ.mapper.ch.MetricsSumMapper;
import com.stardata.observ.mapper.ch.SpanMapperDelegator;
import com.stardata.observ.mapper.pg.ApplicationStructureMapper;
import com.stardata.observ.model.ch.Instance;
import com.stardata.observ.model.ch.SpanMetric;
import com.stardata.observ.vo.AppRelatedResource;
import com.stardata.observ.vo.InstanceListResponse;
import com.stardata.observ.vo.InstanceRedData;
import com.stardata.observ.vo.InstanceRedResponse;
import com.stardata.observ.vo.InstanceResponse;
import com.stardata.observ.vo.NameDisplayListResponse;
import com.stardata.observ.vo.NameDisplayPair;
import com.stardata.observ.vo.TimeRangeType;
import com.stardata.observ.vo.TomcatStatus;
import com.stardata.observ.vo.TomcatStatusList;
import com.stardata.observ.vo.TomcatStatusListResponse;

import lombok.AllArgsConstructor;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static com.stardata.observ.utils.TimeWindowUtils.calculateGranularity;

@Service
@AllArgsConstructor
public class ApplicationStructureService {
    private final ApplicationStructureMapper applicationStructureMapper;
    private final MetricsSumMapper sumMapper;
    private final MetricsGaugeMapper gaugeMapper;
    private final SpanMapperDelegator spanMapper;
    private final ResourceService resourceService;

    public NameDisplayListResponse listPlatforms() {
        List<NameDisplayPair> nameDisplayPairs = applicationStructureMapper.listPlatforms();
        return new NameDisplayListResponse().data(nameDisplayPairs);
    }

    public NameDisplayListResponse listAppClusters(String platform) {
        List<NameDisplayPair> nameDisplayPairs = applicationStructureMapper.listAppClusters(platform);
        return new NameDisplayListResponse().data(nameDisplayPairs);
    }

    public InstanceListResponse listAppInstances(String platform, String cluster, Integer timeType, Long fromTime, Long toTime, Integer recentSeconds) {
        if (TimeRangeType.RECENT.getValue().equals(timeType)) {
            toTime = System.currentTimeMillis();
            fromTime = toTime - recentSeconds * 1000;
        }
        if (fromTime == null || toTime == null || fromTime > toTime) {
            throw new IllegalArgumentException(String.format("Invalid time range: from %d to %d", fromTime, toTime));
        }
        Date from = new Date(fromTime);
        Date to = new Date(toTime);
        return sumMapper.getRecentInstances(platform, cluster, fromTime, toTime)
                .stream()
                .map(instance -> new InstanceResponse()
                        .name(instance.getName())
                        .resources(buildResources(instance, from, to)))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        new InstanceListResponse()::data));
    }

    public InstanceListResponse listSoftwareInstances(String platform, String software, Integer timeType, Long fromTime, Long toTime, Integer recentSeconds) {
        if (TimeRangeType.RECENT.getValue().equals(timeType)) {
            toTime = System.currentTimeMillis();
            fromTime = toTime - recentSeconds * 1000;
        }
        if (fromTime == null || toTime == null || fromTime > toTime) {
            throw new IllegalArgumentException(String.format("Invalid time range: from %d to %d", fromTime, toTime));
        }
        Date from = new Date(fromTime);
        Date to = new Date(toTime);
        List<AppRelatedResource> resources = spanMapper.findServerSoftwaresByServerHostname(platform, software, fromTime, toTime)
                .stream()
                .filter(StringUtils::isNotBlank)
                .map(type -> resourceService.buildServerResource(software, type, from, to))
                .collect(Collectors.toList());
        InstanceResponse instance = new InstanceResponse().name(software).resources(resources);
        return new InstanceListResponse().data(Collections.singletonList(instance));
    }

    private List<AppRelatedResource> buildResources(Instance instance, Date from, Date to) {
        List<AppRelatedResource> resources = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(instance.getServiceNames())) {
            instance.getServiceNames().stream()
                    .filter(StringUtils::isNotBlank)
                    .map(serviceName -> resourceService.buildServiceResource(instance.getName(), serviceName, from, to))
                    .forEach(resources::add);
        }

        if (CollectionUtils.isNotEmpty(instance.getApplicationTypes())) {
            instance.getApplicationTypes().stream()
                    .filter(StringUtils::isNotBlank)
                    // TODO hard-code unsupported application types
                    .map(type -> {
                        if ("springcloud".equals(type) || "dubbo".equals(type)) {
                            return "java";
                        }
                        return type;
                    })
                    .limit(1)
                    .map(name -> resourceService.buildAppResource(instance.getName(), name, from, to))
                    .forEach(resources::add);
        }

        if (StringUtils.isNotBlank(instance.getName())) {
            // add k8s resource
            resources.add(resourceService.buildK8sResource(instance.getName(), from, to));
        }

        return resources;
    }

    public TomcatStatusListResponse getTomcatStatus(
            String platform, String cluster, String instanceName,
            Integer timeType, Integer recentSeconds, Long fromTime, Long toTime, Integer granularity) {
        if (TimeRangeType.RECENT.getValue().equals(timeType)) {
            toTime = System.currentTimeMillis();
            fromTime = toTime - recentSeconds * 1000;
        }
        if (fromTime == null || toTime == null || fromTime > toTime) {
            throw new IllegalArgumentException(String.format("Invalid time range: from %d to %d", fromTime, toTime));
        }
        if (granularity == null) {
            granularity = calculateGranularity(fromTime, toTime);
        }
        List<Long> requestCounts = gaugeMapper.getValues(
                platform, cluster, instanceName, MetricNames.HTTP_SERVER_TOMCAT_REQUEST_COUNT.value(),
                fromTime, toTime, granularity);
        List<Long> activeSessions = sumMapper.getValues(
                platform, cluster, instanceName, MetricNames.HTTP_SERVER_TOMCAT_SESSIONS_ACTIVE_SESSIONS.value(),
                fromTime, toTime, granularity);
        List<Long> threads = sumMapper.getValues(
                platform, cluster, instanceName, MetricNames.HTTP_SERVER_TOMCAT_THREADS.value(),
                fromTime, toTime, granularity);
        List<TomcatStatus> statuses = IntStream.range(0, requestCounts.size())
                .mapToObj(index -> {
                    TomcatStatus status = new TomcatStatus();
                    if (index <= requestCounts.size() - 1) {
                        status.requestCount(requestCounts.get(index));
                    } else {
                        status.requestCount(0L);
                    }
                    if (index <= activeSessions.size() - 1) {
                        status.activeSessions(activeSessions.get(index));
                    } else {
                        status.activeSessions(0L);
                    }
                    if (index <= threads.size() - 1) {
                        status.threads(threads.get(index));
                    } else {
                        status.threads(0L);
                    }
                    return status;
                })
                .collect(Collectors.toList());
        TomcatStatusList list = new TomcatStatusList()
                .fromTime(fromTime)
                .toTime(toTime)
                .granularity(granularity)
                .values(statuses);
        return new TomcatStatusListResponse().data(list);
    }

    public InstanceRedResponse getClusterRed(
            String platform, String cluster, String instanceName, String serviceName,
            Integer timeType, Integer recentSeconds, Long fromTime, Long toTime) {
        if (TimeRangeType.RECENT.getValue().equals(timeType)) {
            toTime = System.currentTimeMillis();
            fromTime = toTime - recentSeconds * 1000;
        }
        if (fromTime == null || toTime == null || fromTime > toTime) {
            throw new IllegalArgumentException(String.format("Invalid time range: from %d to %d", fromTime, toTime));
        }

        List<SpanMetric> metrics = spanMapper.getSpanMetrics(platform, cluster, instanceName, serviceName, fromTime, toTime);
        List<InstanceRedData> data = metrics.stream()
                .map(metric -> {
                    BigDecimal successRate = BigDecimal.ZERO;
                    if (metric.getTotalCount() > 0) {
                        successRate = BigDecimal.valueOf(metric.getSuccessCount())
                                .divide(BigDecimal.valueOf(metric.getTotalCount()), 4, RoundingMode.HALF_UP);
                    }
                    return new InstanceRedData()
                            .spanName(metric.getSpanName())
                            .calls(metric.getTotalCount())
                            .successRate(successRate)
                            .durationP90(metric.getDurationP90().longValue());
                })
                .collect(Collectors.toList());
        return new InstanceRedResponse().data(data);
    }

}
