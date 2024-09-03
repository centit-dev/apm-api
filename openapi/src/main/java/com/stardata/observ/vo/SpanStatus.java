package com.stardata.observ.vo;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 1-成功；2-系统失败；3-业务失败
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum SpanStatus {

    SUCCEED(1),

    SYS_FAULT(2),

    BIZ_FAULT(3);

    private Integer value;

    SpanStatus(Integer value) {
        this.value = value;
    }

    @JsonCreator
    public static SpanStatus fromValue(Integer value) {
        for (SpanStatus b : SpanStatus.values()) {
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
