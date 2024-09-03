package com.stardata.observ.mapper.ch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.stardata.observ.common.FaultKindEnum;
import com.stardata.observ.domain.FaultTrendRateDto;
import com.stardata.observ.domain.FaultTrendRateDto.Trending;
import com.stardata.observ.domain.HeatmapTrendListDto;
import com.stardata.observ.domain.RangeDto;
import com.stardata.observ.domain.SpanFaultTrendListDto;
import com.stardata.observ.domain.SpanListDto;
import com.stardata.observ.helper.DurationStatisticsDto;
import com.stardata.observ.model.ch.DurationStatistics;
import com.stardata.observ.model.ch.FaultTrend;
import com.stardata.observ.model.ch.FaultTrendRate;
import com.stardata.observ.model.ch.HeatmapDurationRange;
import com.stardata.observ.model.ch.HeatmapTrend;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotStatusCondition;
import com.stardata.observ.model.ch.Span;
import com.stardata.observ.model.ch.SpanAttributeSum;
import com.stardata.observ.model.ch.SpanFaultGroup;
import com.stardata.observ.model.ch.SpanSnapshot;
import com.stardata.observ.model.ch.TraceFault;
import com.stardata.observ.model.ch.TraceFaultExceptionGroup;
import com.stardata.observ.model.ch.TraceFaultInstanceGroup;
import com.stardata.observ.model.ch.TraceFaultTrend;

import lombok.AllArgsConstructor;
import lombok.Builder;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import static com.stardata.observ.utils.TimeWindowUtils.calculateGranularity;
import static com.stardata.observ.utils.TimeWindowUtils.calculateGranularityY;

/**
 * a delegator converts snapshot ids to snapshot related queries
 */
@AllArgsConstructor
@Builder
@Component
public class TraceFaultMapperDelegator {
    private static final Integer DEFAULT_DATA_POINTS = 30;

    private TraceFaultMapper mapper;
    private SnapshotHelper snapshotHelper;

    public List<TraceFault> selectList(Wrapper<TraceFault> queryWrapper) {
        return mapper.selectList(queryWrapper);
    }

    public TraceFault selectOne(Wrapper<TraceFault> queryWrapper) {
        return mapper.selectOne(queryWrapper);
    }

    public List<FaultTrendRate> getFaultTrend(long fromTime, long toTime, int interval) {
        return mapper.getFaultTrend(fromTime, toTime, interval);
    }

    public List<TraceFaultInstanceGroup> groupByAppInstance(List<String> snapshotIds) {
        SnapshotQuery query = snapshotHelper.buildSnapshotQuery(snapshotIds);
        if (query == null) {
            return Collections.emptyList();
        }
        return mapper.groupByAppInstance(query);
    }

    @Nullable
    public SpanFaultTrendListDto getTraceFaultTrend(String snapshotId, Integer granularity) {
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
                .minDuration(snapshot.getConditions().getMinDuration())
                .maxDuration(snapshot.getConditions().getMaxDuration())
                .minRootDuration(snapshot.getConditions().getMinRootDuration())
                .querySystemFault(snapshot.getConditions().getSystemStatusCondition() != null)
                .queryBusinessFault(snapshot.getConditions().getBusinessStatusCondition() != null)
                .useAggregation(snapshot.getConditions().useAggregation())
                .build();
        List<TraceFaultTrend> trends = mapper.getTraceFaultTrend(query, granularity);
        SpanFaultTrendListDto dto = new SpanFaultTrendListDto();
        dto.setTrends(trends);
        dto.setFromTime(fromTime);
        dto.setToTime(toTime);
        dto.setGranularity(granularity);
        return dto;
    }

    @Nullable
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
                .minRootDuration(snapshot.getConditions().getMinRootDuration())
                .querySystemFault(snapshot.getConditions().getSystemStatusCondition() != null)
                .queryBusinessFault(snapshot.getConditions().getBusinessStatusCondition() != null)
                .build();
        HeatmapDurationRange range = mapper.getHeatmapDurationRange(query);
        if (range.getMaxDuration() <= 0) {
            return null;
        }
        int granularityY = calculateGranularityY(0, range.getMaxDuration() / 1_000_000);

