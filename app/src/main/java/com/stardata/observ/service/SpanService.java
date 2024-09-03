package com.stardata.observ.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.stardata.observ.common.FaultKindEnum;
import com.stardata.observ.common.QueryOperator;
import com.stardata.observ.common.ResourceAttributeEnum;
import com.stardata.observ.common.SpanAttributeEnum;
import com.stardata.observ.common.SysParameterCodeEnum;
import com.stardata.observ.domain.HeatmapTrendListDto;
import com.stardata.observ.domain.SpanListDto;
import com.stardata.observ.helper.CHWhereHelper;
import com.stardata.observ.mapper.ch.LogMapper;
import com.stardata.observ.mapper.ch.SpanMapperDelegator;
import com.stardata.observ.model.ch.LogCount;
import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.model.ch.Span;
import com.stardata.observ.model.ch.TimelineSpanDto;
import com.stardata.observ.model.pg.ExceptionDefine;
import com.stardata.observ.vo.AppLogDetail;
import com.stardata.observ.vo.FieldCondition;
import com.stardata.observ.vo.HeatmapResponse;
import com.stardata.observ.vo.KeyValuePair;
import com.stardata.observ.vo.SpanBrief;
import com.stardata.observ.vo.SpanDetail;
import com.stardata.observ.vo.SpanDetailResponse;
import com.stardata.observ.vo.SpanKind;
import com.stardata.observ.vo.SpanListResponse;
import com.stardata.observ.vo.SpanPage;
import com.stardata.observ.vo.SpanStatus;
import com.stardata.observ.model.ch.Log;
import com.stardata.observ.model.ch.ServiceMapEdgeResult;

import io.opentelemetry.semconv.incubating.K8sIncubatingAttributes;

