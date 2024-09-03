package com.stardata.observ.domain;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.vo.SpanStatus;
import com.stardata.observ.model.ch.SpanSnapshot;

public class StatusConditionDto {
    AtomicReference<Integer> minRootDuration = new AtomicReference<>();
    AtomicBoolean systemFault = new AtomicBoolean();
    AtomicBoolean businessFault = new AtomicBoolean();

    public static StatusConditionDto build(List<SpanSnapshot> snapshots) {
        StatusConditionDto dto = new StatusConditionDto();
        snapshots.stream()
                .map(SpanSnapshot::getConditions)
                .filter(Objects::nonNull)
                .map(SnapshotCondition::getStatusConditions)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .forEach(condition -> {
                    if (condition.getMinDelay() != null) {
                        if (dto.minRootDuration.get() == null) {
                            dto.minRootDuration.set(condition.getMinDelay().intValue());
                        } else {
                            dto.minRootDuration.set(Math.min(dto.minRootDuration.get(), condition.getMinDelay().intValue()));
                        }
                    } else if (SpanStatus.BIZ_FAULT.getValue().equals(condition.getStatus())) {
                        dto.businessFault.set(true);
                    } else if (SpanStatus.SYS_FAULT.getValue().equals(condition.getStatus())) {
                        dto.systemFault.set(true);
                    }
                });
        return dto;
    }

    public Integer getMinRootDuration() {
        return minRootDuration.get();
    }

    public Boolean getSystemFault() {
        return systemFault.get();
    }

    public Boolean getBusinessFault() {
        return businessFault.get();
    }
}
