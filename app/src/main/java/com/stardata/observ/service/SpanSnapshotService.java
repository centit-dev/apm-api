package com.stardata.observ.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stardata.observ.common.ApplicationValidationException;
import com.stardata.observ.common.FaultKindEnum;
import com.stardata.observ.helper.CHWhereHelper;
import com.stardata.observ.mapper.ch.TraceFaultMapperDelegator;
import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.utils.JSONUtil;
import com.stardata.observ.vo.DurationRange;
import com.stardata.observ.vo.FieldCondition;
import com.stardata.observ.vo.FieldConditionResponse;
import com.stardata.observ.vo.QueryForType;
import com.stardata.observ.vo.SnapshotResponse;
import com.stardata.observ.vo.SpanSnapshotConditionResponse;
import com.stardata.observ.vo.SpanSnapshotResponse;
import com.stardata.observ.vo.SpanStatus;
import com.stardata.observ.vo.SpanSubSnapshotRequest;
import com.stardata.observ.vo.TimeRangeType;
import com.stardata.observ.vo.TimeWindow;
import com.stardata.observ.mapper.ch.FunctionMapper;
import com.stardata.observ.mapper.ch.SpanSnapshotMapper;
import com.stardata.observ.model.ch.FaultTrend;
import com.stardata.observ.model.ch.SpanSnapshot;
import com.stardata.observ.vo.CreateSpanSnapshotRequest;
import com.stardata.observ.vo.SpanSnapshotConditionListResponse;
import com.stardata.observ.vo.StatusCondition;
import com.stardata.observ.vo.TimeRange;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static com.stardata.observ.utils.DateUtil.getActualTimeRange;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-02-24 12:02
 */
@Service
@Slf4j
@AllArgsConstructor
public class SpanSnapshotService {
    private final QueryDictionaryService dictionaryService;
    private final SpanSnapshotMapper spanSnapshotMapper;
    private final TraceFaultMapperDelegator gaugeMapper;
    private final FunctionMapper functionMapper;
    private final CHWhereHelper chWhereHelper;

    public List<SpanSnapshot> getSnapshotsByIds(Collection<String> ids) {
        QueryWrapper<SpanSnapshot> wrapper = new QueryWrapper<>();
        wrapper.in("SnapshotId", ids);
        wrapper.select("SnapshotId", "UserId", "Conditions", "StartTime", "EndTime", "WhereStatement", "Timestamp");
        return spanSnapshotMapper.selectList(wrapper);
    }

    public SpanSnapshot getSnapshotById(String snapshotId) {
        QueryWrapper<SpanSnapshot> wrapper = new QueryWrapper<>();
        wrapper.eq("SnapshotId", snapshotId);
        wrapper.select("SnapshotId", "UserId", "Conditions", "StartTime", "EndTime", "WhereStatement", "Timestamp");
        return spanSnapshotMapper.selectOne(wrapper);
    }

    public SpanSnapshotResponse getSnapshotById(String userId, String snapshotId) {
        QueryWrapper<SpanSnapshot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("UserId", userId);
        queryWrapper.eq("SnapshotId", snapshotId);
        queryWrapper.select("SnapshotId", "UserId", "Conditions", "StartTime", "EndTime", "WhereStatement", "Timestamp");
        SpanSnapshot snapshot = spanSnapshotMapper.selectOne(queryWrapper);
        if (snapshot == null) {
            return new SpanSnapshotResponse();
        }
        return new SpanSnapshotResponse().data(convertToConditionVo(snapshot));
    }

    public SpanSnapshotConditionListResponse getSnapshotsByParentSnapshotId(String userId, String parentId, Integer limit) {
        if (StringUtils.isEmpty(parentId)) {
            return new SpanSnapshotConditionListResponse();
        }
        QueryWrapper<SpanSnapshot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("UserId", userId);
        queryWrapper.eq("ParentSnapshotId", parentId);
        queryWrapper.select("SnapshotId", "UserId", "Conditions", "StartTime", "EndTime", "WhereStatement", "Timestamp");
        if (limit != null) queryWrapper.last(" limit " + limit);
        List<SpanSnapshot> spanSnapshots = spanSnapshotMapper.selectList(queryWrapper);
        return new SpanSnapshotConditionListResponse().data(spanSnapshots.stream()
                .map(this::convertToConditionVo)
                .collect(Collectors.toList()));
    }

