package com.stardata.observ.controller;

import com.stardata.observ.api.ServicesMapApi;
import com.stardata.observ.common.Resources;
import com.stardata.observ.service.ServicesMapService;
import com.stardata.observ.vo.ServiceMapRequest;
import com.stardata.observ.vo.ServiceMapResponse;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-03-04 21:03
 */
@AllArgsConstructor
@Controller
@RequestMapping("${openapi.smartobserv02.base-path:}")
public class ServicesMapsController implements ServicesMapApi {

    private final ServicesMapService servicesMapService;

    @Override
    public ResponseEntity<ServiceMapResponse> createServiceMap(ServiceMapRequest serviceMapRequest) {
        return Resources.with("创建服务地图")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> servicesMapService.createServiceMap(serviceMapRequest));
    }
}
