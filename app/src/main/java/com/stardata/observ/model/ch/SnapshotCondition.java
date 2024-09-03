package com.stardata.observ.model.ch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.stardata.observ.vo.SpanStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SnapshotCondition {
    private String spanOrLogTableName;
    private List<SnapshotFieldCondition> fieldConditions;
    private List<SnapshotStatusCondition> statusConditions;
    private SnapshotTimeRange timeRange;
    private Long minDuration;
    private Long maxDuration;

    public long getFromTime() {
        if (timeRange == null) {
            return 0L;
        }
        return timeRange.getFromTime() != null ? timeRange.getFromTime() : 0L;
    }

    public long getToTime() {
        if (timeRange == null) {
            return 0L;
        }
        return timeRange.getToTime() != null ? timeRange.getToTime() : 0L;
    }

    public SnapshotCondition append(SnapshotCondition condition) {
        spanOrLogTableName = condition.getSpanOrLogTableName();
        if (condition.getFieldConditions() != null) {
            if (fieldConditions == null) {
                fieldConditions = new ArrayList<>();
            }
            Set<String> keys = new HashSet<>();
            for (SnapshotFieldCondition fieldCondition : fieldConditions) {
                keys.add(fieldCondition.getField());
            }
            for (SnapshotFieldCondition fieldCondition : condition.getFieldConditions()) {
                if (!keys.contains(fieldCondition.getField())) {
                    fieldConditions.add(fieldCondition);
                }
            }
        }
        if (condition.getStatusConditions() != null) {
            if (statusConditions == null) {
                statusConditions = new ArrayList<>();
            }
            Set<Integer> keys = new HashSet<>();
            for (SnapshotStatusCondition statusCondition : statusConditions) {
                keys.add(statusCondition.getStatus());
            }
            for (SnapshotStatusCondition statusCondition : condition.getStatusConditions()) {
                if (!keys.contains(statusCondition.getStatus())) {
                    statusConditions.add(statusCondition);
                }
            }
        }
        if (condition.getTimeRange() != null) {
            if (timeRange == null) {
                timeRange = new SnapshotTimeRange(null, null, 0, null);
            }
            timeRange.append(condition.getTimeRange());
        }
        if (condition.getMinDuration() != null) {
            minDuration = minDuration != null ? Math.max(minDuration, condition.getMinDuration()) : condition.getMinDuration();
        }
        if (condition.getMaxDuration() != null) {
            maxDuration = maxDuration != null ? Math.min(maxDuration, condition.getMaxDuration()) : condition.getMaxDuration();
        }
        return this;
    }

    public void addFieldCondition(SnapshotFieldCondition condition) {
        if (fieldConditions == null) {
            fieldConditions = new ArrayList<>();
        }
        fieldConditions.add(condition);
    }

    public SnapshotCondition duplicate() {
        SnapshotCondition condition = new SnapshotCondition();
        if (fieldConditions != null) {
            condition.setFieldConditions(new ArrayList<>(fieldConditions));
        }
        if (statusConditions != null) {
            condition.setStatusConditions(new ArrayList<>(statusConditions));
        }
        if (timeRange != null) {
            condition.setTimeRange(new SnapshotTimeRange(
                    timeRange.getFromTime(),
                    timeRange.getToTime(),
                    timeRange.getTimeType(),
                    timeRange.getRecentSeconds()));
        }
        condition.setMaxDuration(maxDuration);
        condition.setMinDuration(minDuration);
        return condition;
    }

    public Integer getMinRootDuration() {
        SnapshotStatusCondition condition = getSuccessStatusCondition();
        if (condition != null && condition.getMinDelay() != null) {
            return condition.getMinDelay().intValue();
        }
        return null;
    }

    public SnapshotStatusCondition getSuccessStatusCondition() {
        return getStatusCondition(SpanStatus.SUCCEED.getValue());
    }

    public SnapshotStatusCondition getBusinessStatusCondition() {
        return getStatusCondition(SpanStatus.BIZ_FAULT.getValue());
    }

    public SnapshotStatusCondition getSystemStatusCondition() {
        return getStatusCondition(SpanStatus.SYS_FAULT.getValue());
    }

    private SnapshotStatusCondition getStatusCondition(int status) {
        if (statusConditions == null) {
            return null;
        }
        for (SnapshotStatusCondition condition : statusConditions) {
            if (condition.getStatus().equals(status)) {
                return condition;
            }
        }
        return null;
    }

    // TODO monkey-patch to be true if duration queries are provided
    public boolean useAggregation() {
        return getMinDuration() != null || getMaxDuration() != null;
    }

    @Data
    @AllArgsConstructor
    public static class SnapshotFieldCondition {
        private String field;
        private String operator;
        private String value;
    }

    @Data
    @AllArgsConstructor
    public static class SnapshotStatusCondition {
        private Integer status;
        private String trend;
        private Long minDelay;
    }

    @Data
    @AllArgsConstructor
    public static class SnapshotTimeRange {
        private Long fromTime;
        private Long toTime;
        private int timeType;
        private Integer recentSeconds;

        public SnapshotTimeRange append(SnapshotTimeRange range) {
            if (range == null) {
                return this;
            }

            if (range.getFromTime() != null) {
                fromTime = fromTime != null ? Math.max(fromTime, range.getFromTime()) : range.getFromTime();
            }
            if (range.getToTime() != null) {
                toTime = toTime != null ? Math.min(toTime, range.getToTime()) : range.getToTime();
            }
            if (range.getTimeType() != 0) {
                timeType = range.getTimeType();
            }
            if (range.getRecentSeconds() != null) {
                recentSeconds = recentSeconds != null ? Math.min(recentSeconds, range.getRecentSeconds()) : range.getRecentSeconds();
            }
            return this;
        }
    }
}
