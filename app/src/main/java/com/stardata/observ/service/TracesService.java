package com.stardata.observ.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.stardata.observ.common.CommonConstants;
import com.stardata.observ.common.SysParameterCodeEnum;
import com.stardata.observ.mapper.ch.SnapshotQuery;
import com.stardata.observ.model.ch.ServiceMapEdgeResult;
import com.stardata.observ.model.ch.TimelineSpanDto;
import com.stardata.observ.model.ch.Trace;
import com.stardata.observ.model.ch.TraceFault;
import com.stardata.observ.vo.ServiceMapResponse;
import com.stardata.observ.vo.TimelineSpan;
import com.stardata.observ.vo.TraceBrief;
import com.stardata.observ.vo.TraceDetail;
import com.stardata.observ.vo.TraceDetailResponse;
import com.stardata.observ.vo.TraceListResponse;
import com.stardata.observ.vo.TracePage;
import com.stardata.observ.mapper.ch.SpanSnapshotMapper;
import com.stardata.observ.mapper.ch.TraceMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class TracesService {
    private final TraceMapper traceMapper;
    private final TraceFaultService traceFaultService;
    private final SpanService spanService;
    private final ServicesMapService serviceMapService;
    private final QueryDictionaryService dictionaryService;
    private final SpanSnapshotMapper spanSnapshotMapper;
    private final SysParameterService parameterService;

    public TraceListResponse listTracesBySpanSnapshots(String userId, List<String> spanSnapshotIds,
                                                       Integer pageNo, Integer pageSize) {
        if (pageNo == null) {
            pageNo = 0;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        Integer total = spanSnapshotMapper.getTraceCountBySnapshotIds(spanSnapshotIds);
        Integer offset = pageNo * pageSize;
        SnapshotQuery query = SnapshotQuery.builder()
                .userId(userId)
                .snapshotIds(spanSnapshotIds)
                .build();
        List<Trace> data = traceMapper.findTracesBySpanSnapshots(query, offset, pageSize);
        List<String> traceIds = data.stream().map(Trace::getTraceId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(traceIds)) {
            return new TraceListResponse().data(new TracePage(total, pageSize, pageNo, Collections.emptyList()));
        }
        Map<String, TraceFault> traceFaults = traceFaultService.getTraceFaultsByIds(traceIds)
                .stream()
                .collect(Collectors.toMap(TraceFault::getTraceId, Function.identity()));
        List<TraceBrief> traceBriefs = data.stream()
                .map(trace -> convertToTraceBrief(trace, traceFaults))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        TracePage page = new TracePage(total, pageSize, pageNo, traceBriefs);
        return new TraceListResponse().data(page);
    }

    public TraceDetailResponse getTraceDetail(String traceId) {
        Trace trace = traceMapper.findByTraceId(traceId);
        if (trace == null) {
            log.warn("Can't find trace with traceId {}", traceId);
            return new TraceDetailResponse();
        }
        TraceFault traceFault = traceFaultService.getTraceFaultById(traceId);
        if (traceFault == null) {
            log.warn("Can't find trace fault with traceId {}", traceId);
            return new TraceDetailResponse();
        }

        List<TimelineSpanDto> spans = spanService.findTimelineSpansByTraceIdWithLimitation(traceId, CommonConstants.DEFAULT_TRACE_SPAN_LIMIT);
        if (CollectionUtils.isEmpty(spans)) {
            log.warn("Can't find spans with traceId {}", traceId);
            return new TraceDetailResponse();
        }
        TraceDetail traceDetail = new TraceDetail();
        traceDetail.setBrief(convertToTraceBrief(trace, Collections.singletonMap(traceId, traceFault)));
        traceDetail.setSpans(spans.stream().map(span -> buildTimelineSpan(span, traceFault)).collect(Collectors.toList()));
        return new TraceDetailResponse().data(traceDetail);
    }

    private TraceBrief convertToTraceBrief(Trace trace, Map<String, TraceFault> traceFaults) {
        if (trace == null) {
            return null;
        }

        TraceBrief traceBrief = new TraceBrief();
        traceBrief.setTraceId(trace.getTraceId());

        TraceFault traceFault = traceFaults.get(trace.getTraceId());
        if (traceFault != null && StringUtils.isNotBlank(traceFault.getFaultKind())) {
            traceBrief.setStatus(TraceBrief.StatusEnum.fromValue(traceFault.getFaultKind()));
        } else {
            long maxAllowableLatency = parameterService.getLongParameter(SysParameterCodeEnum.MAX_ALLOWABLE_LATENCY, CommonConstants.DEFAULT_MAX_ALLOWABLE_LATENCY);
            // 如果时间超过默认配置，则认为是超时，比如成功但超时了
            if (trace.getDuration() > maxAllowableLatency * 1_000_000) {
                traceBrief.setStatus(TraceBrief.StatusEnum.TIMEOUT);
            } else {
                traceBrief.setStatus(TraceBrief.StatusEnum.SUCCESS);
            }
        }

        traceBrief.setEndpointAddress(trace.getSourceIp());
        traceBrief.setStartTime(trace.getTimestamp().getTime());
        traceBrief.setDuration(trace.getDuration());
        traceBrief.setSpanCount(trace.getSpanCount());

        if (CollectionUtils.isNotEmpty(trace.getPlatforms())) {
            traceBrief.setPlatforms(dictionaryService.getDisplayPairs(trace.getPlatforms()));
        }

        traceBrief.setAppCluster(trace.getAppCluster());
        traceBrief.setInstanceNames(trace.getInstanceNames());

        String firstService = trace.getServiceName();
        if (StringUtils.isNotBlank(trace.getSpanName())) {
            firstService = String.format("%s %s", firstService, trace.getSpanName());
        }
        traceBrief.setFirstService(firstService);

        if (CollectionUtils.isNotEmpty(trace.getServiceNames())) {
            traceBrief.setApplications(dictionaryService.getDisplayPairs(trace.getServiceNames()));
        }

        return traceBrief;
    }

    private TimelineSpan buildTimelineSpan(TimelineSpanDto span, TraceFault traceFault) {
        if (span == null) {
            return null;
        }
        TimelineSpan timelineSpan = new TimelineSpan();
        timelineSpan.setTimestamp(span.getTimestamp().getTime());
        timelineSpan.setTraceId(span.getTraceId());
        timelineSpan.setSpanId(span.getSpanId());
        timelineSpan.setParentSpanId(span.getParentSpanId());
        timelineSpan.setServiceName(span.getServiceName());
        timelineSpan.setSpanName(span.getSpanName());
        timelineSpan.setSpanKind(dictionaryService.getDisplayPair(span.getSpanKind()));
        timelineSpan.setDuration(span.getDuration());
        if (StringUtils.isNotBlank(span.getFaultKind())) {
            timelineSpan.setFaultKind(dictionaryService.getDisplayPair(span.getFaultKind()));
            timelineSpan.setIsCause(span.getSpanId().equals(traceFault.getSpanId()));
        }
        timelineSpan.setApplication(dictionaryService.getDisplayPair(span.getServiceName()));
        timelineSpan.setPlatform(dictionaryService.getDisplayPair(span.getPlatform()));
        timelineSpan.setCluster(span.getCluster());
        return timelineSpan;
    }

    public ServiceMapResponse getServiceMap(String traceId) {
        List<ServiceMapEdgeResult> spans = spanService.findEdgeSpansByTraceId(traceId);
        spans = spans.stream()
                .peek(span -> {
                    if (StringUtils.isBlank(span.getToService())) {
                        span.setToService(span.getServerSoftware());
                    }
                })
                .collect(Collectors.toList());
        return serviceMapService.createServiceMap(spans);
    }
}
