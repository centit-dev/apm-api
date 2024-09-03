package com.stardata.observ.common;

public enum ValidStatusEnum {

    VALID(1),
    INVALID(0);


    private final int value;

    ValidStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