    private SpanSnapshotConditionResponse convertToConditionVo(SpanSnapshot snapshot) {
        SpanSnapshotConditionResponse condition = new SpanSnapshotConditionResponse();
        condition.setSnapshotId(snapshot.getSnapshotId());
        if (snapshot.getConditions() == null) {
            return condition;
        }
        if (snapshot.getConditions().getMinDuration() != null
                || snapshot.getConditions().getMaxDuration() != null) {
            condition.setDurationInterval(new DurationRange()
                    .min(snapshot.getConditions().getMinDuration())
                    .max(snapshot.getConditions().getMaxDuration()));
        }
        if (snapshot.getConditions().getTimeRange() != null) {
            condition.setTimeWindow(new TimeWindow()
                    .fromTime(snapshot.getConditions().getFromTime())
                    .toTime(snapshot.getConditions().getToTime()));
        }
        if (snapshot.getConditions().getStatusConditions() != null) {
            condition.setStatusConditions(snapshot.getConditions().getStatusConditions().stream()
                    .map(cond -> new StatusCondition()
                            .status(SpanStatus.fromValue(cond.getStatus()))
                            .trend(cond.getTrend() != null
                                    ? StatusCondition.TrendEnum.fromValue(cond.getTrend())
                                    : null)
                            .minDelay(cond.getMinDelay()))
                    .collect(Collectors.toList()));
        }
        if (snapshot.getConditions().getFieldConditions() != null) {
            condition.setFieldConditions(snapshot.getConditions().getFieldConditions().stream()
                    .map(cond -> new FieldConditionResponse(
                            dictionaryService.getDisplayPair(cond.getField()),
                            dictionaryService.getDisplayPair(cond.getOperator()),
                            cond.getValue()))
                    .collect(Collectors.toList()));
        }
        return condition;
    }

    public SnapshotResponse createSpanSnapshot(String userId, CreateSpanSnapshotRequest request) {
        TimeRange actualTimeRange = getActualTimeRange(request.getTimeCondition());
        long fromTime = actualTimeRange.getFromTime();
        long toTime = actualTimeRange.getToTime();

        SnapshotCondition condition = new SnapshotCondition();
        condition.setSpanOrLogTableName(request.getFor() == QueryForType.LOG ? "otel_logs" : "otel_spans");
        if (request.getFieldConditions() != null) {
            condition.setFieldConditions(request.getFieldConditions()
                    .stream()
                    .map(cond -> new SnapshotCondition.SnapshotFieldCondition(cond.getName(), cond.getOperator(), cond.getValue()))
                    .collect(Collectors.toList()));
        }
        if (request.getStatusConditions() != null) {
            condition.setStatusConditions(request.getStatusConditions()
                    .stream()
                    .map(cond -> {
                        Integer status = cond.getStatus() == null ? null : cond.getStatus().getValue();
                        String trend = cond.getTrend() == null ? null : cond.getTrend().getValue();
                        return new SnapshotCondition.SnapshotStatusCondition(status, trend, cond.getMinDelay());
                    })
                    .collect(Collectors.toList()));
        }
        condition.setTimeRange(new SnapshotCondition.SnapshotTimeRange(
                fromTime, toTime,
                actualTimeRange.getTimeType() != null ? actualTimeRange.getTimeType().getValue() : TimeRangeType.DESIGNATED.getValue(),
                actualTimeRange.getRecentSeconds()));

        SpanSnapshot snapshot = createSpanSnapshot(userId, null, condition);
        return new SnapshotResponse().data(snapshot.getSnapshotId());
    }

    @Nonnull
    public SpanSnapshot createSpanSnapshot(
            String userId,
            String parentSnapshotId,
            @Nonnull SnapshotCondition condition) {
        return createSpanSnapshot(userId, parentSnapshotId, condition, null);
    }

