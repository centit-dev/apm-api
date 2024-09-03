package com.stardata.observ.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.stardata.observ.common.FaultKindEnum;
import com.stardata.observ.common.MonitorType;
import com.stardata.observ.domain.CommonalityKeyBlacklist;
import com.stardata.observ.domain.HeatmapTrendListDto;
import com.stardata.observ.domain.MonitorQuery;
import com.stardata.observ.domain.SpanCommonalityDto;
import com.stardata.observ.domain.SpanFaultTrendListDto;
import com.stardata.observ.helper.DurationStatisticsDto;
import com.stardata.observ.mapper.ch.SpanMapperDelegator;
import com.stardata.observ.mapper.ch.SpanSnapshotMapper;
import com.stardata.observ.mapper.ch.TraceFaultMapperDelegator;
import com.stardata.observ.model.ch.DurationStatistics;
import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;
import com.stardata.observ.model.ch.SpanAttributeSum;
import com.stardata.observ.model.ch.SpanExceptionGroup;
import com.stardata.observ.model.ch.SpanFaultGroup;
import com.stardata.observ.model.ch.SpanSnapshot;
import com.stardata.observ.model.ch.TraceFault;
import com.stardata.observ.model.ch.TraceFaultExceptionGroup;
import com.stardata.observ.model.ch.TraceFaultInstanceGroup;
import com.stardata.observ.model.ch.TraceFaultTrend;
import com.stardata.observ.model.pg.ApplicationStructure;
import com.stardata.observ.model.pg.ExceptionCategory;
import com.stardata.observ.model.pg.ExceptionDefine;
import com.stardata.observ.vo.AppRelatedResource;
import com.stardata.observ.vo.DurationTrend;
import com.stardata.observ.vo.DurationTrendResponse;
import com.stardata.observ.vo.DurationType;
import com.stardata.observ.vo.FaultTrend;
import com.stardata.observ.vo.FaultTrendResponse;
import com.stardata.observ.vo.GraphPointData;
import com.stardata.observ.vo.HeatmapResponse;
import com.stardata.observ.vo.InitialCauseAppInstance;
import com.stardata.observ.vo.InitialCauseAppInstanceListResponse;
import com.stardata.observ.vo.InitialCauseSpan;
import com.stardata.observ.vo.InitialCauseSpanListResponse;
import com.stardata.observ.vo.InitialCauseSpanPage;
import com.stardata.observ.vo.Percentile;
import com.stardata.observ.vo.ServiceErrorDiagnosisResponse;
import com.stardata.observ.vo.ServiceErrorDiagnosisResponseData;
import com.stardata.observ.vo.ServiceExceptionCategory;
import com.stardata.observ.vo.ServiceExceptionInfo;
import com.stardata.observ.vo.SpanAttributeType;
import com.stardata.observ.vo.SpanCommonality;
import com.stardata.observ.vo.SpanCommonalityListResponse;
import com.stardata.observ.vo.SpanCommonalityValue;
import com.stardata.observ.vo.SpanGroup;
import com.stardata.observ.vo.SpanGroupField;
import com.stardata.observ.vo.SpanGroupListResponse;
import com.stardata.observ.vo.SpanKind;
import com.stardata.observ.vo.SpanStatus;

import io.opentelemetry.semconv.ClientAttributes;
import io.opentelemetry.semconv.NetworkAttributes;
import io.opentelemetry.semconv.ServerAttributes;

