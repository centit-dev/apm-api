package com.stardata.observ.controller;

import javax.servlet.http.HttpServletRequest;

import com.stardata.observ.vo.CommonResponse;
import com.stardata.observ.api.QueryTemplatesApi;
import com.stardata.observ.common.Resources;
import com.stardata.observ.service.UserQueryTemplateService;
import com.stardata.observ.service.UserService;
import com.stardata.observ.vo.QueryTemplateListResponse;
import com.stardata.observ.vo.QueryTemplateRequest;
import com.stardata.observ.vo.QueryTemplateResponse;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-02-19 22:02
 */
@AllArgsConstructor
@Controller
@RequestMapping("${openapi.smartobserv02.base-path:}")
public class QueryTemplatesController implements QueryTemplatesApi {

    private final UserQueryTemplateService userQueryTemplateService;
    private final UserService userService;
    private final HttpServletRequest request;

    @Override
    public ResponseEntity<QueryTemplateResponse> createQueryTemplate(
            QueryTemplateRequest queryTemplateRequest) {
        return Resources.with("创建搜索模板")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> userQueryTemplateService.createUserQueryTemplate(
                        queryTemplateRequest, userService.getLoginUser(request).getId()));
    }

    @Override
    public ResponseEntity<CommonResponse> deleteQueryTemplate(String templateId) {
        return Resources.with("删除搜索模板")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> userQueryTemplateService.deleteUserQueryTemplateById(
                        templateId, userService.getLoginUser(request).getId()));
    }

    @Override
    public ResponseEntity<QueryTemplateListResponse> listQueryTemplates(Integer type, Integer limit) {
        return Resources.with("查询搜索模板")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> userQueryTemplateService.getTemplatesByType(
                        type, userService.getLoginUser(request).getId(), limit));
    }

    @Override
    public ResponseEntity<QueryTemplateResponse> updateQueryTemplate(
            String templateId, QueryTemplateRequest queryTemplateRequest) {
        return Resources.with("更新搜索模板")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> userQueryTemplateService.updateUserQueryTemplate(
                        queryTemplateRequest, templateId, userService.getLoginUser(request).getId()));
    }
}
