package com.stardata.observ.model.ch;

import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stardata.observ.handler.StringMapTypeHandler;

import lombok.Data;

@Data
@TableName(value = "otel_metrics_sum", autoResultMap = true)
public class MetricsSum {
    @TableField(value = "ResourceAttributes", typeHandler = StringMapTypeHandler.class)
    private Map<String, String> resourceAttributes;

    @TableField(value = "MetricName")
    private String metricName;

    @TableField(value = "Attributes", typeHandler = StringMapTypeHandler.class)
    private Map<String, String> attributes;

    @TableField(value = "StartTimeUnix")
    private Date startTimeUnix;

    @TableField(value = "TimeUnix")
    private Date timeUnix;

    @TableField(value = "Value")
    private Double value;
}
