package com.stardata.observ.model.pg;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stardata.observ.model.ch.SnapshotCondition;

import lombok.Data;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-10 14:43
 */
@Data
@TableName(value = "tb_user_query_template", autoResultMap = true)
public class UserQueryTemplate {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField
    private String userId;

    /**
     * 名称，任意100字节内的字符串
     */
    @TableField
    private String name;

    /**
     * 模板类型：1-trace搜索模板，2-日志搜索模板，3-服务搜索模板
     */
    @TableField
    private Integer type;

    @TableField(value = "conditions", typeHandler = com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler.class)
    private SnapshotCondition conditions;

    /**
     * 是否有效：0-无效，1-有效
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer isValid;

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
