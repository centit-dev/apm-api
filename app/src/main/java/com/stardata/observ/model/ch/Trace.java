package com.stardata.observ.model.ch;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-07 23:55
 */
@Data
public class Trace implements Serializable {
    private static final long serialVersionUID = 551793320214549028L;

    private Date timestamp;
    private String traceId;
    private List<String> platforms;
    private String appCluster;
    private String sourceIp;
    private String serviceName;
    private String spanName;
    private Long duration;
    private List<String> serviceNames;
    private List<String> instanceNames;
    private Integer spanCount;
    private List<String> faultKinds;
}
