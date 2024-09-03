package com.stardata.observ.model.pg;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-16 17:53
 */
@Data
@TableName(value = "tb_query_dictionary", autoResultMap = true)
public class QueryDictionary implements Serializable {

    private static final long serialVersionUID = 983833918921408648L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 原始字段名
     */
    private String name;

    private String displayName;

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

    public String getDisplayName() {
        if (StringUtils.isEmpty(displayName)) {
            return name;
        } else return displayName;
    }
}
