package com.stardata.observ.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.stardata.observ.domain.ServiceMapNodeDto;
import com.stardata.observ.domain.ServiceMapNodeDto.ServiceMapEdgeDto;
import com.stardata.observ.mapper.ch.MetricsHistogramMapper;
import com.stardata.observ.mapper.ch.MetricsSumMapper;
import com.stardata.observ.model.ch.Instance;
import com.stardata.observ.model.ch.ServiceMapEdgeResult;
import com.stardata.observ.utils.DateUtil;
import com.stardata.observ.vo.ServiceMapEdge;
import com.stardata.observ.vo.ServiceMapNode;
import com.stardata.observ.vo.ServiceMapRequest;
import com.stardata.observ.vo.ServiceMapResponse;
import com.stardata.observ.vo.ServiceMapResponseData;
import com.stardata.observ.vo.TimeRange;

import lombok.AllArgsConstructor;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-03-04 21:03
 */
@Service
@AllArgsConstructor
public class ServicesMapService {
    private static final List<String> MIDDLEWARE_TYPES = Lists.newArrayList("kafka", "activemq");

    private final MetricsHistogramMapper metricsHistogramMapper;
    private final MetricsSumMapper metricsSumMapper;
    private final QueryDictionaryService queryDictionaryService;

    public ServiceMapResponse createServiceMap(ServiceMapRequest request) {
        TimeRange timeRange = DateUtil.getActualTimeRange(request.getTimeCondition());
        long fromTime = timeRange.getFromTime();
        long toTime = timeRange.getToTime();

        List<ServiceMapEdgeResult> result = metricsHistogramMapper.getServiceMapEdges(fromTime, toTime);
        result = extendEdges(result);
        Map<String, List<String>> instanceNames = metricsSumMapper
                .getRecentInstances(request.getPlatform(), null, fromTime, toTime)
                .stream()
                .collect(Collectors.groupingBy(
                        Instance::getClusterName,
                        Collectors.mapping(Instance::getName, Collectors.toList())));
        Map<String, ServiceMapNodeDto> map = buildServiceTree(result, instanceNames);
        return createServiceMap(request, map);
    }

    /**
     * create service map using all data
     */
    public ServiceMapResponse createServiceMap(List<ServiceMapEdgeResult> edgeData) {
        Map<String, ServiceMapNodeDto> map = buildServiceTree(edgeData, Collections.emptyMap());
        Map<String, ServiceMapNode> nodes = new HashMap<>();
        MultiKeyMap<String, ServiceMapEdge> edges = new MultiKeyMap<>();
        map.forEach((i, node) -> {
            // TODO hard-code app type
            String appType = node.getApplicationType();
            if ("starshop-online-auth".equals(node.getAppCluster()) || "starshop-online-customer".equals(node.getAppCluster())) {
                appType = "dubbo";
            }
            nodes.put(node.getAppCluster(), new ServiceMapNode()
                    .cluster(node.getAppCluster())
                    .type(node.getNodeType())
                    .platform(queryDictionaryService.getDisplayPair(node.getPlatform()))
                    .software(queryDictionaryService.getDisplayPair(appType))
                    .instanceCount(node.getInstanceNames().size())
                    .successCount(node.getSuccessCount())
                    .systemFaultCount(node.getSystemFaultCount())
                    .businessFaultCount(node.getBusinessFaultCount()));

            node.getEdges().forEach((k, v) -> {
                edges.put(node.getAppCluster(), k, new ServiceMapEdge()
                        .fromCluster(node.getAppCluster())
                        .toCluster(k)
                        .duration(v.getDuration() / v.getCallCount())
                        .callCount(v.getCallCount()));
            });
        });
        return new ServiceMapResponse().data(new ServiceMapResponseData()
                .nodes(new ArrayList<>(nodes.values()))
                .edges(new ArrayList<>(edges.values())));
    }

