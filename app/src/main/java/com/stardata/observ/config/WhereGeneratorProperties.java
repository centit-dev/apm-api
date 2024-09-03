package com.stardata.observ.config;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Samson Shu
 * @version 1.0
 * @email shush@stardata.top
 * @date 2023/12/26 23:37
 */
@Data
@ConfigurationProperties(prefix = "where-generators")
public class WhereGeneratorProperties {
    private List<WhereConfig> clickhouse = new ArrayList<>();

    @Data
    public static class WhereConfig {
        private String operator;
        private String generator;
    }
}