import lombok.AllArgsConstructor;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SpanService {
    private final SpanMapperDelegator spanMapper;
    private final LogMapper logMapper;
    private final QueryDictionaryService dictionaryService;
    private final QueryKeyService keyService;
    private final ExceptionDataService exceptionDataService;
    private final SysParameterService parameterService;
    private final CHWhereHelper chWhereHelper;
    private final HeatmapAdaptor heatmapAdaptor;

    public List<TimelineSpanDto> findTimelineSpansByTraceIdWithLimitation(String traceId, Integer limit) {
        return spanMapper.findTimelineSpansByTraceId(traceId, limit);
    }

    public SpanDetailResponse findSpanByTraceIdAndSpanId(String traceId, String spanId) {
        Span span = spanMapper.findSpanByTraceIdAndSpanId(traceId, spanId);
        if (span == null) {
            return new SpanDetailResponse();
        }

        List<Log> logs = logMapper.findLogByTraceIdAndSpanId(traceId, spanId);
        List<String> dataMaskings = parameterService.getStringListParameter(SysParameterCodeEnum.DATA_MASKING_LIST.value());
        SpanDetail data = new SpanDetail()
                .timestamp(span.getTimestamp().getTime())
                .traceId(span.getTraceId())
                .spanId(span.getSpanId())
                .parentSpanId(span.getParentSpanId())
                .serviceName(span.getServiceName())
                .spanName(span.getSpanName())
                .spanKind(convertKind(span.getSpanKind()))
                .duration(span.getDuration())
                .application(dictionaryService.getDisplayPair(span.getServiceName()))
                .resourceAttributes(getResourceAttributes(span))
                .spanAttributes(getSpanAttributes(span))
                .requestAttributes(getRequestBody(span, dataMaskings))
                .responseAttributes(getResponseBody(span, dataMaskings))
                .logs(logs.stream().map(this::buildLogDetail).collect(Collectors.toList()));
        if (span.getResourceAttributes() != null) {
            data.setPlatform(dictionaryService.getDisplayPair(span.getResourceAttributes().get(ResourceAttributeEnum.APP_PLATFORM.value())));
            data.setCluster(span.getResourceAttributes().get(ResourceAttributeEnum.APP_CLUSTER.value()));
        }
        if (span.getSpanAttributes() != null) {
            data.setSoftware(dictionaryService.getDisplayPair(span.getSpanAttributes().get(SpanAttributeEnum.APP_TYPE.value())));
        }
        return new SpanDetailResponse().data(data);
    }

    private AppLogDetail buildLogDetail(Log log) {
        return new AppLogDetail()
                .timestamp(log.getTimestamp().getTime())
                .traceId(log.getTraceId())
                .spanId(log.getSpanId())
                .severityNumber(log.getSeverityNumber())
                .severityText(log.getSeverityText())
                .scopeName(log.getScopeName())
                .body(log.getBody())
                .logAttributes(getLogAttributes(log));
    }

    private List<KeyValuePair> getResourceAttributes(Span span) {
        return getKeyValuePairs(span.getResourceAttributes(), "ResourceAttributes['%s']");
    }

    private List<KeyValuePair> getSpanAttributes(Span span) {
        return getKeyValuePairs(span.getSpanAttributes(), "SpanAttributes['%s']");
    }

    private List<KeyValuePair> getRequestBody(Span span, List<String> dataMaskings) {
        return getBodyKeyValuePairs(span.getSpanAttributes(), SpanAttributeEnum.HTTP_REQUEST_BODY.value(), dataMaskings);
    }

    private List<KeyValuePair> getResponseBody(Span span, List<String> dataMaskings) {
        return getBodyKeyValuePairs(span.getSpanAttributes(), SpanAttributeEnum.HTTP_RESPONSE_BODY.value(), dataMaskings);
    }

    private List<KeyValuePair> getLogAttributes(Log log) {
        return getKeyValuePairs(log.getLogAttributes(), "LogAttributes['%s']");
    }

    private List<KeyValuePair> getKeyValuePairs(Map<String, String> attributes, String format) {
        if (MapUtils.isEmpty(attributes)) {
            return Collections.emptyList();
        }
        return attributes.entrySet().stream()
                .filter(entry -> !entry.getKey().startsWith(SpanAttributeEnum.HTTP_REQUEST_BODY.value())
                        && !entry.getKey().startsWith(SpanAttributeEnum.HTTP_RESPONSE_BODY.value()))
                .map(entry -> {
                    String key = String.format(format, entry.getKey());
                    return new KeyValuePair()
                        .key(dictionaryService.getDisplayNameByName(key))
                        .value(entry.getValue());
                })
                .collect(Collectors.toList());
    }

    private List<KeyValuePair> getBodyKeyValuePairs(Map<String, String> attributes, String prefix, List<String> maskings) {
        if (MapUtils.isEmpty(attributes)) {
            return Collections.emptyList();
        }
        return attributes.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(prefix))
                .filter(entry -> maskings.stream().noneMatch(entry.getKey()::contains))
                .map(entry -> {
                    String key = entry.getKey().replace(prefix, "");
                    return new KeyValuePair()
                        .key(dictionaryService.getDisplayNameByName(key))
                        .value(entry.getValue());
                })
                .collect(Collectors.toList());
    }

    public List<ServiceMapEdgeResult> findEdgeSpansByTraceId(String traceId) {
        return spanMapper.findEdgeSpanByTraceId(traceId);
    }

    public SpanListResponse findSpans(
            List<String> spanSnapshotIds,
            List<FieldCondition> conditions,
            Integer pageNo, Integer pageSize) {
        return findSpans(
                spanSnapshotIds, conditions,
                null, null,
                null, null,
                null, null,
                null, null,
                pageNo, pageSize);
    }

    public SpanListResponse findSpans(
            Long fromTime, Long toTime,
            Long minDuration, Long maxDuration,
            Map<String, String> extraParams,
            Integer pageNo, Integer pageSize) {
        Map<String, String> resourceConditions = new HashMap<>();
        Map<String, String> spanConditions = new HashMap<>();
        Map<String, Boolean> keys = keyService.listKeys();
        extraParams.forEach((key, value) -> {
            if (!keys.containsKey(key)) {
                return;
            }
            if (keys.getOrDefault(key, false)) {
                resourceConditions.put(key, value);
            } else {
                spanConditions.put(key, value);
            }
        });
        String serviceName = extraParams.get("service.name");
        String spanName = extraParams.get("span.name");

        return findSpans(
            Collections.emptyList(), Collections.emptyList(),
            fromTime, toTime, minDuration, maxDuration,
            resourceConditions, spanConditions, serviceName, spanName,
            pageNo, pageSize);
    }

    public SpanListResponse findSpans(
            List<String> spanSnapshotIds,
            List<FieldCondition> conditions,
            Long fromTime, Long toTime,
            Long minDuration, Long maxDuration,
            Map<String, String> resourceConditions, Map<String, String> spanConditions,
            String serviceName, String spanName,
            Integer pageNo, Integer pageSize) {
        if (pageNo == null) {
            pageNo = 0;
        }
        if (pageSize == null) {
            pageSize = 50;
        }
        int offset = pageNo * pageSize;
        SpanListDto dto;
        if (CollectionUtils.isEmpty(spanSnapshotIds)) {
            dto = spanMapper.findSpans(
                    fromTime, toTime, minDuration, maxDuration,
                    resourceConditions, spanConditions,
                    serviceName, spanName,
                    offset, pageSize);
        } else {
            List<SnapshotCondition.SnapshotFieldCondition> snapshotConditions = formatConditions(conditions);
            String whereStatement = chWhereHelper.generateWhereStringFromSnapshot(snapshotConditions);
            dto = spanMapper.findSpanBySnapshotWhereStatement(spanSnapshotIds, whereStatement, offset, pageSize);
        }

        if (dto == null) {
            return new SpanListResponse();
        }

        if (CollectionUtils.isEmpty(dto.getSpans())) {
            SpanPage data = new SpanPage()
                    .pageNo(pageNo)
                    .pageSize(pageSize)
                    .pageCount(0)
                    .totalCount(0L)
                    .spans(Collections.emptyList());
            return new SpanListResponse().data(data);
        }

        Map<String, Integer> logCounts = countLogs(dto.getSpans());
        List<SpanBrief> spanBriefs = dto.getSpans()
                .stream()
                .map(span -> {
                    SpanBrief brief = new SpanBrief();
                    brief.setTraceId(span.getTraceId());
                    brief.setSpanId(span.getSpanId());
                    brief.setParentSpanId(span.getParentSpanId());
                    brief.setTimestamp(span.getTimestamp().getTime());
                    brief.setDuration(span.getDuration());
                    brief.setStatus(convertStatus(span.getFaultKind()));
                    brief.setSpanKind(convertKind(span.getSpanKind()));
                    brief.setServiceName(span.getServiceName());
                    brief.setSpanName(span.getSpanName());
                    brief.setApplication(dictionaryService.getDisplayPair(span.getServiceName()));
                    if (span.getResourceAttributes() != null) {
                        String value = span.getResourceAttributes().get(ResourceAttributeEnum.APP_PLATFORM.value());
                        brief.setPlatform(dictionaryService.getDisplayPair(value));

                        value = span.getResourceAttributes().get(ResourceAttributeEnum.APP_CLUSTER.value());
                        brief.setCluster(value);

                        value = span.getResourceAttributes().get(K8sIncubatingAttributes.K8S_POD_NAME.getKey());
                        brief.setInstance(value);
                    }
                    if (span.getSpanAttributes() != null) {
                        String value = span.getSpanAttributes().get(SpanAttributeEnum.APP_TYPE.value());
                        brief.setSoftware(dictionaryService.getDisplayPair(value));
                    }
                    brief.setLogCount(logCounts.getOrDefault(span.getSpanId(), 0));
                    return brief;
                })
                .collect(Collectors.toList());
        SpanPage data = new SpanPage()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .pageCount(dto.getSpans().size())
                .totalCount(dto.getTotal())
                .spans(spanBriefs);
        return new SpanListResponse().data(data);
    }

    private List<SnapshotCondition.SnapshotFieldCondition> formatConditions(List<FieldCondition> conditions) {
        if (CollectionUtils.isEmpty(conditions)) {
            return Collections.emptyList();
        }
        List<SnapshotCondition.SnapshotFieldCondition> fieldConditions = new ArrayList<>();
        conditions.forEach(condition -> {
            String name = condition.getName();
            // monkey-patch to handle exceptionId
            if ("exceptionId".equals(name)) {
                if ("0".equals(condition.getValue())) {
                    name = String.format("SpanAttributes['%s']", SpanAttributeEnum.FAULT_KIND.value());
                    fieldConditions.add(new SnapshotCondition.SnapshotFieldCondition(name, QueryOperator.Exists.getOperator(), ""));
                    name = String.format("SpanAttributes['%s']", SpanAttributeEnum.EXCEPTION_NAME.value());
                    fieldConditions.add(new SnapshotCondition.SnapshotFieldCondition(name, QueryOperator.EQUAL.getOperator(), ""));
                } else if (StringUtils.isNumeric(condition.getValue())) {
                    name = String.format("SpanAttributes['%s']", SpanAttributeEnum.EXCEPTION_NAME.value());
                    ExceptionDefine definition = exceptionDataService.getExceptionDefineById(Long.valueOf(condition.getValue()));
                    if (definition != null) {
                        fieldConditions.add(new SnapshotCondition.SnapshotFieldCondition(name, condition.getOperator(), definition.getShortName()));
                    }
                }
            } else {
                fieldConditions.add(new SnapshotCondition.SnapshotFieldCondition(name, condition.getOperator(), condition.getValue()));
            }
        });
        return fieldConditions;
    }

    private Map<String, Integer> countLogs(List<Span> spans) {
        Set<String> traceIds = new HashSet<>();
        Set<String> spanIds = new HashSet<>();
        spans.forEach(span -> {
            traceIds.add(span.getTraceId());
            spanIds.add(span.getSpanId());
        });
        return logMapper.countLogsBySpanIds(traceIds, spanIds)
                .stream()
                .collect(Collectors.toMap(
                    LogCount::getSpanId,
                    LogCount::getCount));
    }

    private SpanStatus convertStatus(FaultKindEnum faultKind) {
        switch (faultKind) {
            case SYSTEM_FAULT:
                return SpanStatus.SYS_FAULT;
            case BUSINESS_FAULT:
                return SpanStatus.BIZ_FAULT;
            default:
                return SpanStatus.SUCCEED;
        }
    }

    private SpanKind convertKind(String spanKind) {
        switch (spanKind) {
            case "SPAN_KIND_CLIENT":
                return SpanKind.CLIENT;
            case "SPAN_KIND_SERVER":
                return SpanKind.SERVER;
            case "SPAN_KIND_INTERNAL":
                return SpanKind.INTERNAL;
            case "SPAN_KIND_PRODUCER":
                return SpanKind.PRODUCER;
            case "SPAN_KIND_CONSUMER":
                return SpanKind.CONSUMER;
            default:
                return null;
        }
    }

    public HeatmapResponse getClusterHeatmap(String snapshotId, Integer granularity) {
        HeatmapTrendListDto dto = spanMapper.getHeatmapTrend(snapshotId, granularity);
        return heatmapAdaptor.adapt(dto);
    }
}
