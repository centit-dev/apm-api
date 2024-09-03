package com.stardata.observ.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.stardata.observ.vo.InstanceRedResponse;
import com.stardata.observ.vo.NameDisplayListResponse;
import com.stardata.observ.vo.TomcatStatusListResponse;
import com.stardata.observ.api.PlatformsApi;
import com.stardata.observ.common.Resources;
import com.stardata.observ.service.ApplicationStructureService;
import com.stardata.observ.service.SpanService;
import com.stardata.observ.vo.HeatmapResponse;
import com.stardata.observ.vo.InstanceListResponse;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("${openapi.smartobserv02.base-path:}")
public class PlatformsController implements PlatformsApi {

    private final ApplicationStructureService service;
    private final SpanService spanService;

    @Override
    public ResponseEntity<NameDisplayListResponse> listAppClusters(String platform) {
        return Resources.with("获取平面下应用集群列表")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> service.listAppClusters(platform));
    }

    @Override
    public ResponseEntity<InstanceListResponse> listAppInstances(
            String platform, String cluster,
            Integer timeType, Long fromTime, Long toTime, Integer recentSeconds) {
        return Resources.with("获取集群下应用实例列表")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> service.listAppInstances(platform, cluster, timeType, fromTime, toTime, recentSeconds));
    }

    @Override
    public ResponseEntity<InstanceListResponse> listSoftwareInstances(
            String platform, String software,
            Integer timeType, Long fromTime, Long toTime, Integer recentSeconds) {
        return Resources.with("获取集群下软件实例列表")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> service.listSoftwareInstances(platform, software, timeType, fromTime, toTime, recentSeconds));
    }

    @Override
    public ResponseEntity<NameDisplayListResponse> listPlatforms() {
        return Resources.with("获得平面列表")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> service.listPlatforms());
    }

    @Override
    public ResponseEntity<HeatmapResponse> getClusterHeatmap(
            @NotNull @Valid String snapshotId,
            @Valid Integer granularity) {
        return Resources.with("获取集群热点图")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> spanService.getClusterHeatmap(snapshotId, granularity));
    }

    @Override
    public ResponseEntity<TomcatStatusListResponse> getTomcatStatus(
            String platform, String cluster,
            String instanceName, Integer timeType, Long fromTime, Long toTime, Integer recentSeconds, Integer granularity) {
        return Resources.with("获取tomcat状态趋势")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> service.getTomcatStatus(platform, cluster, instanceName, timeType, recentSeconds, fromTime, toTime, granularity));
    }

    @Override
    public ResponseEntity<InstanceRedResponse> getClusterRed(
            String platform, String cluster, String instanceName, String serviceName,
            Integer timeType, Long fromTime, Long toTime, Integer recentSeconds) {
        return Resources.with("获取实例RED数据")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> service.getClusterRed(
                        platform, cluster, instanceName, serviceName,
                        timeType, recentSeconds, fromTime, toTime));
    }

}
