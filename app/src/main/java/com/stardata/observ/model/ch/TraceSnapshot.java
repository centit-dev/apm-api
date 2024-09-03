package com.stardata.observ.model.ch;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-16 15:00
 */

@Data
@TableName(value = "apm.user_trace_snapshot")
public class TraceSnapshot implements Serializable {

    private static final long serialVersionUID = 2942019624008474568L;

    @TableId(value = "SnapshotId", type = IdType.INPUT)
    private String snapshotId;

    /**
     * json对象表示的查询跳进，包括：字段属性条件、时间条件、状态条件、记录数限制。
     */
    @TableField("Conditions")
    private String conditions;

    /**
     * json数组表示的快照所对应spanId列表
     */
    @TableField("TraceIds")
    private String traceIds;

    @TableField("UserId")
    private String userId;

    /**
     * 创建时间
     */
    @TableField("TimeStamp")
    private LocalDateTime timeStamp;


}