    @Nonnull
    public SpanSnapshot createSpanSnapshot(
            String userId,
            String parentSnapshotId,
            @Nonnull SnapshotCondition condition,
            @Nullable SnapshotCondition traceFaultCondition) {
        SpanSnapshot snapshot = new SpanSnapshot();
        snapshot.setSnapshotId(functionMapper.newUUID());
        snapshot.setParentSnapshotId(parentSnapshotId);
        snapshot.setUserId(userId);
        snapshot.setStartTime(new Date(condition.getFromTime()));
        snapshot.setEndTime(new Date(condition.getToTime()));
        snapshot.setConditions(condition);
        String whereStatement = chWhereHelper.generateWhereStringFromSnapshot(condition.getFieldConditions());
        log.debug("[Monitor] prepare whereStatement is {}", whereStatement);
        snapshot.setWhereStatement(whereStatement);

        String traceFaultStatement = "";
        if (traceFaultCondition != null) {
            traceFaultStatement = chWhereHelper.generateWhereStringFromSnapshot(traceFaultCondition.getFieldConditions());
        }

        Map<String, List<FaultTrend>> trendMap = gaugeMapper.getFaultTrend(
                condition.getBusinessStatusCondition(),
                condition.getSystemStatusCondition(),
                condition.getFromTime(),
                condition.getToTime());

        int result = spanSnapshotMapper.createSnapshotByConditionsWithWhereStatement(
                snapshot.getSnapshotId(),
                snapshot.getParentSnapshotId(),
                condition.getFromTime(),
                condition.getToTime(),
                condition.getMinDuration(),
                condition.getMaxDuration(),
                snapshot.getWhereStatement(),
                traceFaultStatement,
                condition.getSpanOrLogTableName(),
                String.format("'%s'", snapshot.getWhereStatement().replace("'", "\\'")),
                JSONUtil.toJSONString(condition),
                userId,
                CollectionUtils.isNotEmpty(condition.getStatusConditions()),
                condition.getSuccessStatusCondition(),
                // empty means we still look for the fault traces
                trendMap.get(FaultKindEnum.SYSTEM_FAULT.value()),
                trendMap.get(FaultKindEnum.BUSINESS_FAULT.value()));
        if (result == 1) {
            return snapshot;
        }
        throw new ApplicationValidationException("Create snapshot failed.");
    }

    public SnapshotResponse createSubSpanSnapshot(String userId, String parentSnapshotId, SpanSubSnapshotRequest spanSubSnapshotRequest) {
        QueryWrapper<SpanSnapshot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("UserId", userId);
        queryWrapper.eq("SnapshotId", parentSnapshotId);
        queryWrapper.select("Conditions");
        SpanSnapshot parent = spanSnapshotMapper.selectOne(queryWrapper);
        if (parent == null) {
            return new SnapshotResponse();
        }

        SnapshotCondition condition = convertToSnapshotCondition(spanSubSnapshotRequest);
        condition.setSpanOrLogTableName(parent.getConditions().getSpanOrLogTableName());
        condition = parent.getConditions().duplicate().append(condition);
        SpanSnapshot snapshot = createSpanSnapshot(userId, parentSnapshotId, condition);
        return new SnapshotResponse().data(snapshot.getSnapshotId());
    }

    private SnapshotCondition convertToSnapshotCondition(SpanSubSnapshotRequest spanSubSnapshotRequest) {
        SnapshotCondition snapshotCondition = new SnapshotCondition();
        if (CollectionUtils.isNotEmpty(spanSubSnapshotRequest.getFieldConditions())) {
            for (FieldCondition fieldCondition : spanSubSnapshotRequest.getFieldConditions()) {
                snapshotCondition.addFieldCondition(new SnapshotCondition.SnapshotFieldCondition(
                        fieldCondition.getName(),
                        fieldCondition.getOperator(),
                        fieldCondition.getValue())
                );
            }
        }
        TimeWindow timeWindow = spanSubSnapshotRequest.getTimeWindow();
        if (timeWindow != null) {
            snapshotCondition.setTimeRange(new SnapshotCondition.SnapshotTimeRange(
                    timeWindow.getFromTime(),
                    timeWindow.getToTime(),
                    TimeRangeType.DESIGNATED.getValue(),
                    0));
        } else {
            Instant now = Instant.now();
            snapshotCondition.setTimeRange(new SnapshotCondition.SnapshotTimeRange(
                now.minus(5, ChronoUnit.MINUTES).toEpochMilli(),
                now.toEpochMilli(),
                TimeRangeType.DESIGNATED.getValue(),
                0));;
        }
        if (spanSubSnapshotRequest.getDurationInterval() != null) {
            snapshotCondition.setMinDuration(spanSubSnapshotRequest.getDurationInterval().getMin());
            snapshotCondition.setMaxDuration(spanSubSnapshotRequest.getDurationInterval().getMax());
        }
        return snapshotCondition;
    }
}
