package com.stardata.observ.mapper.ch;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.stardata.observ.config.CoreTestConfiguration;
import com.stardata.observ.model.ch.HeatmapDurationRange;
import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotStatusCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotTimeRange;
import com.stardata.observ.model.ch.SpanSnapshot;
import com.stardata.observ.model.ch.TraceFaultInstanceGroup;
import com.stardata.observ.service.SpanSnapshotService;
import com.stardata.observ.vo.SpanStatus;
import com.stardata.observ.vo.TimeRangeType;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled("for integration test")
@ActiveProfiles("container")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = CoreTestConfiguration.class)
public class TraceFaultMapperTest {
    @Autowired
    private SpanSnapshotService snapshotService;

    @Autowired
    private TraceFaultMapper mapper;

    @Test
    void testGroupByAppInstance() {
        SpanSnapshot snapshot = createSnapshot();

        List<TraceFaultInstanceGroup> groups = mapper.groupByAppInstance(buildQuery(snapshot));
        assertThat(groups.size(), greaterThan(0));
        for (TraceFaultInstanceGroup group : groups) {
            assertThat(group.getAppCluster(), not(emptyString()));
            assertThat(group.getSpanKind(), not(emptyString()));
        }
    }

    @Test
    void testGroupByAppInstanceWithoutFaults() {
        SpanSnapshot snapshot = createSnapshot();

        List<TraceFaultInstanceGroup> groups = mapper.groupByAppInstance(buildQuery(snapshot));
        assertEquals(0, groups.size());
    }

    @Test
    public void testGetHeatmapDurationRange() {
        SpanSnapshot snapshot = createSnapshot();

        HeatmapDurationRange range = mapper.getHeatmapDurationRange(buildQuery(snapshot));
        assertThat(range.getMaxDuration(), greaterThan(0L));
    }

    private SpanSnapshot createSnapshot() {
        SnapshotCondition condition = new SnapshotCondition();
        condition.setFieldConditions(Lists.newArrayList(
                new SnapshotFieldCondition("ResourceAttributes['service.platform']", "!=", "")
        ));
        condition.setStatusConditions(Lists.newArrayList(
            new SnapshotStatusCondition(SpanStatus.SUCCEED.getValue(), "", 10L),
            new SnapshotStatusCondition(SpanStatus.BIZ_FAULT.getValue(), "", null),
            new SnapshotStatusCondition(SpanStatus.SYS_FAULT.getValue(), "", null)
        ));
        Instant now = Instant.now();
        condition.setTimeRange(new SnapshotTimeRange(
                now.minus(5, ChronoUnit.HOURS).toEpochMilli(),
                now.toEpochMilli(),
                TimeRangeType.DESIGNATED.getValue(), 0));
        return snapshotService.createSpanSnapshot("1", null, condition);
    }

    private SnapshotQuery buildQuery(SpanSnapshot snapshot) {
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
