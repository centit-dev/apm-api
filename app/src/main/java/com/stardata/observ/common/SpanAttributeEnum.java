package com.stardata.observ.common;

/**
 * @author Samson Shu
 * @version 1.0
 * @email shush@stardata.top
 * @date 2024/1/2 16:26
 */
public enum SpanAttributeEnum {
    // generated from fault kind processor
    FAULT_KIND("fault.kind"),
    // generated from exception processor
    EXCEPTION_ID("exception.definition.id"),
    EXCEPTION_NAME("exception.definition.name"),
    // generated from app type processor
    SERVER_SOFTWARE("server.software"),
    SERVER_HOST("server.host"),
    SERVER_PORT("server.port"),
    HTTP_REQUEST_BODY("http.request.body"),
    HTTP_RESPONSE_BODY("http.response.body"),
    APP_TYPE("application.type");

    private final String value;

    SpanAttributeEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
