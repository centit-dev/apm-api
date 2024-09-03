package com.stardata.observ.utils;

import com.stardata.observ.vo.TimeRangeType;
import com.stardata.observ.vo.TimeRange;

public class DateUtil {

    private DateUtil() {
    }

    public static TimeRange getActualTimeRange(TimeRange timeRange) {
        if (timeRange.getTimeType() == TimeRangeType.RECENT) {
            long time = System.currentTimeMillis();
            timeRange.setFromTime(time - timeRange.getRecentSeconds() * 1000);
            timeRange.setToTime(time);
        }
        return timeRange;
    }
}
