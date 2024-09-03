package com.stardata.observ.common;

import lombok.Getter;

/**
 * @author Samson Shu
 * @version 1.0
 * @email shush@stardata.top
 * @date 2024/1/2 16:26
 */
@Getter
public enum ResourceAttributeEnum {
    APP_CLUSTER("k8s.deployment.name"),
    // defined in the otel agent
    APP_PLATFORM("service.platform");

    private final String value;

    ResourceAttributeEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
