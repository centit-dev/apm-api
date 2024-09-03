package com.stardata.observ.mapper.ch;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.stardata.observ.model.ch.HeatmapDurationRange;
import com.stardata.observ.model.ch.HeatmapTrend;
import com.stardata.observ.model.ch.ServiceMapEdgeResult;
import com.stardata.observ.model.ch.Span;
import com.stardata.observ.model.ch.SpanExceptionGroup;
import com.stardata.observ.model.ch.SpanMetric;
import com.stardata.observ.model.ch.TimelineSpanDto;
import com.stardata.observ.model.ch.TraceFaultInstanceGroup;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpanMapper extends Mapper<Span> {
    List<TimelineSpanDto> findTimelineSpansByTraceId(@Param("traceId") String traceId, @Param("limit") Integer limit);

    List<ServiceMapEdgeResult> findEdgeSpanByTraceId(@Param("traceId") String traceId);

    Span findSpanByTraceIdAndSpanId(@Param("traceId") String traceId, @Param("spanId") String spanId);

    /**
     * Find spans by snapshot id and where statement
     *
     * @param snapshotQuery the snapshot conditions of a list of snapshots
     * @param whereStatement the additional where statement
     * @param offset the offset of the page
     * @param limit the size of the page
     * @return
     */
    List<Span> findSpanBySnapshotWhereStatement(
            @Param("snapshotQuery") SnapshotQuery query,
            @Param("whereStatement") String whereStatement,
            @Param("offset") Integer offset,
            @Param("limit") Integer limit);

    long countSpanBySnapshotWhereStatement(
            @Param("snapshotQuery") SnapshotQuery query,
            @Param("whereStatement") String whereStatement);

    List<Span> findSpans(
            @Param("resourceConditions") Map<String, String> resourceConditions,
            @Param("spanConditions") Map<String, String> spanConditions,
            @Param("serviceName") String serviceName,
            @Param("spanName") String spanName,
            @Param("fromTime") Long fromTime,
            @Param("toTime") Long toTime,
            @Param("minDuration") Long minDuration,
            @Param("maxDuration") Long maxDuration,
            @Param("offset") Integer offset,
            @Param("limit") Integer limit);

    long countSpans(
            @Param("resourceConditions") Map<String, String> resourceConditions,
            @Param("spanConditions") Map<String, String> spanConditions,
            @Param("serviceName") String serviceName,
            @Param("spanName") String spanName,
            @Param("fromTime") Long fromTime,
            @Param("toTime") Long toTime,
            @Param("minDuration") Long minDuration,
            @Param("maxDuration") Long maxDuration);

    List<String> findServerSoftwaresByServerHostname(
            @Param("platform") String platform,
            @Param("hostname") String hostname,
            @Param("fromTime") Long fromTime,
            @Param("toTime") Long toTime);

    HeatmapDurationRange getHeatmapDurationRange(
            @Param("snapshotQuery") SnapshotQuery query);

    List<HeatmapTrend> getHeatmapTrend(
            @Param("snapshotQuery") SnapshotQuery query,
            @Param("granularityX") Integer granularityX,
            @Param("granularityY") Integer granularityY);

    List<SpanMetric> getSpanMetrics(
            @Param("platform") String platform,
            @Param("cluster") String cluster,
            @Param("instanceName") String instanceName,
            @Param("serviceName") String serviceName,
            @Param("fromTime") long fromTime,
            @Param("toTime") long toTime);

    List<SpanExceptionGroup> findSpanExceptions(
            @Param("snapshotQuery") SnapshotQuery query);

    List<TraceFaultInstanceGroup> groupByAppInstance(
            @Param("snapshotQuery") SnapshotQuery query);
}
