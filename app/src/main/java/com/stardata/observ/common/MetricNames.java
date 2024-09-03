package com.stardata.observ.common;

public enum MetricNames {
    HTTP_SERVER_TOMCAT_REQUEST_COUNT("http.server.tomcat.requestCount"),
    HTTP_SERVER_TOMCAT_SESSIONS_ACTIVE_SESSIONS("http.server.tomcat.sessions.activeSessions"),
    HTTP_SERVER_TOMCAT_THREADS("http.server.tomcat.threads");

    private final String value;

    MetricNames(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
