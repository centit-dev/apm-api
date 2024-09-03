package com.stardata.observ.model.ch;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-07 23:53
 */

@Data
public class MetricsHistogram implements Serializable {

    private static final long serialVersionUID = 4699026686998076756L;

    /**
     * 资源属性
     */
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

    private Long count;

    private Double sum;

    private Long bucketCounts;

    private Double explicitBounds;

    private String exemplars_FilteredAttributes;

    private Date exemplars_TimeUnix;

    private Double exemplars_Value;

    private String exemplars_SpanId;

    private String exemplars_TraceId;

    private Long flags;

    private Double min;

    private Double max;

}
