package com.stardata.observ.controller;

import com.stardata.observ.api.TracesApi;
import com.stardata.observ.common.Resources;
import com.stardata.observ.service.LogService;
import com.stardata.observ.service.SpanService;
import com.stardata.observ.service.TracesService;
import com.stardata.observ.vo.AppLogDetailListResponse;
import com.stardata.observ.vo.ServiceMapResponse;
import com.stardata.observ.vo.SpanDetailResponse;
import com.stardata.observ.vo.TraceDetailResponse;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-02-19 23:02
 */
@AllArgsConstructor
@Controller
@RequestMapping("${openapi.smartobserv02.base-path:}")
public class TracesController implements TracesApi {
    private final TracesService tracesService;
    private final SpanService spanService;
    private final LogService logService;

    @Override
    public ResponseEntity<TraceDetailResponse> getTraceDetail(String traceId) {
        return Resources.with("根据traceID查询trace详情")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> tracesService.getTraceDetail(traceId));
    }

    @Override
    public ResponseEntity<AppLogDetailListResponse> getSpanAppLogs(String traceId, String spanId) {
        return Resources.with("根据traceId和spanId查询其内包含的应用日志列表")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> logService.findLogsBySpan(traceId, spanId));
    }

    @Override
    public ResponseEntity<SpanDetailResponse> getSpanByTraceIdAndSpanId(String traceId, String spanId) {
        return Resources.with("获取Span详情")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> spanService.findSpanByTraceIdAndSpanId(traceId, spanId));
    }

    @Override
    public ResponseEntity<ServiceMapResponse> getTraceServiceMap(String traceId) {
        return Resources.with("Trace服务地图")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> tracesService.getServiceMap(traceId));
    }
}
