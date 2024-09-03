package com.stardata.observ.domain;

import java.util.List;

import com.stardata.observ.model.ch.TraceFaultTrend;

import lombok.Data;

@Data
public class SpanFaultTrendListDto {
    private List<TraceFaultTrend> trends;
    private Long fromTime;
    private Long toTime;
    private Integer granularity;
}
