package com.stardata.observ.model.ch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-03-02 19:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeatmapDurationRange {
    private Long maxDuration;
    private Long minDuration;
}
