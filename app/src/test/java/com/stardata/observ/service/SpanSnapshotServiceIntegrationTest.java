package com.stardata.observ.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import com.google.common.collect.Lists;
import com.stardata.observ.config.CoreTestConfiguration;
import com.stardata.observ.vo.SpanStatus;
import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotStatusCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotTimeRange;
import com.stardata.observ.model.ch.SpanSnapshot;
import com.stardata.observ.vo.TimeRangeType;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled("for integration test")
@ActiveProfiles("container")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = CoreTestConfiguration.class)
public class SpanSnapshotServiceIntegrationTest {
    @Autowired
    private SpanSnapshotService service;

    @Test
    void testCreateSpanSnapshot() {
        SpanSnapshot snapshot = createSnapshot();
        assertNotNull(snapshot.getSnapshotId());

        SpanSnapshot record = service.getSnapshotById(snapshot.getSnapshotId());
        assertNotNull(record);
        assertThat(record.getConditions().getFromTime(), greaterThan(0L));
    }

    @Test
    void testCreateSpanSnapshotWithEmptyFieldConditions() {
        SpanSnapshot snapshot = createSnapshotWithEmptyFieldConditions();
        assertNotNull(snapshot.getSnapshotId());

        SpanSnapshot record = service.getSnapshotById(snapshot.getSnapshotId());
        assertNotNull(record);
        assertThat(record.getConditions().getFromTime(), greaterThan(0L));
    }

    @Test
    void testCreateSpanSnapshotWithTrendingSystemFault() {
        SpanSnapshot snapshot = createSnapshotWithSystemFault();
        assertNotNull(snapshot.getSnapshotId());

        SpanSnapshot record = service.getSnapshotById(snapshot.getSnapshotId());
        assertNotNull(record);
        assertThat(record.getConditions().getFromTime(), greaterThan(0L));
    }

    private SpanSnapshot createSnapshot() {
        SnapshotCondition condition = new SnapshotCondition();
        condition.setFieldConditions(Lists.newArrayList(
                new SnapshotFieldCondition("ResourceAttributes['service.platform']", "!=", "")
        ));
        condition.setTimeRange(buildTimeRange(1));
        return service.createSpanSnapshot("1", null, condition);
    }

    private SpanSnapshot createSnapshotWithEmptyFieldConditions() {
        SnapshotCondition condition = new SnapshotCondition();
        condition.setFieldConditions(Lists.newArrayList());
        condition.setTimeRange(buildTimeRange(1));
        return service.createSpanSnapshot("1", null, condition);
    }

    private SpanSnapshot createSnapshotWithSystemFault() {
        SnapshotCondition condition = new SnapshotCondition();
        condition.setFieldConditions(Collections.emptyList());
        condition.setStatusConditions(Lists.newArrayList(
            new SnapshotStatusCondition(SpanStatus.SYS_FAULT.getValue(), "", 0L)
        ));
        condition.setTimeRange(buildTimeRange(5));
        return service.createSpanSnapshot("1", null, condition);
    }

    private SnapshotTimeRange buildTimeRange(int minutes) {
        Instant now = Instant.now();
        return new SnapshotTimeRange(
                now.minus(minutes, ChronoUnit.MINUTES).toEpochMilli(),
                now.toEpochMilli(),
                TimeRangeType.DESIGNATED.getValue(),
                0);
    }
}
