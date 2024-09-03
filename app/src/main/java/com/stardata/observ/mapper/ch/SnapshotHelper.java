package com.stardata.observ.mapper.ch;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stardata.observ.domain.RangeDto;
import com.stardata.observ.domain.StatusConditionDto;
import com.stardata.observ.model.ch.SpanSnapshot;

import lombok.AllArgsConstructor;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SnapshotHelper {
    private SpanSnapshotMapper mapper;

    SpanSnapshot getSnapshotById(String id) {
        QueryWrapper<SpanSnapshot> wrapper = new QueryWrapper<>();
        wrapper.eq("SnapshotId", id);
        wrapper.select("SnapshotId", "UserId", "Conditions", "StartTime", "EndTime", "WhereStatement", "Timestamp");
        return mapper.selectOne(wrapper);
    }

    List<SpanSnapshot> getSnapshotsByIds(Collection<String> ids) {
        QueryWrapper<SpanSnapshot> wrapper = new QueryWrapper<>();
        wrapper.in("SnapshotId", ids);
        wrapper.select("SnapshotId", "UserId", "Conditions", "StartTime", "EndTime", "WhereStatement", "Timestamp");
        return mapper.selectList(wrapper);
    }

    Long countTracesInSnapshot(String snapshotId) {
        QueryWrapper<SpanSnapshot> wrapper = new QueryWrapper<>();
        wrapper.eq("SnapshotId", snapshotId);
        return mapper.selectCount(wrapper);
    }

    @Nullable
    public SnapshotQuery buildSnapshotQuery(List<String> snapshotIds) {
        if (CollectionUtils.isEmpty(snapshotIds)) {
            return null;
        }
        List<SpanSnapshot> snapshots = getSnapshotsByIds(snapshotIds);
        if (CollectionUtils.isEmpty(snapshots)) {
            return null;
        }
        StatusConditionDto dto = StatusConditionDto.build(snapshots);
        RangeDto durationRange = snapshots.stream()
                .filter(snapshot -> snapshot.getConditions() != null)
                .map(snapshot -> new RangeDto(
                        snapshot.getConditions().getMinDuration(),
                        snapshot.getConditions().getMaxDuration()))
                .reduce((a, b) -> a.intersect(b))
                .orElse(RangeDto.EMPTY);
        return SnapshotQuery.builder()
                .snapshotIds(snapshotIds)
                .minDuration(durationRange.getMin())
                .maxDuration(durationRange.getMax())
                .minRootDuration(dto.getMinRootDuration() != null ? dto.getMinRootDuration().intValue() : null)
                .querySystemFault(dto.getSystemFault())
                .queryBusinessFault(dto.getBusinessFault())
                .build();
    }
}
