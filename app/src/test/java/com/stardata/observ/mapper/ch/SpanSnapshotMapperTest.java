package com.stardata.observ.mapper.ch;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.stardata.observ.config.CoreTestConfiguration;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotStatusCondition;
import com.stardata.observ.vo.SpanStatus;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Disabled("for integration test")
@ActiveProfiles("container")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = CoreTestConfiguration.class)
public class SpanSnapshotMapperTest {
    @Autowired private SpanSnapshotMapper mapper;

    @Test
    void testCreateSnapshotByConditionsWithWhereStatement() {
        Instant toTime = Instant.now();
        Instant fromTime = toTime.minus(1, ChronoUnit.HOURS);
        mapper.createSnapshotByConditionsWithWhereStatement(
            UUID.randomUUID().toString(),
            null,
            fromTime.toEpochMilli(),
            toTime.toEpochMilli(),
            null, null,
            null, "''",
            null,
            null,
            null,
            null,
            true,
            new SnapshotStatusCondition(SpanStatus.SUCCEED.getValue(), "", 100L),
            null,
            null);
    }
}
