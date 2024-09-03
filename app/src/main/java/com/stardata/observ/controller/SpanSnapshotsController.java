package com.stardata.observ.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.stardata.observ.api.SpanSnapshotsApi;
import com.stardata.observ.common.Resources;
import com.stardata.observ.service.SpanSnapshotService;
import com.stardata.observ.service.TraceFaultService;
import com.stardata.observ.service.UserService;
import com.stardata.observ.vo.CreateSpanSnapshotRequest;
import com.stardata.observ.vo.FaultTrendResponse;
import com.stardata.observ.vo.HeatmapResponse;
import com.stardata.observ.vo.SnapshotResponse;
import com.stardata.observ.vo.SpanCommonalityListResponse;
import com.stardata.observ.vo.SpanGroupField;
import com.stardata.observ.vo.SpanGroupListResponse;
import com.stardata.observ.vo.SpanSnapshotConditionListResponse;
import com.stardata.observ.vo.SpanSnapshotResponse;
import com.stardata.observ.vo.SpanSubSnapshotRequest;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-02-24 08:02
 */

@AllArgsConstructor
@Controller
@RequestMapping("${openapi.smartobserv02.base-path:}")
public class SpanSnapshotsController implements SpanSnapshotsApi {

    private final SpanSnapshotService snapshotService;
    private final TraceFaultService faultService;
    private final UserService userService;
    private final HttpServletRequest request;

    @Override
    public ResponseEntity<SnapshotResponse> createSpanSnapshot(
            CreateSpanSnapshotRequest createSpanSnapshotRequest) {
        String userId = userService.getLoginUser(request).getId();

        return Resources.with("创建Span快照")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> snapshotService.createSpanSnapshot(userId, createSpanSnapshotRequest));
    }

    @Override
    public ResponseEntity<SnapshotResponse> createSubSpanSnapshot(
            String snapshotId, SpanSubSnapshotRequest spanSubSnapshotRequest) {
        String userId = userService.getLoginUser(request).getId();
        return Resources.with("创建Span子快照")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> snapshotService.createSubSpanSnapshot(userId, snapshotId, spanSubSnapshotRequest));
    }

    @Override
    public ResponseEntity<SpanSnapshotResponse> getSpanSnapshot(String snapshotId) {
        String userId = userService.getLoginUser(request).getId();
        return Resources.with("获取span快照")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> snapshotService.getSnapshotById(userId, snapshotId));
    }

    @Override
    public ResponseEntity<FaultTrendResponse> getSpanFaultTrend(
            String snapshotId, Integer granularity) {
        return Resources.with("获取span报错趋势解析")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> faultService.getTraceFaultTrend(snapshotId, granularity));
    }

    @Override
    public ResponseEntity<HeatmapResponse> getSpanHeatmap(String snapshotId, Integer granularity) {
        return Resources.with("获取span热点图趋势解析")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> faultService.getSpanHeatmap(snapshotId, granularity));
    }

    @Override
    public ResponseEntity<SpanSnapshotConditionListResponse> listSpanAnalysisConditions(
            String snapshotId, Integer limit) {
        String userId = userService.getLoginUser(request).getId();

        return Resources.with("获取span子快照条件列表")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> snapshotService.getSnapshotsByParentSnapshotId(userId, snapshotId, limit));
    }

    @Override
    public ResponseEntity<SpanCommonalityListResponse> identifySpanCommonalities(String snapshotId, Integer limit) {
        return Resources.with("获取span子快照公共字段")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> faultService.listTraceFaultCommonalities(snapshotId, limit == null ? 20 : limit));
    }

    @Override
    public ResponseEntity<SpanGroupListResponse> groupSpanSnapshots(
            String spanSnapshotId,
            @Valid List<@Valid SpanGroupField> spanGroupField) {
        return Resources.with("获取span子快照分组")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> faultService.groupBySnapshotIdAndAttributes(spanSnapshotId, spanGroupField));
    }
}
