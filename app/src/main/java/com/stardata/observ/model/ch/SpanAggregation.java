package com.stardata.observ.model.ch;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stardata.observ.handler.StringMapTypeHandler;

import lombok.Data;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-01-03 23:28
 */
@Data
@TableName(value = "otel_span_aggregations", autoResultMap = true)
public class SpanAggregation implements Serializable {

    private static final long serialVersionUID = 7076038596760619013L;

    @TableField("Timestamp")
    private Date timestamp;

    @TableField("TraceId")
    private String traceId;

    @TableField("SpanId")
    private String spanId;

    @TableField("ParentSpanId")
    private String parentSpanId;

    @TableField("PlatformName")
    private String platformName;

    @TableField("RootServiceName")
    private String rootServiceName;

    @TableField("RootSpanName")
    private String rootSpanName;

    @TableField("ServiceName")
    private String serviceName;

    @TableField("SpanName")
    private String spanName;

    @JsonIgnore
    @TableField("SpanStatus")
    private String spanStatus;

    @TableField(value = "ResourceAttributes", typeHandler = StringMapTypeHandler.class)
    private Map<String, String> resourceAttributes;

    @TableField(value = "SpanAttributes", typeHandler = StringMapTypeHandler.class)
    private Map<String, String> spanAttributes;

    @TableField("Duration")
    private Long duration;

    @TableField("Gap")
    private Long gap;

    @TableField("SelfDuration")
    private Long selfDuration;
}
