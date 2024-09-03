package com.stardata.observ.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.stardata.observ.vo.Percentile;
import com.stardata.observ.api.SpanDiagnosisApi;
import com.stardata.observ.common.Resources;
import com.stardata.observ.service.TraceFaultService;
import com.stardata.observ.vo.DurationTrendResponse;
import com.stardata.observ.vo.DurationType;
import com.stardata.observ.vo.InitialCauseAppInstanceListResponse;
import com.stardata.observ.vo.InitialCauseSpanListResponse;
import com.stardata.observ.vo.ServiceErrorDiagnosisResponse;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("${openapi.smartobserv02.base-path:}")
public class SpanDiagnosisController implements SpanDiagnosisApi {

    private final TraceFaultService traceFaultService;

    @Override
    public ResponseEntity<ServiceErrorDiagnosisResponse> getSpanExceptionAnalysis(
            @NotNull @Valid List<String> spanSnapshotIds) {
        return Resources.with("span诊断报错解析")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> traceFaultService.getSpanExceptionAnalysis(spanSnapshotIds));
    }

    @Override
    public ResponseEntity<InitialCauseAppInstanceListResponse> getSpanAppInstances(
            @NotNull @Valid List<String> spanSnapshotIds) {
        return Resources.with("span诊断异常应用实例解析")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> traceFaultService.groupByAppInstance(spanSnapshotIds));
    }

    @Override
    public ResponseEntity<DurationTrendResponse> getSpanDurationTrends(
            @NotNull @Valid List<String> spanSnapshotIds,
            @Valid List<@Valid Percentile> percentiles,
            @Valid List<@Valid DurationType> durationTypes,
            @Valid Integer granularity,
            @Valid Long fromTime,
            @Valid Long toTime) {
        return Resources.with("span诊断报错解析")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> traceFaultService.calculateDurationStatistics(
                        spanSnapshotIds,
                        percentiles,
                        durationTypes,
                        granularity,
                        fromTime,
                        toTime));
    }

    @Override
    public ResponseEntity<InitialCauseSpanListResponse> getTraceFaults(
            @NotNull @Valid String spanSnapshotId,
            @Valid Integer pageNo,
            @Valid Integer pageSize) {
        return Resources.with("初因span列表")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> traceFaultService.getTraceFaults(spanSnapshotId, pageNo, pageSize));
    }

}
