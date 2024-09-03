package com.stardata.observ.helper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import com.google.common.collect.Lists;
import com.stardata.observ.config.CoreTestConfiguration;
import com.stardata.observ.domain.SpanListDto;
import com.stardata.observ.mapper.ch.SpanMapperDelegator;
import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotTimeRange;
import com.stardata.observ.model.ch.Span;
import com.stardata.observ.model.ch.SpanSnapshot;
import com.stardata.observ.service.SpanSnapshotService;
import com.stardata.observ.vo.TimeRangeType;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Disabled("for integration test")
@ActiveProfiles("container")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = CoreTestConfiguration.class)
public class CHWhereHelperTest {
    @Autowired private SpanSnapshotService snapshotService;
    @Autowired private SpanMapperDelegator spanMapper;
    @Autowired private CHWhereHelper whereHelper;

    private SpanSnapshot snapshot;
    private Span sample;
    private final String stringField = "ResourceAttributes['host.name']";
    private final String numberField = "SpanAttributes['thread.id']";
    private final String durationField = "Duration";

    @BeforeEach
    void setUp() {
        snapshot = createSnapshot();

        // find a sample
        SpanListDto spans = spanMapper.findSpanBySnapshotWhereStatement(
            Collections.singletonList(snapshot.getSnapshotId()),
            "", 0, 1);
        assertNotNull(spans);
        sample = spans.getSpans().get(0);
    }

    @Test
    void testGenerateWhereStringFromSnapshotWithString() {
        String value = sample.getResourceAttributes().get("host.name");

        // Equal
        Span actual = findByWhere(stringField, "=", value);
        assertEquals(value, actual.getResourceAttributes().get("host.name"));

        // NotEqual
        actual = findByWhere(stringField, "!=", value);
        assertNotEquals(value, actual.getResourceAttributes().get("host.name"));

        // StartsWith
        actual = findByWhere(stringField, "startsWith", value.substring(0, 3));
        assertEquals(value, actual.getResourceAttributes().get("host.name"));

        // NotStartWith
        actual = findByWhere(stringField, "notStartWith", value.substring(0, 3));
        assertNull(actual);

        // EndsWith
        actual = findByWhere(stringField, "endsWith", value.substring(0, 3));
        assertNull(actual);

        // Exists
        actual = findByWhere(stringField, "exists", "");
        assertThat(actual.getResourceAttributes(), hasKey("host.name"));

        // NotExist
        actual = findByWhere(stringField, "notExist", "");
        assertNull(actual);

        // Contains
        actual = findByWhere(stringField, "contains", value.substring(0, 3));
        assertEquals(value, actual.getResourceAttributes().get("host.name"));

        // NotContain
        actual = findByWhere(stringField, "notContain", value.substring(0, 3));
        assertNull(actual);

        // In
        actual = findByWhere(stringField, "in", "['" + value + "']");
        assertEquals(value, actual.getResourceAttributes().get("host.name"));

        // NotIn
        actual = findByWhere(stringField, "notIn", "['" + value + "']");
        assertNotEquals(value, actual.getResourceAttributes().get("host.name"));
    }

