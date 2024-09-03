package com.stardata.observ.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.stardata.observ.api.TracesBySnapshotsApi;
import com.stardata.observ.common.Resources;
import com.stardata.observ.service.TracesService;
import com.stardata.observ.service.UserService;
import com.stardata.observ.vo.TraceListResponse;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-02-22 20:02
 */
@AllArgsConstructor
@Controller
@RequestMapping("${openapi.smartobserv02.base-path:}")
public class TracesBySnapshotsController implements TracesBySnapshotsApi {

    private final TracesService tracesService;
    private final UserService userService;
    private final HttpServletRequest request;

    @Override
    public ResponseEntity<TraceListResponse> listTracesBySpanSnapshots(
            List<String> spanSnapshotId, Integer pageNo, Integer pageSize) {
        String userId = userService.getLoginUser(request).getId();

        return Resources.with("获取Trace列表")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> tracesService.listTracesBySpanSnapshots(userId, spanSnapshotId, pageNo, pageSize));
    }

}
