package com.stardata.observ.model.ch;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stardata.observ.handler.StringMapTypeHandler;
import com.stardata.observ.common.FaultKindEnum;
import com.stardata.observ.common.SpanAttributeEnum;

import lombok.Data;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-26 23:29
 */

@Data
@TableName(value = "otel_spans", autoResultMap = true)
public class Span implements Serializable {

    private static final long serialVersionUID = 3196640563048668356L;

    @TableField("Timestamp")
    private Date timestamp;

    @TableField("TraceId")
    private String traceId;

    @TableField("SpanId")
    private String spanId;

    @TableField("ParentSpanId")
    private String parentSpanId;

    @TableField("TraceState")
    private String traceState;

    @TableField("SpanName")
    private String spanName;

    @TableField("SpanKind")
    private String spanKind;

    @TableField("ServiceName")
    private String serviceName;

    @TableField(value = "ResourceAttributes", typeHandler = StringMapTypeHandler.class)
    private Map<String, String> resourceAttributes;

    @TableField("ScopeName")
    private String scopeName;

    @TableField("ScopeVersion")
    private String scopeVersion;

    @TableField(value = "SpanAttributes", typeHandler = StringMapTypeHandler.class)
    private Map<String, String> spanAttributes;

    @TableField("Duration")
    private Long duration;

    @TableField("StatusCode")
    private String statusCode;

    @TableField("StatusMessage")
    private String statusMessage;

    @TableField("Events.Timestamp")
    private String eventsTimestamp;

    @TableField("Events.Name")
    private String eventsName;

    @TableField("Events.Attributes")
    private String eventsAttributes;

    @TableField("Links.TraceId")
    private String linksTraceId;

    @TableField("Links.SpanId")
    private String linksSpanId;

    @TableField("Links.TraceState")
    private String linksTraceState;

    @TableField("Links.Attributes")
    private String linksAttributes;

    public FaultKindEnum getFaultKind() {
        String faultKindStr = spanAttributes.get(SpanAttributeEnum.FAULT_KIND.value());
        for (FaultKindEnum faultKindEnum : FaultKindEnum.values()) {
            if (faultKindEnum.value().equals(faultKindStr)) {
                return faultKindEnum;
            }
        }
        return FaultKindEnum.UNKNOWN;
    }

    /**
     * 获得可展示的服务名称
     *
     * @return 可展示的服务名称，根据span.ServiceName + span.SpanName组合而成
     */
    public String getDisplayServiceCode() {
        return String.format("%s / %s", serviceName, spanName);
    }


}