    // visible for testing
    ServiceMapResponse createServiceMap(ServiceMapRequest request, Map<String, ServiceMapNodeDto> map) {
        ServiceMapNodeDto center = map.get(request.getCluster());
        if (center == null) {
            return new ServiceMapResponse();
        }

        Map<String, ServiceMapNode> nodes = new HashMap<>();
        MultiKeyMap<String, ServiceMapEdge> edges = new MultiKeyMap<>();
        Consumer<ServiceMapNodeDto> nodeConsumer = node -> {
            // TODO hard-code app type
            String appType = node.getApplicationType();
            if ("starshop-online-auth".equals(node.getAppCluster()) || "starshop-online-customer".equals(node.getAppCluster())) {
                appType = "dubbo";
            }
            nodes.put(node.getAppCluster(), new ServiceMapNode()
                    .cluster(node.getAppCluster())
                    .type(node.getNodeType())
                    .platform(queryDictionaryService.getDisplayPair(node.getPlatform()))
                    .software(queryDictionaryService.getDisplayPair(appType))
                    .instanceCount(node.getInstanceNames().size())
                    .successCount(node.getSuccessCount())
                    .systemFaultCount(node.getSystemFaultCount())
                    .businessFaultCount(node.getBusinessFaultCount()));

            node.getEdges().forEach((k, v) -> {
                edges.put(node.getAppCluster(), k, new ServiceMapEdge()
                        .fromCluster(node.getAppCluster())
                        .toCluster(k)
                        .duration(v.getDuration())
                        .callCount(v.getCallCount())
                        .successCount(v.getSuccessCount())
                        .businessFaultCount(v.getBusinessFaultCount())
                        .systemFaultCount(v.getSystemFaultCount()));
            });
        };
        nodeConsumer.accept(center);
        getRelatives(center, ServiceMapNodeDto::getClients, request.getEntranceDepth()).forEach(nodeConsumer);
        getRelatives(center, ServiceMapNodeDto::getServers, request.getExitDepth()).forEach(nodeConsumer);

        // clean up the edges
        List<ServiceMapEdge> edgeData = edges.values().stream()
                .filter(edge -> nodes.containsKey(edge.getFromCluster()) && nodes.containsKey(edge.getToCluster()))
                .collect(Collectors.toList());

        return new ServiceMapResponse().data(new ServiceMapResponseData()
                .nodes(new ArrayList<>(nodes.values()))
                .edges(edgeData));
    }

    private List<ServiceMapNodeDto> getRelatives(
            ServiceMapNodeDto node,
            Function<ServiceMapNodeDto, Map<String, ServiceMapNodeDto>> getter,
            int depth) {
        if (depth <= 0) {
            return Collections.emptyList();
        }
        List<ServiceMapNodeDto> relatives = new ArrayList<>();
        Map<String, ServiceMapNodeDto> relativeMap = getter.apply(node);
        if (relativeMap != null) {
            relativeMap.values().forEach(relative -> {
                relatives.add(relative);
                List<ServiceMapNodeDto> remoteRelatives = getRelatives(relative, getter, depth - 1);
                relatives.addAll(remoteRelatives);
            });
        }
        return relatives;
    }

