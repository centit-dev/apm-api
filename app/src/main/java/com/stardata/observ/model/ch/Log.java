package com.stardata.observ.model.ch;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stardata.observ.handler.StringMapTypeHandler;

import lombok.Data;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-02-23 22:20
 */
@Data
@TableName(value = "otel_logs", autoResultMap = true)
public class Log implements Serializable {

    private static final long serialVersionUID = 7378763959240327218L;

    @TableField("Timestamp")
    private Date timestamp;

    @TableField("TraceId")
    private String traceId;

    @TableField("SpanId")
    private String spanId;

    @TableField("TraceFlags")
    private Long traceFlags;

    @TableField("SeverityText")
    private String severityText;

    @TableField("SeverityNumber")
    private Integer severityNumber;

    @TableField("ServiceName")
    private String serviceName;

    @TableField("Body")
    private String body;

    @TableField("ResourceSchemaUrl")
    private String resourceSchemaUrl;

    @TableField(value = "ResourceAttributes", typeHandler = StringMapTypeHandler.class)
    private Map<String, String> resourceAttributes;

    @TableField("ScopeSchemaUrl")
    private String scopeSchemaUrl;

    @TableField("ScopeName")
    private String scopeName;

    @TableField("ScopeVersion")
    private String scopeVersion;

    @TableField(value = "ScopeAttributes", typeHandler = StringMapTypeHandler.class)
    private Map<String, String> scopeAttributes;

    @TableField(value = "LogAttributes", typeHandler = StringMapTypeHandler.class)
    private Map<String, String> logAttributes;
}
