package com.stardata.observ.model.ch;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-17 22:41
 */

@Data
@TableName(value = "otel_metrics_gauge", autoResultMap = true)
public class MetricsGauge implements Serializable {

    private static final long serialVersionUID = 8543179058068848984L;

    private String resourceAttributes;

    private String resourceSchemaUrl;

    private String scopeName;

    private String scopeVersion;

    private String scopeAttributes;

    private Long scopeDroppedAttrCount;

    private String scopeSchemaUrl;

    private String metricName;

    private String metricDescription;

    private String metricUnit;

    private String attributes;

    private Date startTimeUnix;

    private Date timeUnix;

    private Double value;

    private Long flags;

    private String exemplars_FilteredAttributes;

    private Date exemplars_TimeUnix;

    private Double exemplars_Value;

    private String exemplars_SpanId;

    private String exemplars_TraceId;

}
