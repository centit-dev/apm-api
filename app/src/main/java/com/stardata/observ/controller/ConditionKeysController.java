package com.stardata.observ.controller;

import com.stardata.observ.api.ConditionKeysApi;
import com.stardata.observ.common.Resources;
import com.stardata.observ.service.QueryKeyService;
import com.stardata.observ.vo.ConditionKeyListResponse;
import com.stardata.observ.vo.StringListResponse;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-01-17 19:01
 */
@AllArgsConstructor
@Controller
@RequestMapping("${openapi.smartobserv02.base-path:}")
public class ConditionKeysController implements ConditionKeysApi {

    private final QueryKeyService queryKeyService;

    @Override
    public ResponseEntity<ConditionKeyListResponse> listConditionKeys(Integer type) {
        return Resources.with("查询条件key列表")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> queryKeyService.listConditionKeys(type));
    }

    @Override
    public ResponseEntity<StringListResponse> listConditionValues(Long keyId) {
        return Resources.with("查询条件某key取值")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> queryKeyService.listConditionValues(keyId));
    }
}
