package com.stardata.observ.model.ch;

import com.google.common.collect.Lists;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotStatusCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotTimeRange;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SnapshotConditionTest {
    @Test
    void testAppendWithNewFieldCondition() {
        SnapshotCondition condition = new SnapshotCondition();
        SnapshotCondition newCondition = new SnapshotCondition();
        newCondition.addFieldCondition(new SnapshotFieldCondition("a", "=", "b"));
        SnapshotCondition appended = condition.duplicate().append(newCondition);

        assertThat(
                condition.getFieldConditions(),
                not(hasItem(hasProperty("field", equalTo("a")))));
        assertThat(
                appended.getFieldConditions(),
                hasItem(hasProperty("field", equalTo("a"))));

        appended = appended.append(newCondition);
        assertEquals(1, appended.getFieldConditions().size());
    }

    @Test
    void testAppendWithNewStatusCondition() {
        SnapshotCondition condition = new SnapshotCondition();
        SnapshotCondition newCondition = new SnapshotCondition();
        newCondition.setStatusConditions(Lists.newArrayList(new SnapshotStatusCondition(0, "", 0L)));
        SnapshotCondition appended = condition.duplicate().append(newCondition);

        assertThat(
                condition.getStatusConditions(),
                not(hasItem(hasProperty("status", equalTo(0)))));
        assertThat(
                appended.getStatusConditions(),
                hasItem(hasProperty("status", equalTo(0))));

        appended = appended.append(newCondition);
        assertEquals(1, appended.getStatusConditions().size());
    }

    @Test
    void testAppendWithTimeRange() {
        SnapshotCondition condition = new SnapshotCondition();
        SnapshotCondition newCondition = new SnapshotCondition();
        newCondition.setTimeRange(new SnapshotTimeRange(1L, 2L, 2, 0));
        SnapshotCondition appended = condition.duplicate().append(newCondition);

        assertEquals(1L, appended.getFromTime());
        assertEquals(2L, appended.getToTime());

        appended = appended.append(newCondition);
        assertEquals(1L, appended.getFromTime());
        assertEquals(2L, appended.getToTime());
    }

    @Test
    void testAppendWithTimeRangeWithOverlap() {
        SnapshotCondition condition = new SnapshotCondition();
        condition.setTimeRange(new SnapshotTimeRange(1L, 10L, 2, 0));
        SnapshotCondition newCondition = new SnapshotCondition();
        newCondition.setTimeRange(new SnapshotTimeRange(3L, 12L, 2, 0));
        SnapshotCondition appended = condition.duplicate().append(newCondition);

        assertEquals(3L, appended.getFromTime());
        assertEquals(10L, appended.getToTime());
    }

    @Test
    void testAppendWithDuration() {
        SnapshotCondition condition = new SnapshotCondition();
        SnapshotCondition newCondition = new SnapshotCondition();
        newCondition.setMinDuration(1L);
        newCondition.setMaxDuration(2L);
        SnapshotCondition appended = condition.duplicate().append(newCondition);

        assertNull(condition.getMinDuration());
        assertNull(condition.getMaxDuration());
        assertEquals(1, appended.getMinDuration());
        assertEquals(2, appended.getMaxDuration());

        appended = appended.append(newCondition);
        assertEquals(1, appended.getMinDuration());
        assertEquals(2, appended.getMaxDuration());
    }

    @Test
    void testAppendWithDurationWithOverlap() {
        SnapshotCondition condition = new SnapshotCondition();
        condition.setMinDuration(1L);
        condition.setMaxDuration(10L);
        SnapshotCondition newCondition = new SnapshotCondition();
        newCondition.setMinDuration(3L);
        newCondition.setMaxDuration(12L);
        SnapshotCondition appended = condition.duplicate().append(newCondition);

        assertEquals(3, appended.getMinDuration());
        assertEquals(10, appended.getMaxDuration());
    }
}
