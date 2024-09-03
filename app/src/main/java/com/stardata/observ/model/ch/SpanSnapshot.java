package com.stardata.observ.model.ch;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-16 14:53
 */

@Data
@TableName(value = "apm.user_span_snapshot", autoResultMap = true)
public class SpanSnapshot implements Serializable {

    private static final long serialVersionUID = 3155113372545866491L;

    @TableId(value = "SnapshotId", type = IdType.INPUT)
    private String snapshotId;

    @TableField("ParentSnapshotId")
    private String parentSnapshotId;

    /**
     * 由前端传递的时间范围的起点
     */
    @TableField("StartTime")
    private Date startTime;

    /**
     * 由前端传递的时间范围的终点
     */
    @TableField("EndTime")
    private Date endTime;

    /**
     * json对象表示的查询跳进，包括：字段属性条件、时间条件、状态条件、记录数限制。
     */
    @TableField(value = "Conditions", typeHandler = GsonTypeHandler.class)
    private SnapshotCondition conditions;

    /**
     * json数组表示的快照所对应(traceId,spanId)元组列表
     */
    @TableField("TraceSpanIds")
    private String traceSpanIds;

    @TableField("TraceIds")
    private String traceIds;

    @TableField("SpanIds")
    private String spanIds;

    @TableField("WhereStatement")
    private String whereStatement;

    @TableField("UserId")
    private String userId;

    /**
     * 创建时间
     */
    @TableField("Timestamp")
    private Date timestamp;

    public List<String> getWhereStatements() {
        if (StringUtils.isBlank(whereStatement)) {
            return Collections.emptyList();
        }
        return Collections.singletonList(whereStatement);
    }
}
