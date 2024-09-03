package com.stardata.observ.model.ch;

import lombok.Data;

@Data
public class SpanMetric {
    private String serviceName;
    private String spanName;
    private Long totalCount;
    private Long successCount;
    private Double durationP90;
}
