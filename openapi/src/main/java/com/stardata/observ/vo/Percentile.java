package com.stardata.observ.vo;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * P50, P75, P90, P99
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum Percentile {

    P50("P50"),

    P75("P75"),

    P90("P90"),

    P99("P99");

    private String value;

    Percentile(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Percentile fromValue(String value) {
        for (Percentile b : Percentile.values()) {
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
