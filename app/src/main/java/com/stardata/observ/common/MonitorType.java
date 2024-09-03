package com.stardata.observ.common;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MonitorType {
    ACTIVEMQ("activemq"),
    ROCKETMQ("rocketmq"),
    RABBITMQ("rabbitmq"),
    GAUSSDB("gaussdb"),
    ANTDB("antdb"),
    ORACLE("oracle"),
    MEMCACHED("memcached"),
    MYSQL("mysql"),
    REDIS("redis"),
    KAFKA("kafka"),
    JAVA("java"),
    TTSERVER("ttserver"),
    K8S("k8s"),
    NODE("node"),
    SERVICE("service");

    private static final Map<String, MonitorType> values = Stream.of(MonitorType.values())
            .collect(Collectors.toMap(MonitorType::getValue, Function.identity()));
    private final String value;

    MonitorType(String value) {
        this.value = value;
    }

    public static MonitorType fromValue(String value) {
        return values.get(value);
    }

    public String getValue() {
        return value;
    }
}
