package com.stardata.observ.domain;

import java.util.List;

import com.stardata.observ.model.ch.HeatmapTrend;

import lombok.Data;

@Data
public class HeatmapTrendListDto {
    private List<HeatmapTrend> data;
    private Long fromTime;
    private Long toTime;
    private Long maxRootDuration;
    private Integer granularityX;
    private Integer granularityY;
}
