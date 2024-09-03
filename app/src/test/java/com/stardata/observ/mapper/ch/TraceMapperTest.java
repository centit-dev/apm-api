package com.stardata.observ.mapper.ch;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.stardata.observ.config.CoreTestConfiguration;
import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotTimeRange;
import com.stardata.observ.model.ch.SpanSnapshot;
import com.stardata.observ.model.ch.Trace;
import com.stardata.observ.service.SpanSnapshotService;
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
public class TraceMapperTest {
    @Autowired
    private SpanSnapshotService snapshotService;

    @Autowired
    private TraceMapper mapper;

    @Test
    void testFindTracesBySpanSnapshots() {
        SpanSnapshot snapshot = createSnapshot();

        List<Trace> traces = mapper.findTracesBySpanSnapshots(buildQuery(snapshot), 0, 10);
        assertThat(traces.size(), greaterThan(0));
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
