package com.stardata.observ.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.google.common.collect.Lists;
import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotTimeRange;
import com.stardata.observ.vo.TimeRangeType;

public class TestUtil {
    public static SnapshotCondition buildSnapshotCondition() {
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
        return condition;
    }
}
