package com.stardata.observ.model.pg;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author Samson Shu
 * @email shush@stardata.top
 * @date 2023/12/28
 */

@Data
@TableName(value = "tb_user", autoResultMap = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String password;

    @TableField(fill = FieldFill.INSERT)
    private Integer isValid;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
