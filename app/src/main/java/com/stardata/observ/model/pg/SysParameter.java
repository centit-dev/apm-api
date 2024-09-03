package com.stardata.observ.model.pg;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-18 21:07
 */

@Data
@TableName(value = "tb_sys_parameter", autoResultMap = true)
public class SysParameter implements Serializable {

    private static final long serialVersionUID = 8000762676103583223L;

    /**
     * 参数名称，目前支持的参数包括：
     * <p>
     * dag_latency：DAG服务地图上显示的时延；
     * <p>
     * max_allowable_latency：响应时间过长标准；
     * <p>
     * trace_latency_show_trend：trace时延趋势展示的内容，最多不超过5个，是个JSON字符串数组，如：["P50","P75","P90"]；
     * <p>
     * trace_result_grouping_fields：异常trace参与分组统计span属性列表，最多不超过6个，是个JSON字符串数组，如：["span.platform","span.cluster","span.instance","span.application","name","span.exception.name"]；
     * <p>
     * trace_grouping_invalid_fields：trace定位不参与分组推荐span属性列表,是个JSON字符串数组，如：["trace.id", "span.id", "timestamp"]；
     * <p>
     * service_comparison_invalid_fields：服务比对span无效属性列表，是个JSON字符串数组，如：["trace.id", "span.id", "timestamp"]；
     * <p>
     * data_type_operators：数据类型对应的操作符列表，是个JSON字符串对象，如：{"number": [">=","="], "string": ["contains","exists"]}；
     */
    @TableId
    private String code;

    /**
     * 参数值。 无论什么类型的参数，都保存为字符串。可以保存：整数、浮点数、普通字符串、JSON字符串。
     */
    private String value;

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
