package com.stardata.observ.common;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-17 14:12
 */
public enum FaultKindEnum {
    UNKNOWN("Unknown"),
    BUSINESS_FAULT("BusinessFault"),
    SYSTEM_FAULT("SystemFault"),
    SUCCEED("");

    private final String value;

    FaultKindEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
