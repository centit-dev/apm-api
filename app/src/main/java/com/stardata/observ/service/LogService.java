package com.stardata.observ.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.stardata.observ.helper.CHWhereHelper;
import com.stardata.observ.vo.AppLogBrief;
import com.stardata.observ.vo.AppLogDetail;
import com.stardata.observ.vo.AppLogListResponse;
import com.stardata.observ.vo.KeyValuePair;
import com.stardata.observ.vo.QueryConditions;
import com.stardata.observ.vo.QueryForType;
import com.stardata.observ.common.ResourceAttributeEnum;
import com.stardata.observ.mapper.ch.LogMapper;
import com.stardata.observ.model.ch.Log;
import com.stardata.observ.vo.AppLogDetailListResponse;
import com.stardata.observ.vo.AppLogPage;
import com.stardata.observ.vo.TimeRange;

import io.opentelemetry.semconv.incubating.K8sIncubatingAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import static com.stardata.observ.utils.DateUtil.getActualTimeRange;

@Service
@Slf4j
@AllArgsConstructor
public class LogService {

    private final QueryDictionaryService dictionaryService;
    private final LogMapper logMapper;
    private final CHWhereHelper chWhereHelper;

    public List<Log> findLogByTraceId(String traceId) {
        return logMapper.findLogByTraceId(traceId);
    }

    public AppLogListResponse listAppLogs(
            Integer pageNo, Integer pageSize, QueryConditions queryConditions) {
        if (pageNo == null || pageNo < 0) {
            pageNo = 0;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        if (queryConditions == null || queryConditions.getFor() != QueryForType.LOG) {
            return new AppLogListResponse();
        }
        AppLogPage data = new AppLogPage().pageNo(pageNo).pageSize(pageSize);
        String whereStatement = chWhereHelper.generateWhereString(queryConditions.getFieldConditions());
        log.info("[Monitor] listAppLogs prepare whereStatement is {}", whereStatement);

        TimeRange actualTimeRange = getActualTimeRange(queryConditions.getTimeCondition());
        long fromTime = actualTimeRange.getFromTime();
        long toTime = actualTimeRange.getToTime();
        int totalCount = logMapper.countLogByConditionsWithWhereStatement(fromTime, toTime, whereStatement);
        data.setTotalCount(totalCount);

        Integer offset = pageNo * pageSize;
        List<Log> logs = logMapper.findLogByConditionsWithWhereStatement(fromTime, toTime, whereStatement, offset, pageSize);
        List<AppLogBrief> logBriefs = convertToLogBrief(logs);
        data.setLogs(logBriefs);
        data.setPageCount(logBriefs.size());
        return new AppLogListResponse().data(data);
    }

    private List<AppLogBrief> convertToLogBrief(List<Log> logs) {
        List<AppLogBrief> logBriefs = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(logs)) {
            for (Log log : logs) {
                AppLogBrief appLogBrief = new AppLogBrief();
                appLogBrief.setTraceId(log.getTraceId());
                appLogBrief.setSpanId(log.getSpanId());
                appLogBrief.setTimestamp(log.getTimestamp().getTime());
                appLogBrief.setService(log.getServiceName());
                appLogBrief.setSeverityNumber(log.getSeverityNumber());
                appLogBrief.setSeverityText(log.getSeverityText());
                appLogBrief.setScopeName(log.getScopeName());
                appLogBrief.setBody(log.getBody());
                appLogBrief.setApplication(dictionaryService.getDisplayPair(log.getServiceName()));
                appLogBrief.setCluster(log.getResourceAttributes().get(ResourceAttributeEnum.APP_CLUSTER.value()));
                appLogBrief.setInstance(log.getResourceAttributes().get(K8sIncubatingAttributes.K8S_POD_NAME.getKey()));
                appLogBrief.setPlatform(dictionaryService.getDisplayPair(log.getResourceAttributes().get(ResourceAttributeEnum.APP_PLATFORM.value())));
                logBriefs.add(appLogBrief);
            }
        }
        return logBriefs;
    }

    public AppLogDetailListResponse findLogsBySpan(String traceId, String spanId) {
        List<AppLogDetail> details = logMapper.findLogByTraceIdAndSpanId(traceId, spanId).stream()
                .map(log -> {
                    AppLogDetail detail = new AppLogDetail();
                    detail.setTimestamp(log.getTimestamp().getTime());
                    detail.setTraceId(log.getTraceId());
                    detail.setSpanId(log.getSpanId());
                    detail.setApplication(dictionaryService.getDisplayPair(log.getServiceName()));
                    detail.setService(log.getServiceName());
                    if (log.getResourceAttributes() != null) {
                        String value = log.getResourceAttributes().get(ResourceAttributeEnum.APP_PLATFORM.value());
                        detail.setPlatform(dictionaryService.getDisplayPair(value));

                        value = log.getResourceAttributes().get(ResourceAttributeEnum.APP_CLUSTER.value());
                        detail.setCluster(value);

                        value = log.getResourceAttributes().get(K8sIncubatingAttributes.K8S_POD_NAME.getKey());
                        detail.setInstance(value);
                    }
                    detail.setSeverityText(log.getSeverityText());
                    detail.setSeverityNumber(log.getSeverityNumber());
                    detail.setScopeName(log.getScopeName());
                    detail.setBody(log.getBody());
                    detail.setResourceAttributes(convertPairs(log.getResourceAttributes()));
                    detail.setScopeAttributes(convertPairs(log.getScopeAttributes()));
                    detail.setLogAttributes(convertPairs(log.getLogAttributes()));
                    return detail;
                })
                .collect(Collectors.toList());
        return new AppLogDetailListResponse(null, null, details);
    }

    private List<KeyValuePair> convertPairs(Map<String, String> attributes) {
        if (MapUtils.isEmpty(attributes)) {
            return Collections.emptyList();
        }

        return attributes.entrySet().stream()
                .map(entry -> new KeyValuePair().key(entry.getKey()).value(entry.getValue()))
                .collect(Collectors.toList());
    }
}
