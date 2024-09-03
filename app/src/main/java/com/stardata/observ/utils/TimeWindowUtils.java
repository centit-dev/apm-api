package com.stardata.observ.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TimeWindowUtils {

    public static final int MAX_X_WINDOWS = 40;
    public static final int MAX_Y_WINDOWS = 25;
    public static final int MULTIPLE_OF = 5;

    /**
     * Calculate the granularity of the time window. Unit is second
     */
    public static int calculateGranularity(long fromTime, long toTime) {
        if (toTime <= fromTime) {
            throw new IllegalArgumentException("toTime must be greater than fromTime.");
        }

        long timeRange = toTime - fromTime;
        return BigDecimal.valueOf(timeRange)
                // ms to s
                .divide(BigDecimal.valueOf(1000))
                // find the granularity that can divide the time range into windowSize
                .divide(BigDecimal.valueOf(MAX_X_WINDOWS), RoundingMode.HALF_UP)
                // make sure the granularity is a multiple of MULTIPLE_OF
                .divide(BigDecimal.valueOf(MULTIPLE_OF), RoundingMode.HALF_UP)
                // and it's at least MULTIPLE_OF
                .max(BigDecimal.ONE)
                .multiply(BigDecimal.valueOf(MULTIPLE_OF))
                .intValue();
    }

    /**
     * Calculate the granularity of the time window. Unit is millisecond
     */
    public static int calculateGranularityY(long start, long end) {
        if (end == 0) {
            end = 100;
        }
        if (end <= start) {
            throw new IllegalArgumentException("end must be greater than start.");
        }

        long timeRange = end - start;
        return BigDecimal.valueOf(timeRange)
                // find the granularity that can divide the time range into windowSize
                .divide(BigDecimal.valueOf(MAX_Y_WINDOWS), RoundingMode.HALF_UP)
                // make sure the granularity is a multiple of MULTIPLE_OF
                .divide(BigDecimal.valueOf(MULTIPLE_OF), RoundingMode.HALF_UP)
                // and it's at least MULTIPLE_OF
                .max(BigDecimal.ONE)
                .multiply(BigDecimal.valueOf(MULTIPLE_OF))
                .intValue();
    }

    public static List<Long> generateTimeWindowStarts(long fromTime, long toTime, int initialGranularity, int maxWindows, int multipleOf) {
        // make suer * N
        int granularity = adjustGranularityToMultiple(initialGranularity, multipleOf);
        while (!isWindowCountAcceptable(fromTime, toTime, granularity, maxWindows)) {
            granularity += multipleOf;
        }
        List<Long> windowStarts = new ArrayList<>();
        for (long start = fromTime; start < toTime; start += granularity) {
            windowStarts.add(start);
        }
        return windowStarts;
    }

    private static boolean isWindowCountAcceptable(long fromTime, long toTime, int granularity, int maxWindows) {
        int windowCount = (int) Math.ceil((double) (toTime - fromTime) / granularity);
        return windowCount <= maxWindows;
    }

    private static int adjustGranularityToMultiple(int granularity, int multipleOf) {
        if (multipleOf <= 0) {
            throw new IllegalArgumentException("Multiple of must be greater than 0");
        }
        return (int) (Math.ceil((double) granularity / multipleOf) * multipleOf);
    }
}
