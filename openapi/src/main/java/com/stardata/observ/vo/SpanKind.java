package com.stardata.observ.vo;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Span的操作类型：CLIENT、SERVER、INTERNAL、PRODUCER、CONSUMER等。
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum SpanKind {

    CLIENT("SPAN_KIND_CLIENT"),

    SERVER("SPAN_KIND_SERVER"),

    INTERNAL("SPAN_KIND_INTERNAL"),

    PRODUCER("SPAN_KIND_PRODUCER"),

    CONSUMER("SPAN_KIND_CONSUMER");

    private String value;

    SpanKind(String value) {
        this.value = value;
    }

    @JsonCreator
    public static SpanKind fromValue(String value) {
        for (SpanKind b : SpanKind.values()) {
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
