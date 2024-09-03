package com.stardata.observ.model.ch;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class TraceFaultInstanceGroup {
    private String platformName;
    private String appCluster;
    private String instanceName;
    private String serviceName;
    private String spanName;
    private String spanKind;
    private Integer calls;
    private Integer faultCalls;
    private String applicationType;
    private List<Map<String, String>> servers;
    private String node;
}
