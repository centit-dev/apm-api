package com.stardata.observ.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import com.stardata.observ.domain.HeatmapTrendListDto;
import com.stardata.observ.model.ch.HeatmapTrend;
import com.stardata.observ.vo.Heatmap;
import com.stardata.observ.vo.HeatmapResponse;
import com.stardata.observ.vo.GraphPointData;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeatmapAdaptor {

    public HeatmapResponse adapt(HeatmapTrendListDto dto) {
        if (dto == null) {
            return new HeatmapResponse();
        }
        List<HeatmapTrend> trends = dto.getData();
        log.debug("heatMapTrends: {} ", trends);
        Map<Long, HeatmapTrend> trendMap = trends.stream().collect(Collectors.toMap(
                trend -> trend.getTimeUnix().getTime(),
                Function.identity()));

        List<List<GraphPointData>> data = LongStream
                .range(dto.getFromTime() / 1000 / dto.getGranularityX(), dto.getToTime() / 1000 / dto.getGranularityX() + 1)
                .mapToObj(index -> index * 1000 * dto.getGranularityX())
                .map(time -> {
                    HeatmapTrend trend = trendMap.get(time);
                    return buildYPoints(trend, dto.getMaxRootDuration(), dto.getGranularityY());
                })
                .collect(Collectors.toList());
        return new HeatmapResponse().data(new Heatmap()
                .fromTime(dto.getFromTime())
                .toTime(dto.getToTime())
                .fromDuration(0L)
                .toDuration(dto.getMaxRootDuration())
                .timeGranularity(dto.getGranularityX())
                .durationGranularity(dto.getGranularityY())
                .values(data));
    }

    private List<GraphPointData> buildYPoints(HeatmapTrend trend, long maxRootDuration, int granularityY) {
        return LongStream
                .range(0, maxRootDuration / 1_000_000 / granularityY + 1)
                .mapToObj(index -> index * 1_000_000 * granularityY)
                .map(duration -> {
                    GraphPointData data = new GraphPointData();
                    if (trend == null) {
                        return data
                                .successCount(0L)
                                .systemFaultCount(0L)
                                .businessFaultCount(0L);
                    }

                    if (trend.getSuccessDurations() == null) {
                        data.successCount(0L);
                    } else {
                        data.successCount(trend.getSuccessDurations().getOrDefault(duration, 0L));
                    }

                    if (trend.getSystemDurations() == null) {
                        data.systemFaultCount(0L);
                    } else {
                        data.systemFaultCount(trend.getSystemDurations().getOrDefault(duration, 0L));
                    }

                    if (trend.getBusinessDurations() == null) {
                        data.businessFaultCount(0L);
                    } else {
                        data.businessFaultCount(trend.getBusinessDurations().getOrDefault(duration, 0L));
                    }
                    return data;
                })
                .collect(Collectors.toList());
    }
}
