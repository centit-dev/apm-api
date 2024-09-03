package com.stardata.observ.config;

import com.stardata.observ.helper.CHWhereHelper;
import com.stardata.observ.mapper.ch.SpanMapperDelegator;
import com.stardata.observ.service.SpanSnapshotService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@MapperScan("com.stardata.observ.mapper")
@ComponentScan(basePackageClasses = {
    PgEntityCallbackHandler.class,
    SpanSnapshotService.class,
    SpanMapperDelegator.class,
    CHWhereHelper.class})
public class CoreTestConfiguration {

}