    // visible for testing
    // NOTE parallel stream is slower than sequential stream. A powerful core takes 30ms to process 10,000 edges.
    /**
     * <p>build a map with app cluster as the key and the representing node as the value.</p>
     * Each node has a map of clients that calls it and has another map of servers it calls:
     * <ul>
     * <li>clients -> node(as server)</li>
     * <li>node(as client) -> servers</li>
     * </ul>
     * Each map records the edge between the client and the server.
     *
     * @param edges the edges to build the service tree
     * @param instanceNames
     * @return a map with app cluster as key and the node as value
     */
    Map<String, ServiceMapNodeDto> buildServiceTree(List<ServiceMapEdgeResult> edges, Map<String, List<String>> instanceNames) {
        // a map with server cluster as key and clients as value
        Map<String, List<ServiceMapNodeDto>> clients = new HashMap<>();
        // a map with app cluster as key and the node as value
        Map<String, ServiceMapNodeDto> map = new HashMap<>();

        edges.forEach(edgeData -> {
            ServiceMapNodeDto node = map.computeIfAbsent(edgeData.getClientApplicationCluster(), v -> {
                ServiceMapNodeDto n = new ServiceMapNodeDto();
                n.setAppCluster(edgeData.getClientApplicationCluster());
                n.setPlatform(edgeData.getPlatformName());
                n.setInstanceNames(new HashSet<>());
                n.setClients(new HashMap<>());
                n.setServers(new HashMap<>());
                n.setEdges(new HashMap<>());
                return n;
            });
            if (StringUtils.isNotBlank(edgeData.getClientSoftware())) {
                node.setNodeType(ServiceMapNode.TypeEnum.SOFTWARE);
                node.setApplicationType(edgeData.getClientSoftware());
            } else {
                node.setNodeType(ServiceMapNode.TypeEnum.APP_CLUSTER);
                node.setApplicationType(edgeData.getClientApplicationType());
            }
            if (edgeData.getInstanceNames() != null) {
                node.getInstanceNames().addAll(edgeData.getInstanceNames());
            } else if (MapUtils.isNotEmpty(instanceNames)) {
                node.getInstanceNames().addAll(instanceNames.getOrDefault(edgeData.getClientApplicationCluster(), Collections.emptyList()));
            }

            // find clients and link clients to this node
            clients.getOrDefault(node.getAppCluster(), Collections.emptyList())
                    .forEach(client -> node.getClients().put(client.getAppCluster(), client));
            // if not found, let's do it after all edges are processed.

            // init servers
            String serverApplicationCluster = StringUtils.isNotEmpty(edgeData.getServerApplicationCluster())
                    ? edgeData.getServerApplicationCluster()
                    : String.format("%s/%s", edgeData.getServerSoftware(), edgeData.getToService());
            ServiceMapNodeDto server = map.computeIfAbsent(serverApplicationCluster, v -> {
                ServiceMapNodeDto s = new ServiceMapNodeDto();
                s.setAppCluster(serverApplicationCluster);
                s.setPlatform(edgeData.getPlatformName());
                // if there is no server, use the server software from the node side
                // if the server comes later, this value will be overridden
                // if the server has already arrived, this value will never be used
                if (StringUtils.isNotBlank(edgeData.getServerSoftware())) {
                    s.setApplicationType(edgeData.getServerSoftware());
                    s.setNodeType(ServiceMapNode.TypeEnum.SOFTWARE);
                } else if (StringUtils.isNotBlank(edgeData.getServerApplicationType())) {
                    s.setApplicationType(edgeData.getServerApplicationType());
                    s.setNodeType(ServiceMapNode.TypeEnum.APP_CLUSTER);
                }
                s.setInstanceNames(new HashSet<>());
                s.setClients(new HashMap<>());
                s.setServers(new HashMap<>());
                s.setEdges(new HashMap<>());
                return s;
            });

            // aggregate statistics from the edges
            server.setBusinessFaultCount(server.getBusinessFaultCount() + edgeData.getBusinessFaultCount());
            server.setSystemFaultCount(server.getSystemFaultCount() + edgeData.getSystemFaultCount());
            int edgeDataSuccessCount = edgeData.getCallCount() - edgeData.getBusinessFaultCount() - edgeData.getSystemFaultCount();
            server.setSuccessCount(server.getSuccessCount() + edgeDataSuccessCount);

            // record the links between the client and the server
            node.getServers().put(serverApplicationCluster, server);
            server.getClients().put(serverApplicationCluster, node);

            // cache the node as a client
            clients.compute(serverApplicationCluster, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(node);
                return v;
            });

            ServiceMapEdgeDto edge = new ServiceMapNodeDto.ServiceMapEdgeDto();
            edge.setServerSoftware(edgeData.getServerSoftware());
            edge.setCallCount(edgeData.getCallCount());
            edge.setSuccessCount(edgeDataSuccessCount);
            edge.setBusinessFaultCount(edgeData.getBusinessFaultCount());
            edge.setSystemFaultCount(edgeData.getSystemFaultCount());
            edge.setDuration(edgeData.getDuration());
            node.getEdges().put(serverApplicationCluster, edge);
        });

        // find and set missing clients
        clients.forEach((serverCluster, cs) -> {
            ServiceMapNodeDto server = map.get(serverCluster);
            if (server == null) {
                return;
            }
            cs.forEach(c -> server.getClients().put(c.getAppCluster(), c));
        });

        return map;
    }

    private List<ServiceMapEdgeResult> extendEdges(List<ServiceMapEdgeResult> original) {
        return original.stream()
            .flatMap(edge -> {
                if (!MIDDLEWARE_TYPES.contains(edge.getServerSoftware())) {
                    return Stream.of(edge);
                }
                ServiceMapEdgeResult producer = new ServiceMapEdgeResult();
                producer.setFromService(edge.getFromService());
                producer.setToService(edge.getServerSoftware());
                producer.setClientApplicationType(edge.getClientApplicationType());
                producer.setClientApplicationCluster(edge.getClientApplicationCluster());
                // middleware doesn't have application.type
                // producer.setServerApplicationType(edge.getServerApplicationType());
                producer.setServerApplicationCluster(edge.getServerSoftware());
                producer.setServerSoftware(edge.getServerSoftware());

                ServiceMapEdgeResult consumer = new ServiceMapEdgeResult();
                consumer.setFromService(edge.getServerSoftware());
                consumer.setToService(edge.getToService());
                // middleware doesn't have application.type
                // consumer.setClientApplicationType(edge.getClientApplicationType());
                consumer.setClientApplicationCluster(edge.getServerSoftware());
                consumer.setClientSoftware(edge.getServerSoftware());
                consumer.setServerApplicationType(edge.getServerApplicationType());
                consumer.setServerApplicationCluster(edge.getServerApplicationCluster());
                // application doesn't have server.software
                // consumer.setServerSoftware(edge.getServerSoftware());

                return Stream.of(producer, consumer)
                    .peek(result -> {
                        result.setPlatformName(edge.getPlatformName());
                        result.setCallCount(edge.getCallCount());
                        result.setBusinessFaultCount(edge.getBusinessFaultCount());
                        result.setSystemFaultCount(edge.getSystemFaultCount());
                        result.setDuration(edge.getDuration());
                    });
            })
            .collect(Collectors.toList());
    }
}
