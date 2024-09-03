package com.stardata.observ.common;

import javax.annotation.Nonnull;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-18 21:12
 */
public enum SysParameterCodeEnum {
    MAX_ALLOWABLE_LATENCY("max_allowable_latency"),
    PERFORMANCE_BREAKDOWN_THRESHOLD("performance_breakdown_threshold"),
    NORMAL_COMPARABLE_THRESHOLD("normal_comparable_threshold"),

    TRACE_LATENCY_SHOW_TREND("trace_latency_show_trend"),
    DATA_TYPE_OPERATORS("data_type_operators"),
    TRACE_RESULT_GROUPING_FIELDS("trace_result_grouping_fields"),
    COMMONALITY_KEY_BLACKLIST("commonality_key_blacklist"),
    METADATA_KEY_BLACKLIST("metadata_key_blacklist"),
    DATA_MASKING_LIST("data_masking_list"),

    MAX_TROUBLESHOOTING_COUNT("max_troubleshooting_count");

    @Nonnull
    private final String value;

    SysParameterCodeEnum(
            @Nonnull
            String value) {
        this.value = value;
    }

    @Nonnull
    public String value() {
        return this.value;
    }
}