    @Test
    void testGenerateWhereStringFromSnapshotWithNumber() {
        String valueString = sample.getSpanAttributes().get("thread.id");
        long value = Long.parseLong(valueString);

        // Equal
        Span actual = findByWhere(numberField, "=", valueString);
        assertEquals(valueString, actual.getSpanAttributes().get("thread.id"));

        // GreaterThan, not supported yet
        actual = findByWhere(numberField, ">", valueString);
        // assertNotEquals(valueString, actual.getSpanAttributes().get("thread.id"));
        assertNotNull(actual);

        // GreaterThanOrEqual, not supported yet
        actual = findByWhere(numberField, ">=", String.valueOf(value + 1));
        // assertNotEquals(valueString, actual.getSpanAttributes().get("thread.id"));
        assertNotNull(actual);

        // LessThan, not supported yet
        actual = findByWhere(numberField, "<", valueString);
        // assertNotEquals(valueString, actual.getSpanAttributes().get("thread.id"));
        assertNotNull(actual);

        // LessThanOrEqual, not supported yet
        actual = findByWhere(numberField, "<=", String.valueOf(value - 1));
        // assertEquals(valueString, actual.getSpanAttributes().get("thread.id"));
        assertNotNull(actual);

        // NotEqual, not supported yet
        actual = findByWhere(numberField, "!=", valueString);
        // assertNotEquals(valueString, actual.getSpanAttributes().get("thread.id"));
        assertNotNull(actual);

        // Exists
        actual = findByWhere(numberField, "exists", "");
        assertThat(actual.getSpanAttributes(), hasKey("thread.id"));

        // NotExist
        actual = findByWhere(numberField, "notExist", "");
        assertNull(actual);

        // In
        actual = findByWhere(numberField, "in", "[" + value + "]");
        assertEquals(valueString, actual.getSpanAttributes().get("thread.id"));

        // NotIn
        actual = findByWhere(numberField, "notIn", "[" + value + "]");
        assertNotEquals(valueString, actual.getSpanAttributes().get("thread.id"));
    }

    @Test
    void testGenerateWhereStringFromSnapshotWithDuration() {
        Long value = sample.getDuration();

        // Equal
        Span actual = findByWhere(durationField, "=", String.valueOf(value));
        assertEquals(value, actual.getDuration());

        // GreaterThan
        actual = findByWhere(durationField, ">", String.valueOf(value));
        assertNotEquals(value, actual.getDuration());

        // GreaterThanOrEqual
        actual = findByWhere(durationField, ">=", String.valueOf(value + 1));
        assertNotEquals(value, actual.getDuration());

        // LessThan
        actual = findByWhere(durationField, "<", String.valueOf(value));
        assertNotEquals(value, actual.getDuration());

        // LessThanOrEqual
        actual = findByWhere(durationField, "<=", String.valueOf(value - 1));
        assertNotEquals(value, actual.getDuration());

        // NotEqual
        actual = findByWhere(durationField, "!=", String.valueOf(value));
        assertNotEquals(value, actual.getDuration());

        // Exists, not supported
        actual = findByWhere(durationField, "exists", "");
        assertNotNull(actual);

        // NotExist, not supported
        actual = findByWhere(durationField, "notExist", "");
        assertNotNull(actual);

        // In
        actual = findByWhere(durationField, "in", "[" + value + "]");
        assertEquals(value, actual.getDuration());

        // NotIn
        actual = findByWhere(durationField, "notIn", "[" + value + "]");
        assertNotEquals(value, actual.getDuration());
    }

    private Span findByWhere(String field, String operator, String value) {
        SnapshotFieldCondition condition = new SnapshotFieldCondition(field, operator, value);
        String where = whereHelper.generateWhereStringFromSnapshot(Collections.singletonList(condition));
        SpanListDto spans = spanMapper.findSpanBySnapshotWhereStatement(
            Collections.singletonList(snapshot.getSnapshotId()),
            where, 0, 1);
        if (spans == null || CollectionUtils.isEmpty(spans.getSpans())) {
            return null;
        }
        return spans.getSpans().get(0);
    }

    private SpanSnapshot createSnapshot() {
        SnapshotCondition condition = new SnapshotCondition();
        condition.setFieldConditions(Lists.newArrayList(
                new SnapshotFieldCondition("ResourceAttributes['service.platform']", "!=", "")
        ));
        Instant now = Instant.now();
        condition.setTimeRange(new SnapshotTimeRange(
                now.minus(1, ChronoUnit.HOURS).toEpochMilli(),
                now.toEpochMilli(),
                TimeRangeType.DESIGNATED.getValue(), 0));
        return snapshotService.createSpanSnapshot("1", null, condition);
    }
}
