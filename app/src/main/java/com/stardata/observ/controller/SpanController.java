package com.stardata.observ.controller;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.stardata.observ.api.SpansApi;
import com.stardata.observ.common.Resources;
import com.stardata.observ.service.SpanService;
import com.stardata.observ.vo.FieldCondition;
import com.stardata.observ.vo.SpanListResponse;

import lombok.AllArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("${openapi.smartobserv02.base-path:}")
public class SpanController implements SpansApi {

    private final SpanService spanService;
    private final Gson gson;
    private final HttpServletRequest request;
    private final Type type = new TypeToken<List<FieldCondition>>() {}.getType();

    @Override
    public ResponseEntity<SpanListResponse> getSpans(
            @Valid List<String> spanSnapshotIds, @Valid String conditionString,
            @Valid Long timestampStart, @Valid Long timestampEnd,
            @Valid Long minDuration, @Valid Long maxDuration,
            @Valid Integer pageNo, @Valid Integer pageSize) {
        Map<String, String> extraParams = new HashMap<>();
        Map<String, String[]> params = request.getParameterMap();
        params.forEach((k, v) -> extraParams.put(k, v[0]));
        return Resources.with("获取Span列表")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> {
                    List<FieldCondition> conditions = null;
                    try {
                        if (StringUtils.isNotBlank(conditionString)) {
                            conditions = gson.fromJson(conditionString, type);
                        }
                    } catch (JsonSyntaxException | JsonIOException e) {
                        throw new IllegalArgumentException("条件格式错误");
                    }
                    if (conditions != null) {
                        return spanService.findSpans(spanSnapshotIds, conditions, pageNo, pageSize);
                    }

                    return spanService.findSpans(timestampStart, timestampEnd, minDuration, maxDuration, extraParams, pageNo, pageSize);
                });
    }

}
