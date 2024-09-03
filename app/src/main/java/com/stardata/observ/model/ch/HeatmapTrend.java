package com.stardata.observ.model.ch;

import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-03-03 00:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeatmapTrend {
    private Date timeUnix;
    private Map<Long, Long> businessDurations;
    private Map<Long, Long> systemDurations;
    private Map<Long, Long> successDurations;
}
