package com.stardata.observ.model.pg;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author Samson Shu
 * @email shush@stardata.top
 * @date 2023/12/28
 */

@Data
@TableName(value = "tb_user_token", autoResultMap = true)
public class UserToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String token;

    private Date expiredTime;

    private Date createTime;

}
