package com.stardata.observ.mapper.ch;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stardata.observ.model.ch.DurationStatistics;
import com.stardata.observ.model.ch.FaultTrendRate;
import com.stardata.observ.model.ch.HeatmapDurationRange;
import com.stardata.observ.model.ch.HeatmapTrend;
import com.stardata.observ.model.ch.SpanAttributeSum;
import com.stardata.observ.model.ch.SpanFaultGroup;
import com.stardata.observ.model.ch.TraceFault;
import com.stardata.observ.model.ch.TraceFaultExceptionGroup;
import com.stardata.observ.model.ch.TraceFaultInstanceGroup;
import com.stardata.observ.model.ch.TraceFaultTrend;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TraceFaultMapper extends BaseMapper<TraceFault> {
    List<FaultTrendRate> getFaultTrend(
            @Param("fromTime") long fromTime,
            @Param("toTime") long toTime,
            @Param("interval") int interval);

    List<TraceFaultInstanceGroup> groupByAppInstance(
            @Param("snapshotQuery") SnapshotQuery query);

    List<TraceFaultTrend> getTraceFaultTrend(
            @Param("snapshotQuery") SnapshotQuery query,
            @Param("granularity") Integer granularity);

    SpanAttributeSum sumResourceAttributesBySnapshot(
            @Param("snapshotQuery") SnapshotQuery query,
            @Param("resourceAttributeBlacklist") List<String> resourceAttributeBlacklist,
            @Param("limit") int limit);

    SpanAttributeSum sumSpanAttributesBySnapshot(
        @Param("snapshotQuery") SnapshotQuery query,
        @Param("spanAttributeBlacklist") List<String> spanAttributeBlacklist,
        @Param("limit") int limit);

    HeatmapDurationRange getHeatmapDurationRange(
            @Param("snapshotQuery") SnapshotQuery query);

    /**
     * Get the heat map data
     * where x is the time for each granularityX seconds
     * and y is the duration for each granularityY milliseconds
     * @param snapshotQuery the snapshot conditions of a list of snapshots
     * @param granularityX the granularity of the time length of each group in seconds
     * @param granularityY the granularity of the duration length of each group in milliseconds
     * @return a list of heat map trend data
     */
    List<HeatmapTrend> getHeatmapTrend(
            @Param("snapshotQuery") SnapshotQuery query,
            @Param("granularityX") Integer granularityX,
            @Param("granularityY") Integer granularityY);

    List<TraceFault> getTraceFaults(
            @Param("snapshotQuery") SnapshotQuery query,
            @Param("offset") Integer offset,
            @Param("limit") Integer limit);

    List<SpanFaultGroup> groupBySnapshotIdAndAttributes(
            @Param("snapshotQuery") SnapshotQuery query,
            @Param("groupByAttributes") List<String> groupByAttributes);

    /**
     * Find spans by snapshot id and where statement
     *
     * @param snapshotQuery the snapshot conditions of a list of snapshots
     * @param whereStatement the additional where statement
     * @param offset the offset of the page
     * @param limit the size of the page
     * @return
     */
    List<TraceFault> findSpanBySnapshotWhereStatement(
        @Param("snapshotQuery") SnapshotQuery query,
        @Param("whereStatement") String whereStatement,
        @Param("offset") Integer offset,
        @Param("limit") Integer limit);

    long countSpanBySnapshotWhereStatement(
            @Param("snapshotQuery") SnapshotQuery query,
            @Param("whereStatement") String whereStatement);

    /**
     * <p>Calculate duration statistics
     * from percentiles and duration types
     * by spans limited with snapshotIds</p>
     * <p>
     * the api is defined in favor of MyBatis ddl
     *
     * @param snapshotQuery   the snapshot conditions of a list of snapshots
     * @param granularity     the granularity of the time length of each group in seconds
     * @param quantiles       the quantiles like [0.5,0.9,0.99]
     * @param durationTypes   the duration types like ["TRACE_DURATION","SELF_DURATION","GAP"]
     * @return a list of duration statistics for each duration type
     */
    List<DurationStatistics> calculateDurationStatistics(
            @Param("snapshotQuery") SnapshotQuery query,
            @Param("granularity") Integer granularity,
            @Param("quantiles") List<BigDecimal> quantiles,
            @Param("durationTypes") List<String> durationTypes);

    List<TraceFaultExceptionGroup> groupExceptionBySnapshotIds(
            @Param("snapshotQuery") SnapshotQuery query);
}
