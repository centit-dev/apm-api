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
 * @date 2024-02-25 18:03
 */

@Data
@TableName(value = "tb_software_define", autoResultMap = true)
public class SoftwareDefine implements Serializable {

    private static final long serialVersionUID = 7943353519202230214L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 中间件、数据库名称
     */
    private String name;

    /**
     * span识别规则条件，以类似如下的json格式保存：
     * [
     * {
     * "column":"otel.library.name",
     * "op": "contains",
     * "value": "lettuc"
     * },
     * {
     * "column":"otel.db.system",
     * "op": "=",
     * "value": "redis"
     * }
     * ]
     */
    private String spanConditions;

    /**
     * 软件定义类型：1-应用软件，2-中间件数据库等；
     */
    private Integer type;

    /**
     * 软件logo的图片文件地址，http或https路径URL。
     */
    private String logo;

    /**
     * 是否有效：0-无效，1-有效
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
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
