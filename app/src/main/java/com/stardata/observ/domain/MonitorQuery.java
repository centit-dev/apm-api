package com.stardata.observ.domain;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

@Data
public class MonitorQuery {
    private static final String GRAFANA_HOSTNAME_PREFIX = "var-hostname";
    private static final String GRAFANA_K8S_PREFIX = "var-k8sPodName";
    private static final String GRAFANA_K8S_NODE_PREFIX = "var-k8sNodeName";
    private static final String GRAFANA_SERVICE_PREFIX = "var-serviceName";
    private static final String GRAFANA_SPAN_PREFIX = "var-spanName";
    private static final String GRAFANA_FROM = "from";
    private static final String GRAFANA_TO = "to";

    private String hostname;
    private String k8sNodeName;
    private String k8sPodName;
    private String serviceName;
    private String spanName;
    private long from;
    private long to;

    public String getQuery() {
        return Stream
                .of(
                        Pair.of(GRAFANA_HOSTNAME_PREFIX, hostname),
                        Pair.of(GRAFANA_K8S_NODE_PREFIX, k8sNodeName),
                        Pair.of(GRAFANA_K8S_PREFIX, k8sPodName),
                        Pair.of(GRAFANA_SERVICE_PREFIX, serviceName),
                        Pair.of(GRAFANA_SPAN_PREFIX, spanName),
                        Pair.of(GRAFANA_FROM, String.valueOf(from)),
                        Pair.of(GRAFANA_TO, String.valueOf(to)))
                .filter(tuple -> StringUtils.isNotBlank(tuple.getRight()))
                .map(tuple -> String.format("%s=%s", tuple.getLeft(), tuple.getRight()))
                .collect(Collectors.joining("&"));
    }
}
