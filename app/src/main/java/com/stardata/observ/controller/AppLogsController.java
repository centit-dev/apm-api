package com.stardata.observ.controller;

import com.stardata.observ.api.AppLogsApi;
import com.stardata.observ.common.Resources;
import com.stardata.observ.service.LogService;
import com.stardata.observ.vo.AppLogListResponse;
import com.stardata.observ.vo.QueryConditions;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-02-26 22:02
 */
@AllArgsConstructor
@Controller
@RequestMapping("${openapi.smartobserv02.base-path:}")
public class AppLogsController implements AppLogsApi {

    private final LogService logService;

    @Override
    public ResponseEntity<AppLogListResponse> listAppLogs(
            Integer pageNo, Integer pageSize, QueryConditions queryConditions) {
        return Resources.with("查询Log列表")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> logService.listAppLogs(pageNo, pageSize, queryConditions));
    }
}
