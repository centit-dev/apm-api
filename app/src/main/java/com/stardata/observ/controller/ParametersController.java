package com.stardata.observ.controller;

import com.stardata.observ.vo.NameDisplayListResponse;
import com.stardata.observ.api.ParametersApi;
import com.stardata.observ.common.Resources;
import com.stardata.observ.service.SysParameterService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-01-17 23:01
 */
@AllArgsConstructor
@Controller
@RequestMapping("${openapi.smartobserv02.base-path:}")
public class ParametersController implements ParametersApi {

    final SysParameterService service;

    @Override
    public ResponseEntity<NameDisplayListResponse> getDataTypeOperators(String type) {
        return Resources.with("获取数据类型对应的操作符列表")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> service.getDataTypeOperator(type));
    }
}
