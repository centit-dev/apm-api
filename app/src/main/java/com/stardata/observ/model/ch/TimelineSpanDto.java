package com.stardata.observ.model.ch;

import java.util.Date;

import lombok.Data;

@Data
public class TimelineSpanDto {
    private Date timestamp;
    private String traceId;
    private String spanId;
    private String parentSpanId;
    private String serviceName;
    private String spanName;
    private String spanKind;
    private Long duration;
    private String faultKind;
    private String platform;
    private String cluster;
}
