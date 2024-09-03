package com.stardata.observ.mapper.ch;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stardata.observ.model.ch.FaultTrend;
import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.model.ch.SpanSnapshot;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Samson Shu
 * @version 1.0
 * @email shush@stardata.top
 * @date 2023/12/28 17:50
 */
@Repository
public interface SpanSnapshotMapper extends BaseMapper<SpanSnapshot> {
    int getTraceCountBySnapshotIds(@Param("spanSnapshotIds") List<String> spanSnapshotIds);

    /**
     * Create a base snapshot from trace conditions and span wheres
     *
     * @param snapshotId the pre-generated snapshot id
     * @param parentSnapshotId the parent snapshot id
     * @param fromTime the start time of the spans
     * @param toTime the end time of the spans
     * @param maxDuration the maximum duration of the spans
     * @param minDuration the minimum duration of the spans
     * @param whereStatement the where statement for the spans
     * @param whereStatementValue the string format of the where statement stored for the sequence queries
     * @param condition the string format of the conditions stored for restoring the frontend state
     * @param userId the user id of the user who created the snapshot
     * @param statusConditionQueried whether the status condition is queried
     * @param successStatusCondition the success status condition
     * @param sysFaultStatusCondition the system fault status condition,
     *      exclude all system fault traces if it is null,
     *      include all system fault traces if it is empty,
     *      include the specified system fault traces if it is not empty
     * @param bizFaultStatusCondition the business fault status condition,
     *      exclude all business fault traces if it is null,
     *      include all business fault traces if it is empty,
     *      include the specified business fault traces if it is not empty
     * @return 1 if created successfully, 0 otherwise
     */
    int createSnapshotByConditionsWithWhereStatement(
            @Param("snapshotId") String snapshotId,
            @Param("parentSnapshotId") String parentSnapshotId,
            @Param("fromTime") long fromTime,
            @Param("toTime") long toTime,
            @Param("minDuration") Long minDuration,
            @Param("maxDuration") Long maxDuration,
            @Param("whereStatement") String whereStatement,
            @Param("traceFaultWhereStatement") String traceFaultWhereStatement,
            @Param("tableName") String tableName,
            @Param("whereStatementValue") String whereStatementValue,
            @Param("condition") String condition,
            @Param("userId") String userId,
            @Param("statusConditionQueried") boolean statusConditionQueried,
            @Param("successStatusCondition") SnapshotCondition.SnapshotStatusCondition successStatusCondition,
            @Param("sysFaultStatusCondition") List<FaultTrend> sysFaultStatusCondition,
            @Param("bizFaultStatusCondition") List<FaultTrend> bizFaultStatusCondition);
}
