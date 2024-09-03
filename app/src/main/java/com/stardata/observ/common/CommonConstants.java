package com.stardata.observ.common;

/**
 * @author Samson Shu
 * @email shush@stardata.top
 * @date 2020/7/2 14:57
 */
public class CommonConstants {
    public static final Long DEFAULT_MAX_ALLOWABLE_LATENCY = 300L;
    public static final Float DEFAULT_BREAKDOWN_THRESHOLD = 0.1f;
    public static final Float DEFAULT_COMPARABLE_THRESHOLD = 0.1f;
    public static final Integer MAX_SPAN_COUNT = 3000;
    public static final String OTHER_EXCEPTION_CATEGORY = "其它类";
    public static final String OTHER_EXCEPTION_NAME = "其它异常";
    public static final int MIN_SUCCEED_DELAY = 100;
    public static final Integer DEFAULT_TRACE_LIMIT = 100000;
    public static final Integer DEFAULT_TRACE_SPAN_LIMIT = 1000;

    private CommonConstants() {
    }
}
