package com.stardata.observ.vo;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 1-span条件搜索，2-日志条件搜索。
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum QueryForType {

    TRACE(1),

    LOG(2);

    private Integer value;

    QueryForType(Integer value) {
        this.value = value;
    }

    @JsonCreator
    public static QueryForType fromValue(Integer value) {
        for (QueryForType b : QueryForType.values()) {
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
