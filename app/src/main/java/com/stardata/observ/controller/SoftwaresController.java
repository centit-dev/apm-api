package com.stardata.observ.controller;

import com.stardata.observ.api.SoftwaresApi;
import com.stardata.observ.common.Resources;
import com.stardata.observ.service.SoftwareService;
import com.stardata.observ.vo.SoftwareListResponse;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-02-25 17:02
 */
@AllArgsConstructor
@Controller
@RequestMapping("${openapi.smartobserv02.base-path:}")
public class SoftwaresController implements SoftwaresApi {

    private final SoftwareService service;

    @Override
    public ResponseEntity<SoftwareListResponse> listSoftwares() {
        return Resources.with("查询软件列表")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> service.listSoftware());
    }
}
