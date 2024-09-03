package com.stardata.observ.model.ch;

import lombok.Data;

@Data
public class FaultTrend {
    private String platformName;
    private String appCluster;
    private String serviceName;
    private String spanName;
    private String faultKind;
}
