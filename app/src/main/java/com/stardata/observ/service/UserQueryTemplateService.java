package com.stardata.observ.service;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stardata.observ.vo.FieldConditionResponse;
import com.stardata.observ.vo.QueryForType;
import com.stardata.observ.vo.QueryTemplateResponse;
import com.stardata.observ.vo.SpanStatus;
import com.stardata.observ.vo.TimeRangeType;
import com.stardata.observ.common.ApplicationValidationException;
import com.stardata.observ.mapper.pg.UserQueryTemplateMapper;
import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotStatusCondition;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotTimeRange;
import com.stardata.observ.model.pg.UserQueryTemplate;
import com.stardata.observ.utils.DateUtil;
import com.stardata.observ.vo.QueryTemplate;
import com.stardata.observ.vo.QueryTemplateListResponse;
import com.stardata.observ.vo.QueryTemplateRequest;
import com.stardata.observ.vo.StatusCondition;
import com.stardata.observ.vo.StatusCondition.TrendEnum;
import com.stardata.observ.vo.TimeRange;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserQueryTemplateService {

    private final UserQueryTemplateMapper mapper;
    private final QueryDictionaryService dictionaryService;

    public QueryTemplateListResponse getTemplatesByType(int type, String userId, Integer limit) {
        QueryWrapper<UserQueryTemplate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("type", type);
        if (limit == null) {
            limit = 10;
        }
        queryWrapper.last("limit " + limit);
        List<QueryTemplate> queryTemplates = mapper.selectList(queryWrapper)
                .stream()
                .map(this::convertToQueryTemplate)
                .collect(Collectors.toList());
        return new QueryTemplateListResponse().data(queryTemplates);
    }

    private UserQueryTemplate getTemplateById(String id, String userId) {
        QueryWrapper<UserQueryTemplate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.eq("user_id", userId);
        return mapper.selectOne(queryWrapper);
    }

    public QueryTemplateResponse createUserQueryTemplate(
            QueryTemplateRequest queryTemplateRequest, String userId) {
        UserQueryTemplate userQueryTemplate = new UserQueryTemplate();
        userQueryTemplate.setUserId(userId);
        userQueryTemplate.setName(queryTemplateRequest.getName());
        userQueryTemplate.setType(queryTemplateRequest.getFor().getValue());
        userQueryTemplate.setConditions(buildCondition(queryTemplateRequest));
        mapper.insert(userQueryTemplate);
        QueryTemplate data = convertToQueryTemplate(userQueryTemplate);
        return new QueryTemplateResponse().data(data);
    }

    public QueryTemplateResponse updateUserQueryTemplate(
            QueryTemplateRequest queryConditions, String templateId, String userId) {
        UserQueryTemplate userQueryTemplate = getTemplateById(templateId, userId);
        if (userQueryTemplate == null) {
            throw new ApplicationValidationException("Can't find QueryTemplate.");
        }
        userQueryTemplate.setName(queryConditions.getName());
        userQueryTemplate.setType(queryConditions.getFor().getValue());
        userQueryTemplate.setConditions(buildCondition(queryConditions));
        mapper.updateById(userQueryTemplate);
        QueryTemplate data = convertToQueryTemplate(userQueryTemplate);
        return new QueryTemplateResponse().data(data);
    }

    private SnapshotCondition buildCondition(QueryTemplateRequest request) {
        SnapshotCondition condition = new SnapshotCondition();
        condition.setFieldConditions(request.getFieldConditions()
                .stream()
                .map(cond -> new SnapshotFieldCondition(cond.getName(), cond.getOperator(), cond.getValue()))
                .collect(Collectors.toList())
        );
        condition.setStatusConditions(request.getStatusConditions()
                .stream()
                .map(cond -> new SnapshotStatusCondition(
                        cond.getStatus() != null ? cond.getStatus().getValue() : null,
                        cond.getTrend() != null ? cond.getTrend().getValue() : null,
                        cond.getMinDelay()))
                .collect(Collectors.toList()));
        TimeRange timeRange = DateUtil.getActualTimeRange(request.getTimeCondition());
        condition.setTimeRange(new SnapshotTimeRange(
                timeRange.getFromTime(),
                timeRange.getToTime(),
                timeRange.getTimeType() != null ? timeRange.getTimeType().getValue() : TimeRangeType.DESIGNATED.getValue(),
                timeRange.getRecentSeconds()));
        return condition;
    }

    private QueryTemplate convertToQueryTemplate(UserQueryTemplate item) {
        if (item == null) {
            return null;
        }
        QueryTemplate queryTemplate = new QueryTemplate()
                .id(item.getId())
                .name(item.getName())
                ._for(QueryForType.fromValue(item.getType()));
        if (item.getConditions() == null) {
            return queryTemplate;
        }
        SnapshotTimeRange timeRange = item.getConditions().getTimeRange();
        if (timeRange != null) {
            TimeRangeType timeRangeType = null;
            try {
                timeRangeType = TimeRangeType.fromValue(timeRange.getTimeType());
            } catch (Exception e) {
                log.error("Unexpected time range type: {}", timeRange.getTimeType());
            }
            queryTemplate.setTimeCondition(new TimeRange()
                    .fromTime(item.getConditions().getFromTime())
                    .toTime(item.getConditions().getToTime())
                    .timeType(timeRangeType)
                    .recentSeconds(timeRange.getRecentSeconds()));
        }
        if (item.getConditions().getStatusConditions() != null) {
            queryTemplate.setStatusConditions(item.getConditions().getStatusConditions()
                    .stream()
                    .map(cond -> {
                        TrendEnum trend = null;
                        if (StringUtils.isNotBlank(cond.getTrend())) {
                            try {
                                trend = TrendEnum.fromValue(cond.getTrend());
                            } catch (IllegalArgumentException ex) {
                                log.error("Unexpected trend value: {}", cond.getTrend());
                            }
                        }
                        return new StatusCondition()
                                .status(SpanStatus.fromValue(cond.getStatus()))
                                .trend(trend)
                                .minDelay(cond.getMinDelay());
                    })
                    .collect(Collectors.toList()));
        }
        if (item.getConditions().getFieldConditions() != null) {
            queryTemplate.setFieldConditions(item.getConditions().getFieldConditions()
                    .stream()
                    .map(cond -> new FieldConditionResponse()
                            .name(dictionaryService.getDisplayPair(cond.getField()))
                            .operator(dictionaryService.getDisplayPair(cond.getOperator()))
                            .value(cond.getValue()))
                    .collect(Collectors.toList()));
        }
        return queryTemplate;
    }

    public void deleteUserQueryTemplateById(String id, String userId) {
        QueryWrapper<UserQueryTemplate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.eq("user_id", userId);
        mapper.delete(queryWrapper);
    }
}
