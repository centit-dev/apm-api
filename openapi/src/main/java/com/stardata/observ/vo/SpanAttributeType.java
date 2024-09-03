package com.stardata.observ.vo;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 用于标注该属性是span五类属性的哪一类：基本信息、span属性、resource属性、request属性、response属性。
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum SpanAttributeType {

    BASIC(1),

    SPAN(2),

    RESOURCE(3),

    REQUEST(4),

    RESPONSE(5);

    private Integer value;

    SpanAttributeType(Integer value) {
        this.value = value;
    }

    @JsonCreator
    public static SpanAttributeType fromValue(Integer value) {
        for (SpanAttributeType b : SpanAttributeType.values()) {
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
