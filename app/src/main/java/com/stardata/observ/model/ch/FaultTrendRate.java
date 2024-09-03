package com.stardata.observ.model.ch;

import java.util.Date;

import lombok.Data;

@Data
public class FaultTrendRate {
    private Date timeStep;
    private String platformName;
    private String appCluster;
    private String serviceName;
    private String spanName;
    private Float businessFaultRate;
    private Float systemFaultRate;
}
