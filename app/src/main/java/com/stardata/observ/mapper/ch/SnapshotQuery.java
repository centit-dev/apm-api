package com.stardata.observ.mapper.ch;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SnapshotQuery {
    private String userId;
    private List<String> snapshotIds;
    private List<String> snapshotWheres;
    private Long fromTime;
    private Long toTime;
    private Long minDuration;
    private Long maxDuration;
    private Integer minRootDuration;
    @Builder.Default
    private boolean querySystemFault = true;
    @Builder.Default
    private boolean queryBusinessFault = true;
    private boolean useAggregation;
}
