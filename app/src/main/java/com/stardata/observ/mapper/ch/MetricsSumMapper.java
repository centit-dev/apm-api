package com.stardata.observ.mapper.ch;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stardata.observ.model.ch.Instance;
import com.stardata.observ.model.ch.MetricsSum;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsSumMapper extends BaseMapper<MetricsSum> {
    List<Instance> getRecentInstances(
            @Param("platform") String platform,
            @Param("cluster") String cluster,
            @Param("fromTime") Long fromTime,
            @Param("toTime") Long toTime);

    List<Long> getValues(
            @Param("platform") String platform,
            @Param("cluster") String cluster,
            @Param("instanceName") String instanceName,
            @Param("metricName") String metricName,
            @Param("fromTime") Long fromTime,
            @Param("toTime") Long toTime,
            @Param("granularity") Integer granularity);
}
