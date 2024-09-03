package com.stardata.observ.model.ch;

import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stardata.observ.handler.StringMapTypeHandler;

import lombok.Data;

@Data
@TableName(value = "otel_trace_faults", autoResultMap = true)
public class TraceFault {

    @TableField("Timestamp")
    private Date timestamp;

    @TableField("TraceId")
    private String traceId;

    @TableField("PlatformName")
    private String platformName;

    @TableField("AppCluster")
    private String appCluster;

    @TableField("InstanceName")
    private String instanceName;

    @TableField("RootServiceName")
    private String rootServiceName;

    @TableField("RootSpanName")
    private String rootSpanName;

    @TableField("RootDuration")
    private Long rootDuration;

    @TableField("ParentSpanId")
    private String parentSpanId;

    @TableField("SpanId")
    private String spanId;

    @TableField("ServiceName")
    private String serviceName;

    @TableField("SpanName")
    private String spanName;

    @TableField("SpanKind")
    private String spanKind;

    @TableField("FaultKind")
    private String faultKind;

    @TableField("Duration")
    private Long duration;

    @TableField("Gap")
    private Long gap;

    @TableField("SelfDuration")
    private Long selfDuration;

    @TableField(value = "ResourceAttributes", typeHandler = StringMapTypeHandler.class)
    private Map<String, String> resourceAttributes;

    @TableField(value = "SpanAttributes", typeHandler = StringMapTypeHandler.class)
    private Map<String, String> spanAttributes;

}
