package com.stardata.observ.mapper.ch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.stardata.observ.domain.HeatmapTrendListDto;
import com.stardata.observ.domain.RangeDto;
import com.stardata.observ.domain.SpanListDto;
import com.stardata.observ.model.ch.HeatmapDurationRange;
import com.stardata.observ.model.ch.HeatmapTrend;
import com.stardata.observ.model.ch.ServiceMapEdgeResult;
import com.stardata.observ.model.ch.Span;
import com.stardata.observ.model.ch.SpanExceptionGroup;
import com.stardata.observ.model.ch.SpanMetric;
import com.stardata.observ.model.ch.SpanSnapshot;
import com.stardata.observ.model.ch.TimelineSpanDto;

import lombok.AllArgsConstructor;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static com.stardata.observ.utils.TimeWindowUtils.calculateGranularity;
import static com.stardata.observ.utils.TimeWindowUtils.calculateGranularityY;

/**
 * a delegator converts snapshot ids to snapshot related queries
 */
@AllArgsConstructor
@Component
public class SpanMapperDelegator {
    private SpanMapper delegate;
    private SnapshotHelper snapshotHelper;

    public List<TimelineSpanDto> findTimelineSpansByTraceId(String traceId, Integer limit) {
        return delegate.findTimelineSpansByTraceId(traceId, limit);
    }

    public List<ServiceMapEdgeResult> findEdgeSpanByTraceId(String traceId) {
        return delegate.findEdgeSpanByTraceId(traceId);
    }

    public Span findSpanByTraceIdAndSpanId(String traceId, String spanId) {
        return delegate.findSpanByTraceIdAndSpanId(traceId, spanId);
    }

    @Nullable
    public SpanListDto findSpanBySnapshotWhereStatement(
            List<String> spanSnapshotIds, String whereStatement, Integer offset, Integer limit) {
        List<SpanSnapshot> snapshots = snapshotHelper.getSnapshotsByIds(spanSnapshotIds);
        if (CollectionUtils.isEmpty(snapshots)) {
            return null;
        }
        List<String> snapshotIds = new ArrayList<>();
        List<String> snapshotWheres = new ArrayList<>();
        boolean spanOnly = snapshots.stream().noneMatch(snapshot -> "otel_logs".equals(snapshot.getConditions().getSpanOrLogTableName()));
        snapshots.forEach(snapshot -> {
            snapshotIds.add(snapshot.getSnapshotId());
            if (spanOnly && StringUtils.isNotBlank(snapshot.getWhereStatement())) {
                snapshotWheres.add(snapshot.getWhereStatement());
            }
        });

        // find the intersections of the durations
        RangeDto range = snapshots.stream()
                .filter(snapshot -> snapshot.getConditions() != null)
                .map(snapshot -> new RangeDto(
                        snapshot.getConditions().getMinDuration(),
                        snapshot.getConditions().getMaxDuration()))
                .reduce((a, b) -> a.intersect(b))
                .orElse(RangeDto.EMPTY);

        SnapshotQuery query = SnapshotQuery.builder()
                .snapshotIds(snapshotIds)
                .snapshotWheres(snapshotWheres)
                .minDuration(range.getMin())
                .maxDuration(range.getMax())
                .useAggregation(range.getMin() != null || range.getMax() != null)
                .build();
        List<Span> spans = delegate.findSpanBySnapshotWhereStatement(
                query, whereStatement, offset, limit);
        SpanListDto dto = new SpanListDto();
        if (CollectionUtils.isEmpty(spans)) {
            dto.setSpans(Collections.emptyList());
            dto.setTotal(0L);
            return dto;
        }

        long total = delegate.countSpanBySnapshotWhereStatement(
                query, whereStatement);
        dto.setSpans(spans);
        dto.setTotal(total);
        return dto;
    }

