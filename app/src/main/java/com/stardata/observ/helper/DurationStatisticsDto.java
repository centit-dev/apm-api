package com.stardata.observ.helper;

import java.util.List;

import com.stardata.observ.model.ch.DurationStatistics;

import lombok.Data;

@Data
public class DurationStatisticsDto {
    private List<DurationStatistics> statistics;
    private Long fromTime;
    private Long toTime;
    private Integer granularity;
}