        List<HeatmapTrend> data = mapper.getHeatmapTrend(
                query, granularity, granularityY);
        HeatmapTrendListDto dto = new HeatmapTrendListDto();
        dto.setData(data);
        dto.setFromTime(fromTime);
        dto.setToTime(toTime);
        dto.setMaxRootDuration(range.getMaxDuration());
        dto.setGranularityX(granularity);
        dto.setGranularityY(granularityY);
        return dto;
    }

    @Nullable
    public SpanAttributeSum sumAttributesBySnapshot(
            String snapshotId, List<String> resourceAttributeBlacklist, List<String> spanAttributeBlacklist, int limit) {
        if (StringUtils.isEmpty(snapshotId)) {
            return null;
        }
        SpanSnapshot snapshot = snapshotHelper.getSnapshotById(snapshotId);
        if (snapshot == null) {
            return null;
        }
        SnapshotQuery query = SnapshotQuery.builder()
                .snapshotIds(Collections.singletonList(snapshot.getSnapshotId()))
                .snapshotWheres(snapshot.getWhereStatements())
                .minDuration(snapshot.getConditions().getMinDuration())
                .maxDuration(snapshot.getConditions().getMaxDuration())
                .minRootDuration(snapshot.getConditions().getMinRootDuration())
                .querySystemFault(snapshot.getConditions().getSystemStatusCondition() != null)
                .queryBusinessFault(snapshot.getConditions().getBusinessStatusCondition() != null)
                .useAggregation(snapshot.getConditions().useAggregation())
                .build();
        // monkey patching approx_top_k as two of it in a same query produce Cartesian product result
        SpanAttributeSum resourceSum = mapper.sumResourceAttributesBySnapshot(query, resourceAttributeBlacklist, limit);
        SpanAttributeSum spanSum = mapper.sumSpanAttributesBySnapshot(query, spanAttributeBlacklist, limit);
        resourceSum.setSpanSum(spanSum.getSpanSum());
        return resourceSum;
    }

    public List<TraceFault> getTraceFaults(String snapshotId, Integer offset, Integer limit) {
        SpanSnapshot snapshot = snapshotHelper.getSnapshotById(snapshotId);
        if (snapshot == null || snapshot.getConditions() == null || snapshot.getConditions().getTimeRange() == null) {
            return null;
        }

        long fromTime = snapshot.getConditions().getFromTime();
        long toTime = snapshot.getConditions().getToTime();

        SnapshotQuery query = SnapshotQuery.builder()
                .snapshotIds(Collections.singletonList(snapshot.getSnapshotId()))
                .snapshotWheres(snapshot.getWhereStatements())
                .fromTime(fromTime)
                .toTime(toTime)
                .minDuration(snapshot.getConditions().getMinDuration())
                .maxDuration(snapshot.getConditions().getMaxDuration())
                .minRootDuration(snapshot.getConditions().getMinRootDuration())
                .querySystemFault(snapshot.getConditions().getSystemStatusCondition() != null)
                .queryBusinessFault(snapshot.getConditions().getBusinessStatusCondition() != null)
                .build();
        return mapper.getTraceFaults(query, offset, limit);
    }

    @Nullable
    public List<SpanFaultGroup> groupBySnapshotIdAndAttributes(
            String snapshotId, List<String> groupByAttributes) {
        SpanSnapshot snapshot = snapshotHelper.getSnapshotById(snapshotId);
        if (snapshot == null) {
            return null;
        }
        // the query was built with snap conditions
        // but since the data is based on trace faults,
        // all we need is the trace id query.
        SnapshotQuery query = SnapshotQuery.builder()
                .snapshotIds(Collections.singletonList(snapshot.getSnapshotId()))
                .snapshotWheres(Collections.emptyList())
                .minDuration(snapshot.getConditions().getMinDuration())
                .maxDuration(snapshot.getConditions().getMaxDuration())
                .minRootDuration(snapshot.getConditions().getMinRootDuration())
                .querySystemFault(snapshot.getConditions().getSystemStatusCondition() != null)
                .queryBusinessFault(snapshot.getConditions().getBusinessStatusCondition() != null)
                .useAggregation(snapshot.getConditions().useAggregation())
                .build();
        return mapper.groupBySnapshotIdAndAttributes(query, groupByAttributes);
    }

    public Map<String, List<FaultTrend>> getFaultTrend(
            SnapshotStatusCondition businessCondition,
            SnapshotStatusCondition systemCondition,
            long fromTime, long toTime) {
        if (businessCondition == null && systemCondition == null) {
            return Collections.emptyMap();
        }

        int interval = Math.max((int) (toTime - fromTime) / 100 / 1000, 5);
        List<FaultTrendRate> trends = mapper.getFaultTrend(fromTime, toTime, interval);
        Map<FaultTrend, FaultTrendRateDto> faultRates = new HashMap<>();
        BiConsumer<FaultTrendRate, FaultKindEnum> consumer = (trend, kind) -> {
            FaultTrend key = new FaultTrend();
            key.setPlatformName(trend.getPlatformName());
            key.setAppCluster(trend.getAppCluster());
            key.setServiceName(trend.getServiceName());
            key.setSpanName(trend.getSpanName());
            key.setFaultKind(kind.value());
            faultRates.compute(key, (k, rates) -> {
                if (rates == null) {
                    rates = new FaultTrendRateDto();
                }
                return rates.addRate(trend.getBusinessFaultRate());
            });
        };
        trends.forEach(trend -> {
            consumer.accept(trend, FaultKindEnum.BUSINESS_FAULT);
            consumer.accept(trend, FaultKindEnum.SYSTEM_FAULT);
        });

        Map<String, List<FaultTrend>> trendMap = new HashMap<>();
        // monkey-patch: empty list means the fault should be looked up but no specific Platform/AppCluster/Service/Span is required
        if (businessCondition != null && StringUtils.isEmpty(businessCondition.getTrend())) {
            trendMap.put(FaultKindEnum.BUSINESS_FAULT.value(), Collections.emptyList());
        }
        if (systemCondition != null && StringUtils.isEmpty(systemCondition.getTrend())) {
            trendMap.put(FaultKindEnum.SYSTEM_FAULT.value(), Collections.emptyList());
        }
        faultRates.entrySet().stream()
                .filter(entry -> {
                    FaultTrend trend = entry.getKey();
                    FaultTrendRateDto rates = entry.getValue();
                    if (businessCondition != null && trend.getFaultKind().equals(FaultKindEnum.BUSINESS_FAULT.value())) {
                        Trending trending = Trending.fromValue(businessCondition.getTrend());
                        return rates.matchTrending(trending);
                    } else if (systemCondition != null && trend.getFaultKind().equals(FaultKindEnum.SYSTEM_FAULT.value())) {
                        Trending trending = Trending.fromValue(systemCondition.getTrend());
                        return rates.matchTrending(trending);
                    }

                    return false;
                })
                .map(Map.Entry::getKey)
                .forEach(trend -> trendMap.compute(trend.getFaultKind(), (key, faults) -> {
                    if (faults == null) {
                        faults = new ArrayList<>();
                    }
                    faults.add(trend);
                    return faults;
                }));
        return trendMap;
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
        snapshots.forEach(snapshot -> {
            snapshotIds.add(snapshot.getSnapshotId());
            if (StringUtils.isNotBlank(snapshot.getWhereStatement())) {
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
        List<Span> spans = mapper
                .findSpanBySnapshotWhereStatement(query, whereStatement, offset, limit)
                .stream()
                .map(fault -> {
                    Span span = new Span();
                    span.setTraceId(fault.getTraceId());
                    span.setSpanId(fault.getSpanId());
                    span.setParentSpanId(fault.getParentSpanId());
                    span.setTimestamp(fault.getTimestamp());
                    span.setDuration(fault.getDuration());
                    span.setSpanKind(fault.getSpanKind());
                    span.setServiceName(fault.getServiceName());
                    span.setSpanName(fault.getSpanName());
                    span.setResourceAttributes(fault.getResourceAttributes());
                    span.setSpanAttributes(fault.getSpanAttributes());
                    return span;
                })
                .collect(Collectors.toList());
        SpanListDto dto = new SpanListDto();
        if (CollectionUtils.isEmpty(spans)) {
            dto.setSpans(Collections.emptyList());
            dto.setTotal(0L);
            return dto;
        }

        long total = mapper.countSpanBySnapshotWhereStatement(query, whereStatement);
        dto.setSpans(spans);
        dto.setTotal(total);
        return dto;
    }

    @Nullable
    public DurationStatisticsDto calculateDurationStatistics(
            List<String> spanSnapshotIds,
            Long fromTime,
            Long toTime,
            Integer granularity,
            List<BigDecimal> quantiles,
            List<String> durationTypes) {
        List<SpanSnapshot> snapshots = snapshotHelper.getSnapshotsByIds(spanSnapshotIds);
        if (CollectionUtils.isEmpty(snapshots)) {
            return null;
        }
        List<String> snapshotIds = new ArrayList<>();
        List<String> snapshotWheres = new ArrayList<>();
        snapshots.forEach(snapshot -> {
            snapshotIds.add(snapshot.getSnapshotId());
            if (StringUtils.isNotBlank(snapshot.getWhereStatement())) {
                snapshotWheres.add(snapshot.getWhereStatement());
            }
        });

        // find out the intersection of time range
        Map.Entry<Long, Long> timeRange = Stream
                .concat(
                        snapshots.stream().map(snapshot -> Pair.of(
                                snapshot.getStartTime().getTime(),
                                snapshot.getEndTime().getTime())),
                        Stream.of(Pair.of(fromTime, toTime)))
                .filter(entry -> entry != null && entry.getLeft() != null)
                .reduce((a, b) -> Pair.of(
                        Math.max(a.getLeft(), b.getLeft()),
                        Math.min(a.getRight(), b.getRight())))
                .orElse(null);
        if (timeRange == null) {
            return null;
        }
        fromTime = timeRange.getKey();
        toTime = timeRange.getValue();

        // find out the intersection of duration range
        RangeDto durationRange = snapshots.stream()
                .filter(snapshot -> snapshot.getConditions() != null)
                .map(snapshot -> new RangeDto(
                        snapshot.getConditions().getMinDuration(),
                        snapshot.getConditions().getMaxDuration()))
                .reduce((a, b) -> a.intersect(b))
                .orElse(RangeDto.EMPTY);

        granularity = getGranularity(fromTime, toTime, granularity);

        SnapshotQuery query = SnapshotQuery.builder()
                .snapshotIds(snapshotIds)
                .snapshotWheres(snapshotWheres)
                .fromTime(fromTime)
                .toTime(toTime)
                .minDuration(durationRange.getMin())
                .maxDuration(durationRange.getMax())
                .useAggregation(true)
                .build();
        List<DurationStatistics> stats = mapper.calculateDurationStatistics(
                query, granularity, quantiles, durationTypes);
        DurationStatisticsDto dto = new DurationStatisticsDto();
        dto.setStatistics(stats);
        dto.setFromTime(fromTime);
        dto.setToTime(toTime);
        dto.setGranularity(granularity);
        return dto;
    }

    private int getGranularity(long fromTime, long toTime, Integer providedGranularity) {
        if (providedGranularity != null && providedGranularity > 0) {
            return providedGranularity;
        }
        float possibleGranularity = (float) (toTime - fromTime) / DEFAULT_DATA_POINTS / 1000;
        // options: 3600 * 24, 3600, 60, 10
        if (possibleGranularity > 3600 * 24) {
            return 3600 * 24;
        } else if (possibleGranularity > 3600) {
            return 3600;
        } else if (possibleGranularity > 60) {
            return 60;
        } else {
            return 10;
        }
    }

    public List<TraceFaultExceptionGroup> groupExceptionBySnapshotIds(List<String> snapshotIds) {
        SnapshotQuery query = snapshotHelper.buildSnapshotQuery(snapshotIds);
        if (query == null) {
            return Collections.emptyList();
        }
        return mapper.groupExceptionBySnapshotIds(query);
    }
}
