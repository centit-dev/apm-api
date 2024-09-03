package com.stardata.observ.model.pg;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-08 00:14
 */

@Data
@TableName(value = "tb_query_key", autoResultMap = true)
public class QueryKey implements Serializable {

    public static final String FIELD_SPANS_VALID = "spans_valid";
    public static final String FIELD_LOGS_VALID = "logs_valid";
    public static final String FIELD_METRIC_VALID = "metrics_valid";
    private static final long serialVersionUID = 137291812125021597L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 查询条件key名称。用于描述该查询条件对应的数据，来源于哪个字段中，如：SpanAttributes['hostName'], spanName等
     */
    private String name;

    /**
     * 该查询条件对应的数据类型。S-字符串；N-数字。
     */
    private String type;

    /**
     * span查询是否可用该key
     */
    private Boolean spansValid;

    /**
     * log查询是否可用该key
     */
    private Boolean logsValid;

    /**
     * metrics查询是否可用该key
     */
    private Boolean metricsValid;

    /**
     * 该查询条件Key有效期。默认30天更新一次，如果没更新，表示已失效（该查询条件已经不再存在）。
     */
    private Date validDate;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
