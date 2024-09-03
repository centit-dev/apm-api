package com.stardata.observ.vo;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 表示哪种时间范围的类型：1-最近时间；2-指定时间段。为空表示不限时间范围
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum TimeRangeType {

    RECENT(1),

    DESIGNATED(2);

    private Integer value;

    TimeRangeType(Integer value) {
        this.value = value;
    }

    @JsonCreator
    public static TimeRangeType fromValue(Integer value) {
        for (TimeRangeType b : TimeRangeType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    @JsonValue
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
