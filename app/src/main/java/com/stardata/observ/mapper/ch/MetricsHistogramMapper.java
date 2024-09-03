package com.stardata.observ.mapper.ch;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stardata.observ.model.ch.MetricsHistogram;
import com.stardata.observ.model.ch.ServiceMapEdgeResult;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsHistogramMapper extends BaseMapper<MetricsHistogram> {
    List<ServiceMapEdgeResult> getServiceMapEdges(
            @Param("fromTime") long fromTime,
            @Param("toTime") long toTime);
}
