package com.stardata.observ.mapper.ch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.stardata.observ.common.FaultKindEnum;
import com.stardata.observ.domain.FaultTrendRateDto;
import com.stardata.observ.model.ch.FaultTrend;
import com.stardata.observ.model.ch.FaultTrendRate;
import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.vo.SpanStatus;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TraceFaultMapperDelegatorTest {
    @Mock
    private TraceFaultMapper mapper;

    TraceFaultMapperDelegator delegator;

    @BeforeEach
    void setUp() {
        delegator = TraceFaultMapperDelegator.builder().mapper(mapper).build();
    }

    @Test
    void testGetFaultTrendWithNoFaults() {
        SnapshotCondition condition = buildCondition(null, null);
        Map<String, List<FaultTrend>> trends = delegator.getFaultTrend(
                condition.getBusinessStatusCondition(),
                condition.getSystemStatusCondition(),
                 0L, 0L);
        assertEquals(0, trends.size());
    }

    @Test
    void testGetFaultTrendWithNoTrends() {
        when(delegator.getFaultTrend(anyLong(), anyLong(), anyInt()))
                .thenReturn(buildRates());

        SnapshotCondition condition = buildCondition("", "");
        Map<String, List<FaultTrend>> trends = delegator.getFaultTrend(
                condition.getBusinessStatusCondition(),
                condition.getSystemStatusCondition(),
                0L, 0L);
        assertEquals(2, trends.size());
        assertThat(trends.get(FaultKindEnum.BUSINESS_FAULT.value()), empty());;
        assertThat(trends.get(FaultKindEnum.SYSTEM_FAULT.value()), empty());;
    }

    @Test
    void testGetFaultTrendWithBusinessUpTrending() {
        when(delegator.getFaultTrend(anyLong(), anyLong(), anyInt()))
                .thenReturn(buildRates());

        SnapshotCondition condition = buildCondition(FaultTrendRateDto.Trending.UP.getValue(), "");
        Map<String, List<FaultTrend>> trends = delegator.getFaultTrend(
                condition.getBusinessStatusCondition(),
                condition.getSystemStatusCondition(),
                0L, 0L);
        assertEquals(2, trends.size());
        assertEquals(3, trends.get(FaultKindEnum.BUSINESS_FAULT.value()).size());
        assertThat(trends.get(FaultKindEnum.SYSTEM_FAULT.value()), empty());;
    }

    @Test
    void testGetFaultTrendWithSystemDownTrending() {
        when(delegator.getFaultTrend(anyLong(), anyLong(), anyInt()))
                .thenReturn(buildRates());

        SnapshotCondition condition = buildCondition("", FaultTrendRateDto.Trending.DOWN.getValue());
        Map<String, List<FaultTrend>> trends = delegator.getFaultTrend(
                condition.getBusinessStatusCondition(),
                condition.getSystemStatusCondition(),
                0L, 0L);
        assertEquals(2, trends.size());
        assertThat(trends.get(FaultKindEnum.BUSINESS_FAULT.value()), empty());
        assertEquals(3, trends.get(FaultKindEnum.SYSTEM_FAULT.value()).size());
    }

    private List<FaultTrendRate> buildRates() {
        return IntStream.range(0, 100)
                .mapToObj(index -> index)
                .flatMap(index -> Stream
                        .<Pair<FaultTrendRateDto.Trending, FaultTrendRateDto.Trending>>of(
                                Pair.of(FaultTrendRateDto.Trending.UP, FaultTrendRateDto.Trending.UP),
                                Pair.of(FaultTrendRateDto.Trending.UP, FaultTrendRateDto.Trending.DOWN),
                                Pair.of(FaultTrendRateDto.Trending.UP, null),
                                Pair.of(FaultTrendRateDto.Trending.DOWN, FaultTrendRateDto.Trending.UP),
                                Pair.of(FaultTrendRateDto.Trending.DOWN, FaultTrendRateDto.Trending.DOWN),
                                Pair.of(FaultTrendRateDto.Trending.DOWN, null),
                                Pair.of(null, FaultTrendRateDto.Trending.UP),
                                Pair.of(null, FaultTrendRateDto.Trending.DOWN),
                                Pair.of(null, null))
                        .map(pair -> {
                            FaultTrendRate rate = new FaultTrendRate();
                            rate.setPlatformName(String.format("platform-%s-%s", pair.getLeft(), pair.getRight()));
                            rate.setAppCluster(String.format("cluster-%s-%s", pair.getLeft(), pair.getRight()));
                            rate.setServiceName(String.format("service-%s-%s", pair.getLeft(), pair.getRight()));
                            rate.setSpanName(String.format("span-%s-%s", pair.getLeft(), pair.getRight()));
                            rate.setBusinessFaultRate(getRate(index, pair.getLeft()));
                            rate.setSystemFaultRate(getRate(index, pair.getRight()));
                            return rate;
                        }))
                .collect(Collectors.toList());
    }

    private float getRate(Integer index, FaultTrendRateDto.Trending trending) {
        if (trending == null) {
            return 0.5f;
        }
        return trending == FaultTrendRateDto.Trending.UP ? index / 100f : (100 - index) / 100f;
    }

    private SnapshotCondition buildCondition(String businessTrend, String systemTrend) {
        SnapshotCondition condition = new SnapshotCondition();
        condition.setStatusConditions(new ArrayList<>());
        if (businessTrend != null) {
            SnapshotCondition.SnapshotStatusCondition cond = new SnapshotCondition.SnapshotStatusCondition(SpanStatus.BIZ_FAULT.getValue(), businessTrend, null);
            condition.getStatusConditions().add(cond);
        }
        if (systemTrend != null) {
            SnapshotCondition.SnapshotStatusCondition cond = new SnapshotCondition.SnapshotStatusCondition(SpanStatus.SYS_FAULT.getValue(), systemTrend, null);
            condition.getStatusConditions().add(cond);
        }
        return condition;
    }
}
