package com.stardata.observ.model.ch;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-02-28 21:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraceFaultTrend {
    private Date timeUnix;
    private Long businessFaultCount = 0L;
    private Long systemFaultCount = 0L;
    private Long successCount = 0L;
}
