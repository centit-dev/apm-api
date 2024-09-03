package com.stardata.observ.model.pg;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * @author Samson Shu
 * @email shush@stardata.top
 * @date 2024/01/02
 */

@Data
@TableName(value = "tb_exception_define", autoResultMap = true)
public class ExceptionDefine implements Serializable {

    private static final long serialVersionUID = 7928134730815618890L;

    @TableId
    private Long id;

    private Long categoryId;

    private String shortName;

    private String longName;

    /**
     * 是否有效：0-无效，1-有效
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer isValid;

    /**
     * 创建时间
     */
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