    public SpanListDto findSpans(
            Long fromTime, Long toTime,
            Long minDuration, Long maxDuration,
            Map<String, String> resourceConditions, Map<String, String> spanConditions,
            String serviceName, String spanName,
            Integer offset, Integer limit) {
        List<Span> spans = delegate.findSpans(
                resourceConditions, spanConditions,
                serviceName, spanName,
                fromTime, toTime,
                minDuration, maxDuration,
                offset, limit);
        SpanListDto dto = new SpanListDto();
        if (CollectionUtils.isEmpty(spans)) {
            dto.setSpans(Collections.emptyList());
            dto.setTotal(0L);
            return dto;
        }

        long total = delegate.countSpans(
                resourceConditions, spanConditions,
                serviceName, spanName,
                fromTime, toTime,
                minDuration, maxDuration);
        dto.setSpans(spans);
        dto.setTotal(total);
        return dto;
    }

    public List<String> findServerSoftwaresByServerHostname(String platform, String hostname, Long fromTime, Long toTime) {
        return delegate.findServerSoftwaresByServerHostname(platform, hostname, fromTime, toTime);
    }

    public HeatmapTrendListDto getHeatmapTrend(String snapshotId, Integer granularity) {
        SpanSnapshot snapshot = snapshotHelper.getSnapshotById(snapshotId);
        if (snapshot == null || snapshot.getConditions() == null || snapshot.getConditions().getTimeRange() == null) {
            return null;
        }

        long fromTime = snapshot.getConditions().getFromTime();
        long toTime = snapshot.getConditions().getToTime();
        if (granularity == null) {
            granularity = calculateGranularity(fromTime, toTime);
        }

        SnapshotQuery query = SnapshotQuery.builder()
                .snapshotIds(Collections.singletonList(snapshot.getSnapshotId()))
                .snapshotWheres(snapshot.getWhereStatements())
                .fromTime(fromTime)
                .toTime(toTime)
                .minDuration(snapshot.getConditions().getMinDuration())
                .maxDuration(snapshot.getConditions().getMaxDuration())
                .minRootDuration(snapshot.getConditions().getMinRootDuration() == null ? 0 : snapshot.getConditions().getMinRootDuration())
                .querySystemFault(snapshot.getConditions().getSystemStatusCondition() != null)
                .queryBusinessFault(snapshot.getConditions().getBusinessStatusCondition() != null)
                .build();
        HeatmapDurationRange range = delegate.getHeatmapDurationRange(query);
        if (range.getMaxDuration() <= 0) {
            return null;
        }
        int granularityY = calculateGranularityY(0, range.getMaxDuration() / 1_000_000);
        List<HeatmapTrend> data = delegate.getHeatmapTrend(query, granularity, granularityY);
        HeatmapTrendListDto dto = new HeatmapTrendListDto();
        dto.setData(data);
        dto.setFromTime(fromTime);
        dto.setToTime(toTime);
        dto.setMaxRootDuration(range.getMaxDuration());
        dto.setGranularityX(granularity);
        dto.setGranularityY(granularityY);
        return dto;
    }

    public List<SpanMetric> getSpanMetrics(
            String platform, String cluster, String instanceName,
            String serviceName, Long fromTime, Long toTime) {
        return delegate.getSpanMetrics(platform, cluster, instanceName, serviceName, fromTime, toTime);
    }

    public List<SpanExceptionGroup> findSpanExceptions(List<String> spanSnapshotIds) {
        SnapshotQuery query = snapshotHelper.buildSnapshotQuery(spanSnapshotIds);
        if (query == null) {
            return Collections.emptyList();
        }
        return delegate.findSpanExceptions(query);
    }

    public MultiKeyMap<String, List<Map<String, String>>> findServersByAppInstance(List<String> spanSnapshotIds) {
        SnapshotQuery query = snapshotHelper.buildSnapshotQuery(spanSnapshotIds);
        MultiKeyMap<String, List<Map<String, String>>> result = new MultiKeyMap<>();
        if (query == null) {
            return result;
        }
        delegate.groupByAppInstance(query).forEach(group -> {
            String platform = group.getPlatformName();
            String cluster = group.getAppCluster();
            String instanceName = group.getInstanceName();
            result.put(platform, cluster, instanceName, group.getServers());
        });
        return result;
    }
}
