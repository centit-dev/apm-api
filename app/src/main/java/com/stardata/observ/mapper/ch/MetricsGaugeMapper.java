package com.stardata.observ.mapper.ch;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stardata.observ.model.ch.FaultTrend;
import com.stardata.observ.model.ch.MetricsHistogram;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsGaugeMapper extends BaseMapper<MetricsHistogram> {
    List<FaultTrend> getFaultTrend(
            @Param("fromTime") long fromTime,
            @Param("toTime") long toTime);

    List<Long> getValues(
        @Param("platform") String platform,
        @Param("cluster") String cluster,
        @Param("instanceName") String instanceName,
        @Param("metricName") String metricName,
        @Param("fromTime") Long fromTime,
        @Param("toTime") Long toTime,
        @Param("granularity") Integer granularity);
}
