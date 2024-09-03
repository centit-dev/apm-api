package com.stardata.observ.model.pg;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-01-18 18:47
 */

@Data
@TableName(value = "tb_application_structure", autoResultMap = true)
public class ApplicationStructure implements Serializable {

    private static final long serialVersionUID = 7661740569558312234L;

    /**
     * 应用分层编码，平面名称或应用集群名称
     */
    private String code;

    /**
     * 应用分层父节点编码
     */
    private String parentCode;

    /**
     * 应用分层级别：1-平面；2-应用集群；3-应用实例；
     */
    private Integer level;

    /**
     * 该查询条件Key有效期。默认30天更新一次，如果没更新，表示已失效（该分层已经不存在）。
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
