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
@TableName(value = "tb_query_value", autoResultMap = true)
public class QueryValue implements Serializable {

    private static final long serialVersionUID = 1504498484180553908L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 归属key
     */
    private Long keyId;

    /**
     * 查询条件取值
     */
    private String value;

    /**
     * 该查询条件value有效期。默认30天更新一次，如果没更新，表示已失效（该查询值已经不再存在）。
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
