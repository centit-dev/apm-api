package com.stardata.observ.model.ch;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class DurationStatistics {
    private Date timeUnix;
    private List<Long> traceDurationPercentiles;
    private List<Long> selfDurationPercentiles;
    private List<Long> gapDurationPercentiles;
}
