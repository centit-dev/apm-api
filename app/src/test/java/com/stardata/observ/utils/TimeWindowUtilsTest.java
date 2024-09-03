package com.stardata.observ.utils;

import org.junit.jupiter.api.Test;

import static com.stardata.observ.utils.TimeWindowUtils.calculateGranularity;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeWindowUtilsTest {
    @Test
    void testCalculateGranularity() {
        int granularity = calculateGranularity(1000, 1_000_000);
        assertEquals(25, granularity);

        granularity = calculateGranularity(1000, 600_000);
        assertEquals(15, granularity);

        granularity = calculateGranularity(1000, 10_000_000);
        assertEquals(250, granularity);

        granularity = calculateGranularity(1000, 100_000);
        assertEquals(5, granularity);
    }
}
