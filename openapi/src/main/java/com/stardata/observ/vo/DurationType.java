package com.stardata.observ.vo;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 包含1-自身时延，2-gap时延，3-Trace时延
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum DurationType {

    SELF_DURATION("SELF_DURATION"),

    GAP("GAP"),

    TRACE_DURATION("TRACE_DURATION");

    private String value;

    DurationType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static DurationType fromValue(String value) {
        for (DurationType b : DurationType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
