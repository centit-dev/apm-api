package com.stardata.observ.mapper.ch;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import com.google.common.collect.Lists;
import com.stardata.observ.config.CoreTestConfiguration;
import com.stardata.observ.domain.CommonalityKeyBlacklist;
import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.model.ch.SpanSnapshot;
import com.stardata.observ.service.SpanSnapshotService;
import com.stardata.observ.service.SysParameterService;
import com.stardata.observ.vo.DurationType;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.stardata.observ.config.TestUtil.buildSnapshotCondition;

@Disabled("for integration test")
@ActiveProfiles("container")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = CoreTestConfiguration.class)
public class LargeDataTest {
    @Autowired private SpanSnapshotService snapshotService;
    @Autowired private SpanMapper spanMapper;
    @Autowired private TraceFaultMapper traceFaultMapper;
    @Autowired private SysParameterService sysParameterService;

    private SpanSnapshot snapshot;
    private StopWatch watch;

    @BeforeEach
    void setUp() {
        snapshot = createSnapshot();
        watch = StopWatch.createStarted();
    }

    @AfterEach
    void tearDown() {
        watch.stop();
        System.out.println(watch.getTime());
    }

    // old 1_000_000: timeout
    // new 1_000_000: 2407ms
    @Test
    void testGetSpanFaultTrend() {
        traceFaultMapper.getTraceFaultTrend(buildQuery(), 60);
    }

    // old 1_000_000: timeout
    // new 1_000_000: 1778ms
    @Test
    void testGetHeatmapTrend() {
        traceFaultMapper.getHeatmapTrend(buildQuery(), 10, 50);
    }

    // old 1_000_000: timeout
    // new 1_000_000: 1279ms
    @Test
    void testGetHeatmapDurationRange() {
        traceFaultMapper.getHeatmapDurationRange(buildQuery());
    }

    // old 1_000_000: timeout
    // new 1_000_000: 7090ms
    @Test
    void testSumAttributesBySnapshot() {
        SnapshotQuery query = buildQuery();
        CommonalityKeyBlacklist blacklist = sysParameterService.getCommonalityKeyBlacklist();
        traceFaultMapper.sumResourceAttributesBySnapshot(
                query,
                blacklist.getResourceAttributeBlacklist(),
                20);
        traceFaultMapper.sumSpanAttributesBySnapshot(
                query,
                blacklist.getSpanAttributeBlacklist(),
                20);
    }

    // 1_000_000: 269ms
    @Test
    void testGroupBySnapshotIdAndAttributes() {
        traceFaultMapper.groupBySnapshotIdAndAttributes(
                buildQuery(),
                Lists.newArrayList("ResourceAttributes['service.platform']"));
    }

    // old 1_000_000: 1512ms
    @Test
    void testGroupExceptionIdBySnapshotIds() {
        Instant now = Instant.now();
        SnapshotQuery query = buildQuery();
        query.setFromTime(now.minus(10, ChronoUnit.MINUTES).toEpochMilli());
        query.setToTime(now.toEpochMilli());
        spanMapper.findSpanExceptions(query);
    }

    // 1_000_000: 253ms
    @Test
    void testGroupByAppInstance() {
        traceFaultMapper.groupByAppInstance(buildQuery());
    }

    // old 1_000_000: 1100ms
    // new 1_000_000: 163ms
    @Test
    void testCalculateDurationStatistics() {
        Instant now = Instant.now();
        SnapshotQuery query = buildQuery();
        query.setFromTime(now.minus(10, ChronoUnit.MINUTES).toEpochMilli());
        query.setToTime(now.toEpochMilli());
        traceFaultMapper.calculateDurationStatistics(
                query,
                10,
                Lists.newArrayList(
                        BigDecimal.valueOf(0.5),
                        BigDecimal.valueOf(0.9),
                        BigDecimal.valueOf(0.99)),
                Lists.newArrayList(
                        DurationType.TRACE_DURATION.getValue(),
                        DurationType.SELF_DURATION.getValue(),
                        DurationType.GAP.getValue()));
    }

    // old 1_000_000: 29461ms
    // new 1_000_000: 331ms
    @Test
    void testFindSpanBySnapshotWhereStatement() {
        spanMapper.findSpanBySnapshotWhereStatement(buildQuery(), "1 = 1", 10_000, 100);
    }

    // old 1_000_000: timeout
    // new 1_000_000: 3197ms
    @Test
    void testCountSpanBySnapshotWhereStatement() {
        spanMapper.countSpanBySnapshotWhereStatement(buildQuery(), "1 = 1");
    }

    private SpanSnapshot createSnapshot() {
        SnapshotCondition condition = buildSnapshotCondition();
        return snapshotService.createSpanSnapshot("1", null, condition);
    }

    private SnapshotQuery buildQuery() {
        return SnapshotQuery.builder()
                .snapshotIds(Collections.singletonList(snapshot.getSnapshotId()))
                .snapshotWheres(snapshot.getWhereStatements())
                .minDuration(snapshot.getConditions().getMinDuration())
                .maxDuration(snapshot.getConditions().getMaxDuration())
                .minRootDuration(snapshot.getConditions().getMinRootDuration())
                .querySystemFault(snapshot.getConditions().getSystemStatusCondition() != null)
                .queryBusinessFault(snapshot.getConditions().getBusinessStatusCondition() != null)
                .build();
    }
}
