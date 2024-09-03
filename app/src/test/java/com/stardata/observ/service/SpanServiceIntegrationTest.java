package com.stardata.observ.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import com.google.common.collect.Lists;
import com.stardata.observ.config.CoreTestConfiguration;
import com.stardata.observ.vo.FieldCondition;
import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotTimeRange;
import com.stardata.observ.model.ch.SpanSnapshot;
import com.stardata.observ.vo.SpanListResponse;
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

@Disabled("for integration test")
@ActiveProfiles("container")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = CoreTestConfiguration.class)
public class SpanServiceIntegrationTest {
    @Autowired
    private SpanSnapshotService snapshotService;

    @Autowired
    private SpanService service;

    @Test
    void testQuerySpanBySnapshots() {
        SpanSnapshot snapshot = createSnapshot();

        FieldCondition condition = new FieldCondition().name("exceptionId").operator("!=").value("");
        SpanListResponse spans = service.findSpans(
            Collections.singletonList(snapshot.getSnapshotId()),
            Collections.singletonList(condition), 0, 10);
        assertThat(spans.getData().getSpans().size(), greaterThan(0));
    }

    private SpanSnapshot createSnapshot() {
        SnapshotCondition condition = new SnapshotCondition();
        condition.setFieldConditions(Lists.newArrayList(
                new SnapshotFieldCondition("ResourceAttributes['service.platform']", "!=", "")
        ));
        Instant now = Instant.now();
        condition.setTimeRange(new SnapshotTimeRange(
                now.minus(1, ChronoUnit.MINUTES).toEpochMilli(),
                now.toEpochMilli(),
                TimeRangeType.DESIGNATED.getValue(),
                0));
        return snapshotService.createSpanSnapshot("1", null, condition);
    }
}