import lombok.AllArgsConstructor;
import lombok.Builder;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@AllArgsConstructor
@Builder
@Service
public class TraceFaultService {
    private static final Pattern PERCENTILE_PATTERN = Pattern.compile("^[pP](?<percentile>\\d{1,2})$");
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(?<ip>\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})$");
    static final Pattern KEY_VALUE_PATTERN = Pattern.compile("(?<key>.+?)=(?<value>.+?)$");
    private static final Comparator<SpanCommonalityDto> COMMONALITY_COMPARATOR =
            Comparator.comparing(SpanCommonalityDto::getValue).reversed();

    private final MonitorDashboardService dashboardService;
    private final QueryDictionaryService dictionaryService;
    private final SpanSnapshotService snapshotService;
    private final SoftwareService softwareService;
    private final ResourceService resourceService;
    private final ExceptionDataService exceptionDataService;
    private final K8sWatchService watchService;
    private final TraceFaultMapperDelegator mapper;
    private final SpanMapperDelegator spanMapper;
    private final HeatmapAdaptor heatmapAdaptor;
    private final SpanSnapshotMapper snapshotMapper;
    private final SysParameterService parameterService;

    public List<TraceFault> getTraceFaultsByIds(List<String> traceIds) {
        QueryWrapper<TraceFault> wrapper = new QueryWrapper<>();
        wrapper.in("TraceId", traceIds);
        wrapper.select(
            "Timestamp", "TraceId", "PlatformName", "AppCluster", "InstanceName",
            "RootServiceName", "RootSpanName", "RootDuration",
            "ParentSpanId", "SpanId", "ServiceName", "SpanName", "SpanKind", "FaultKind");
        return mapper.selectList(wrapper);
    }

    public TraceFault getTraceFaultById(String traceId) {
        QueryWrapper<TraceFault> wrapper = new QueryWrapper<>();
        wrapper.eq("TraceId", traceId);
        wrapper.select(
            "Timestamp", "TraceId", "PlatformName", "AppCluster", "InstanceName",
            "RootServiceName", "RootSpanName", "RootDuration",
            "ParentSpanId", "SpanId", "ServiceName", "SpanName", "SpanKind", "FaultKind");
        return mapper.selectOne(wrapper);
    }

    public InitialCauseAppInstanceListResponse groupByAppInstance(List<String> spanSnapshotIds) {
        List<SpanSnapshot> snapshots = snapshotService.getSnapshotsByIds(spanSnapshotIds);
        if (CollectionUtils.isEmpty(snapshots)) {
            return new InitialCauseAppInstanceListResponse();
        }
        Date from;
        Date to;
        if (snapshots.size() == 1) {
            from = snapshots.get(0).getStartTime();
            to = snapshots.get(0).getEndTime();
        } else {
            from = snapshots.stream().map(SpanSnapshot::getStartTime).min(Date::compareTo).orElse(new Date());
            to = snapshots.stream().map(SpanSnapshot::getEndTime).max(Date::compareTo).orElse(new Date());
        }
        List<ApplicationStructure> pods = watchService.getPods(from, to);
        List<TraceFaultInstanceGroup> groups = mapper.groupByAppInstance(spanSnapshotIds);
        MultiKeyMap<String, List<Map<String, String>>> serversPerGroup = spanMapper.findServersByAppInstance(spanSnapshotIds);
        List<InitialCauseAppInstance> instances = groups
                .stream()
                .map(group -> {
                    InitialCauseAppInstance instance = new InitialCauseAppInstance();
                    instance.setPlatform(dictionaryService.getDisplayPair(group.getPlatformName()));
                    instance.setApplication(dictionaryService.getDisplayPair(group.getServiceName()));
                    instance.setCluster(group.getAppCluster());
                    instance.setInstance(group.getInstanceName());
                    instance.setSoftware(group.getApplicationType());
                    instance.setServiceName(group.getServiceName());
                    instance.setSpanName(group.getSpanName());
                    instance.setSpanKind(SpanKind.fromValue(group.getSpanKind()));
                    instance.setExceptionCount(group.getFaultCalls());
                    instance.setCallCount(group.getCalls());

                    if (StringUtils.isNotBlank(group.getServiceName())
                            && StringUtils.isNotBlank(group.getSpanName())
                            && StringUtils.isNotBlank(group.getInstanceName())) {
                        MonitorQuery query = new MonitorQuery();
                        query.setK8sPodName(group.getInstanceName());
                        query.setServiceName(group.getServiceName());
                        query.setSpanName(group.getSpanName());
                        if (from != null) {
                            query.setFrom(from.getTime());
                        }
                        if (to != null) {
                            query.setTo(to.getTime());
                        }
                        instance.setServiceMonitorUrl(dashboardService.getUrl(MonitorType.SERVICE.getValue(), query));
                    }

                    List<Map<String, String>> servers = serversPerGroup.get(group.getPlatformName(), group.getAppCluster(), group.getInstanceName());
                    instance.setResources(buildResources(group, servers, pods, from, to));
                    return instance;
                })
                .collect(Collectors.toList());
        return new InitialCauseAppInstanceListResponse().data(instances);
    }

    private List<AppRelatedResource> buildResources(
            TraceFaultInstanceGroup group, List<Map<String, String>> servers, List<ApplicationStructure> pods, Date from, Date to) {
        if (StringUtils.isBlank(group.getInstanceName())) {
            return Collections.emptyList();
        }

        List<AppRelatedResource> resources = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(servers)) {
            servers.stream()
                    .map(server -> {
                        String host = server.get("server.host");
                        if (StringUtils.isBlank(host) && StringUtils.isBlank(server.get("server.software"))) {
                            return null;
                        }
                        String podName;
                        Matcher matcher = IPV4_PATTERN.matcher(host);
                        if (matcher.find()) {
                            String parentName = pods.stream().filter(pod -> pod.getCode().contains(host))
                                    .max(Comparator.comparing(ApplicationStructure::getUpdateTime))
                                    .map(ApplicationStructure::getParentCode)
                                    .orElse(null);
                            if (parentName != null) {
                                podName = pods.stream().filter(pod -> pod.getParentCode().contains(parentName))
                                        .filter(pod -> !IPV4_PATTERN.matcher(pod.getCode()).matches())
                                        .max(Comparator.comparing(ApplicationStructure::getUpdateTime))
                                        .map(ApplicationStructure::getCode)
                                        .orElse(null);
                            } else {
                                podName = host;
                            }
                        } else if (StringUtils.isNotBlank(host)) {
                            String part = host.contains(".") ? host.split("\\.")[0] : host;
                            podName = pods.stream().filter(pod -> pod.getParentCode().contains(part))
                                    .max(Comparator.comparing(ApplicationStructure::getUpdateTime))
                                    .map(ApplicationStructure::getCode)
                                    .orElse(null);
                        } else {
                            podName = host;
                        }
                        return buildResource(podName, server.get("server.software"), from, to);
                    })
                    .filter(Objects::nonNull)
                    .forEach(resources::add);
        }

        if (StringUtils.isNotBlank(group.getApplicationType())) {
            if ("springcloud".equals(group.getApplicationType()) || "dubbo".equals(group.getApplicationType())) {
                resources.add(resourceService.buildAppResource(group.getInstanceName(), "java", from, to));
            } else {
                resources.add(resourceService.buildAppResource(group.getInstanceName(), group.getApplicationType(), from, to));
            }
        }

        // add k8s resource
        resources.add(resourceService.buildK8sResource(group.getInstanceName(), from, to));

        // add node resource
        resources.add(resourceService.buildNodeResource(group.getNode(), from, to));

        return resources;
    }

    private AppRelatedResource buildResource(String instanceName, String type, Date from, Date to) {
        return buildResource(instanceName, type, type, from, to);
    }

    private AppRelatedResource buildResource(String instanceName, String name, String type, Date from, Date to) {
        MonitorQuery query = new MonitorQuery();
        query.setK8sPodName(instanceName);
        if (from != null) {
            query.setFrom(from.getTime());
        }
        if (to != null) {
            query.setTo(to.getTime());
        }

        return new AppRelatedResource()
                .id(instanceName)
                .name(name)
                .displayName(dictionaryService.getDisplayNameByName(name))
                .type(type)
                .logo(softwareService.getSoftwareLogo(type))
                .monitorURL(dashboardService.getUrl(type, query));
    }

    public HeatmapResponse getSpanHeatmap(String snapshotId, Integer granularity) {
        HeatmapTrendListDto dto = mapper.getHeatmapTrend(snapshotId, granularity);
        return heatmapAdaptor.adapt(dto);
    }

    public FaultTrendResponse getTraceFaultTrend(String snapshotId, Integer granularity) {
        SpanFaultTrendListDto dto = mapper.getTraceFaultTrend(snapshotId, granularity);
        if (dto == null) {
            return new FaultTrendResponse();
        }

        Map<Long, TraceFaultTrend> spanFaultTrendMap = dto.getTrends().stream()
                .collect(Collectors.toMap(
                        spanFaultTrend -> spanFaultTrend.getTimeUnix().getTime(),
                        Function.identity()));
        List<GraphPointData> data = LongStream
                .range(dto.getFromTime() / 1000 / dto.getGranularity(), dto.getToTime() / 1000 / dto.getGranularity())
                .mapToObj(index -> index * dto.getGranularity() * 1000)
                .map(time -> spanFaultTrendMap.getOrDefault(time, new TraceFaultTrend()))
                .map(trend -> new GraphPointData()
                        .successCount(trend.getSuccessCount())
                        .businessFaultCount(trend.getBusinessFaultCount())
                        .systemFaultCount(trend.getSystemFaultCount()))
                .collect(Collectors.toList());
        return new FaultTrendResponse()
                .data(new FaultTrend()
                        .fromTime(dto.getFromTime())
                        .toTime(dto.getToTime())
                        .granularity(dto.getGranularity())
                        .values(data));
    }

    public SpanCommonalityListResponse listTraceFaultCommonalities(String snapshotId, Integer limit) {
        CommonalityKeyBlacklist blacklist = parameterService.getCommonalityKeyBlacklist();
        SpanAttributeSum sum = mapper.sumAttributesBySnapshot(
                snapshotId,
                blacklist.getResourceAttributeBlacklist(),
                blacklist.getSpanAttributeBlacklist(),
                limit);
        if (sum == null) {
            return new SpanCommonalityListResponse();
        }
        List<SpanCommonality> data = Stream
                .concat(
                        sum.getResourceSum().entrySet().stream().map(entry -> Pair.of(SpanAttributeType.RESOURCE, entry)),
                        sum.getSpanSum().entrySet().stream().filter(entry -> entry.getValue() > 10)
                                .map(entry -> Pair.of(SpanAttributeType.SPAN, entry)))
                .map(entry -> {
                    SpanCommonalityDto commonality = new SpanCommonalityDto();
                    commonality.setType(entry.getLeft());

                    String keyValue = entry.getRight().getKey();
                    Matcher matcher = KEY_VALUE_PATTERN.matcher(keyValue);
                    if (!matcher.find()) {
                        return null;
                    }
                    commonality.setName(matcher.group("key"));
                    if (entry.getLeft() == SpanAttributeType.SPAN) {
                        String fullName = String.format("SpanAttributes['%s']", commonality.getName());
                        commonality.setName(fullName);
                        commonality.setDisplayName(dictionaryService.getDisplayNameByName(fullName));
                    } else if (entry.getLeft() == SpanAttributeType.RESOURCE) {
                        String fullName = String.format("ResourceAttributes['%s']", commonality.getName());
                        commonality.setName(fullName);
                        commonality.setDisplayName(dictionaryService.getDisplayNameByName(fullName));
                    }

                    SpanCommonalityValue value = new SpanCommonalityValue();
                    value.setCode(matcher.group("value"));
                    double count = entry.getRight().getValue().doubleValue();
                    value.setValue(count);

                    commonality.setValues(Lists.newArrayList(value));
                    commonality.setValue(count);

                    return commonality;
                }).filter(Objects::nonNull)
                .sorted(COMMONALITY_COMPARATOR)
                .limit(limit != null ? limit : 20)
                .collect(Collectors.toList());
        return new SpanCommonalityListResponse().data(data);
    }

    public SpanGroupListResponse groupBySnapshotIdAndAttributes(
            String snapshotId,
            List<SpanGroupField> spanGroupFields) {
        SpanSnapshot snapshot = snapshotService.getSnapshotById(snapshotId);
        if (snapshot == null) {
            throw new IllegalArgumentException("Invalid snapshot id: " + snapshotId);
        }

        List<String> fields = spanGroupFields == null
                ? Collections.emptyList()
                : spanGroupFields.stream()
                        .map(SpanGroupField::getName)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
        List<SpanFaultGroup> groups = mapper.groupBySnapshotIdAndAttributes(snapshotId, fields);
        return Flux.fromIterable(groups)
                .flatMap(group -> {
                    Mono<Tuple2<String, List<String>>> subSnapshotAndLabels = CollectionUtils.isEmpty(fields)
                            ? Mono.just(Tuples.of(snapshot.getSnapshotId(), Collections.<String>emptyList()))
                            : createSubSnapshotAndLabels(snapshot, fields, group);
                    return subSnapshotAndLabels.map(tuple -> {
                        SpanGroup spanGroup = new SpanGroup();
                        spanGroup.setSnapshotId(tuple.getT1());
                        spanGroup.setLabels(tuple.getT2());
                        spanGroup.setCallCount(group.getCalls());
                        spanGroup.setInnormalCount(group.getFaultCalls());
                        spanGroup.setInnormalInstances(group.getInstances());
                        spanGroup.setInnormalFirstServices(group.getRootServices()
                                .stream()
                                .map(StringUtils::trimToNull)
                                .filter(StringUtils::isNotBlank)
                                .collect(Collectors.toList()));
                        return spanGroup;
                    });
                })
                .sort(Comparator.comparing(SpanGroup::getInnormalCount).reversed())
                .collectList()
                .map(new SpanGroupListResponse()::groups)
                .block();
    }

    private Mono<Tuple2<String, List<String>>> createSubSnapshotAndLabels(SpanSnapshot parent, List<String> groupByAttributes, SpanFaultGroup group) {
        return Flux
                .zip(
                        Flux.fromIterable(groupByAttributes),
                        Flux.fromStream(group.getLabels().stream().map(StringUtils::trimToEmpty)))
                .map(tuple -> {
                    String key = tuple.getT1();
                    String value = tuple.getT2();
                    return new SnapshotFieldCondition(key, "=", value);
                })
                .collectList()
                .map(fieldConditions -> {
                    SnapshotCondition conditions = new SnapshotCondition();
                    fieldConditions.forEach(conditions::addFieldCondition);
                    SpanSnapshot subSnapshot = snapshotService.createSpanSnapshot(parent.getUserId(), parent.getSnapshotId(), parent.getConditions(), conditions);
                    List<String> labels = fieldConditions.stream()
                            .map(condition -> {
                                String key = dictionaryService.getDisplayNameByName(condition.getField());
                                return String.format("%s%s%s", key, condition.getOperator(), condition.getValue());
                            })
                            .collect(Collectors.toList());
                    return Tuples.of(subSnapshot.getSnapshotId(), labels);
                })
                .subscribeOn(Schedulers.newParallel("subSnapshot", 2));
    }

    public InitialCauseSpanListResponse getTraceFaults(String spanSnapshotId, Integer pageNo, Integer pageSize) {
        if (pageNo == null) {
            pageNo = 0;
        }
        if (pageSize == null) {
            pageSize = 50;
        }
        int offset = pageNo * pageSize;
        List<TraceFault> faults = mapper.getTraceFaults(spanSnapshotId, offset, pageSize);
        List<InitialCauseSpan> spans = faults.stream()
            .map(fault -> {
                InitialCauseSpan span = new InitialCauseSpan();
                span.setTraceId(fault.getTraceId());
                span.setSpanId(fault.getSpanId());
                span.setRootServiceName(fault.getRootServiceName());
                span.setRootSpanName(fault.getRootSpanName());
                span.setServiceName(fault.getServiceName());
                span.setSpanName(fault.getSpanName());
                span.setSpanKind(SpanKind.fromValue(fault.getSpanKind()));
                span.setStatus(convertStatus(fault.getFaultKind()));
                span.setPlatform(dictionaryService.getDisplayPair(fault.getPlatformName()));
                span.setCluster(fault.getAppCluster());
                span.setInstance(fault.getInstanceName());
                span.setTimestamp(fault.getTimestamp().getTime());
                span.setDuration(fault.getDuration());
                span.setGap(fault.getGap());
                span.setSelfDuration(fault.getSelfDuration());
                span.setLocalAddress(getLocalAddress(fault.getSpanAttributes()));
                span.setPeerAddress(getPeerAddress(fault.getSpanAttributes()));
                return span;
            })
            .collect(Collectors.toList());
        Integer total = snapshotMapper.getTraceCountBySnapshotIds(Collections.singletonList(spanSnapshotId));
        InitialCauseSpanPage page = new InitialCauseSpanPage()
                .spans(spans)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .pageCount(total / pageSize)
                .totalCount(total);
        return new InitialCauseSpanListResponse().data(page);
    }

    private SpanStatus convertStatus(String faultKind) {
        if (FaultKindEnum.BUSINESS_FAULT.value().equals(faultKind)) {
            return SpanStatus.BIZ_FAULT;
        } else if (FaultKindEnum.SYSTEM_FAULT.value().equals(faultKind)) {
            return SpanStatus.SYS_FAULT;
        }
        return SpanStatus.SUCCEED;
    }

    private String getLocalAddress(Map<String, String> spanAttributes) {
        String address = spanAttributes.getOrDefault(NetworkAttributes.NETWORK_LOCAL_ADDRESS.getKey(), StringUtils.EMPTY);
        if (address == null) {
            address = spanAttributes.getOrDefault(ClientAttributes.CLIENT_ADDRESS.getKey(), StringUtils.EMPTY);
        }

        String port = spanAttributes.getOrDefault(NetworkAttributes.NETWORK_LOCAL_PORT.getKey(), StringUtils.EMPTY);
        if (port == null) {
            port = spanAttributes.getOrDefault(ClientAttributes.CLIENT_PORT.getKey(), StringUtils.EMPTY);
        }
        if (StringUtils.isBlank(address) && StringUtils.isBlank(port)) {
            return StringUtils.EMPTY;
        }
        return String.format("%s:%s", address, port);
    }

    private String getPeerAddress(Map<String, String> spanAttributes) {
        String address = spanAttributes.getOrDefault(NetworkAttributes.NETWORK_PEER_ADDRESS.getKey(), StringUtils.EMPTY);
        if (address == null) {
            address = spanAttributes.getOrDefault(ServerAttributes.SERVER_ADDRESS.getKey(), StringUtils.EMPTY);
        }

        String port = spanAttributes.getOrDefault(NetworkAttributes.NETWORK_PEER_PORT.getKey(), StringUtils.EMPTY);
        if (port == null) {
            port = spanAttributes.getOrDefault(ServerAttributes.SERVER_PORT.getKey(), StringUtils.EMPTY);
        }
        if (StringUtils.isBlank(address) && StringUtils.isBlank(port)) {
            return StringUtils.EMPTY;
        }
        return String.format("%s:%s", address, port);
    }

    public DurationTrendResponse calculateDurationStatistics(
            List<String> spanSnapshotIds,
            List<Percentile> percentiles,
            List<DurationType> durationTypes,
            Integer granularity,
            Long fromTime,
            Long toTime) {
        // check if percentiles are available
        List<BigDecimal> quantiles = percentiles.stream()
                .map(Percentile::getValue)
                .map(this::getPercentileValue)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(quantiles)) {
            return new DurationTrendResponse();
        }

        // make the query
        List<String> types = durationTypes.stream().map(DurationType::getValue).collect(Collectors.toList());
        DurationStatisticsDto dto = mapper.calculateDurationStatistics(
                spanSnapshotIds,
                fromTime, toTime, granularity, quantiles, types);
        if (dto == null) {
            return new DurationTrendResponse();
        }
        Map<Long, DurationStatistics> statistics = dto.getStatistics()
                .stream()
                .collect(Collectors.toMap(
                        stats -> stats.getTimeUnix().getTime(), Function.identity()));
        DurationTrend data = new DurationTrend()
                .fromTime(dto.getFromTime())
                .toTime(dto.getToTime())
                .granularity(dto.getGranularity())
                .percentile(percentiles);

        // fill the empty 0s
        LongStream
                .range(dto.getFromTime() / 1000 / dto.getGranularity(), dto.getToTime() / 1000 / dto.getGranularity() + 1)
                .mapToObj(index -> index * 1000 * dto.getGranularity())
                .forEach(group -> {
                    DurationStatistics stats = statistics.get(group);
                    if (stats == null) {
                        data.addTraceDurationsItem(Collections.emptyList());
                        data.addSelfDurationsItem(Collections.emptyList());
                        data.addGapsItem(Collections.emptyList());
                    } else {
                        data.addTraceDurationsItem(stats.getTraceDurationPercentiles());
                        data.addSelfDurationsItem(stats.getSelfDurationPercentiles());
                        data.addGapsItem(stats.getGapDurationPercentiles());
                    }
                });
        return new DurationTrendResponse().data(data);
    }

    @Nullable
    private BigDecimal getPercentileValue(String percentile) {
        Matcher matcher = PERCENTILE_PATTERN.matcher(percentile);
        if (!matcher.find()) {
            return null;
        }
        try {
            return new BigDecimal(matcher.group("percentile"))
                    .movePointLeft(2);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    public ServiceErrorDiagnosisResponse getSpanExceptionAnalysis(List<String> spanSnapshotIds) {
        Map<String, Long> traceFaultGroups = mapper.groupExceptionBySnapshotIds(spanSnapshotIds)
                .stream()
                .collect(Collectors.toMap(
                        TraceFaultExceptionGroup::getExceptionDefinition,
                        TraceFaultExceptionGroup::getCount));
        List<SpanExceptionGroup> groups = spanMapper.findSpanExceptions(spanSnapshotIds);
        Map<String, String> exceptionStacktraces = groups.stream()
                .collect(Collectors.toMap(
                        SpanExceptionGroup::getExceptionDefinition,
                        SpanExceptionGroup::getExceptionBodySample,
                        (prev, next) -> next));
        Map<String, ExceptionDefine> exceptionDefines = exceptionDataService.FindExceptionDefineByNames(traceFaultGroups.keySet());
        Map<Long, ExceptionCategory> exceptionCategories = exceptionDataService.getExceptionCategoriesByIds(
                exceptionDefines.values().stream()
                        .map(ExceptionDefine::getCategoryId)
                        .collect(Collectors.toSet()));

        AtomicInteger spanCount = new AtomicInteger(0);
        Map<Long, ServiceExceptionCategory> categories = new HashMap<>();

        // add known exceptions
        traceFaultGroups.forEach((shortName, count) -> {
            ExceptionDefine definition = exceptionDefines.get(shortName);
            if (definition == null) {
                return;
            }
            ExceptionCategory category = exceptionCategories.get(definition.getCategoryId());
            if (category == null) {
                return;
            }
            categories.compute(category.getId(), (k, v) -> {
                if (v == null) {
                    v = new ServiceExceptionCategory()
                            .categoryId(category.getId().intValue())
                            .categoryName(category.getName())
                            .spanCount(0)
                            .exceptions(new ArrayList<>());
                }
                ServiceExceptionInfo info = new ServiceExceptionInfo()
                        .exceptionId(definition.getId().intValue())
                        .exceptionName(definition.getShortName())
                        .rootExceptionBody(exceptionStacktraces.get(definition.getShortName()))
                        .spanCount(count.intValue());
                v.addExceptionsItem(info);
                v.spanCount(v.getSpanCount() + count.intValue());
                spanCount.addAndGet(count.intValue());
                return v;
            });
        });

        // add unknown exceptions
        ExceptionDefine otherDefinition = exceptionDataService.getOtherDefinition();
        ExceptionCategory otherCategory = exceptionDataService.getOtherCategory();
        ServiceExceptionInfo otherExceptionInfo = new ServiceExceptionInfo()
                .exceptionId(otherDefinition.getId().intValue())
                .exceptionName(otherDefinition.getShortName());
        ServiceExceptionCategory otherServiceCategory = new ServiceExceptionCategory()
                .categoryId(otherCategory.getId().intValue())
                .categoryName(otherCategory.getName())
                .spanCount(0)
                .exceptions(Collections.singletonList(otherExceptionInfo));
        exceptionStacktraces.forEach((shortName, stacktrace) -> {
            if (exceptionDefines.containsKey(shortName) || !traceFaultGroups.containsKey(shortName)) {
                return;
            }
            otherExceptionInfo.rootExceptionBody(stacktrace)
                    .setSpanCount(otherServiceCategory.getSpanCount() + traceFaultGroups.get(shortName).intValue());
            otherServiceCategory.setSpanCount(otherExceptionInfo.getSpanCount());
        });
        if (otherServiceCategory.getSpanCount() > 0) {
            categories.putIfAbsent(otherCategory.getId(), otherServiceCategory);
        }

        ServiceErrorDiagnosisResponseData data = new ServiceErrorDiagnosisResponseData()
                .spanCount(spanCount.get())
                .categories(new ArrayList<>(categories.values()));
        return new ServiceErrorDiagnosisResponse().data(data);
    }
}
