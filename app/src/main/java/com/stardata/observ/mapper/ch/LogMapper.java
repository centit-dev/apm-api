package com.stardata.observ.mapper.ch;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stardata.observ.model.ch.LogCount;
import com.stardata.observ.model.ch.Log;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-02-23 22:02
 */
@Repository
public interface LogMapper extends BaseMapper<Log> {
    List<Log> findLogByTraceIdAndSpanId(
            @Param("traceId") String traceId,
            @Param("spanId") String spanId);

    List<Log> findLogByTraceId(@Param("traceId") String traceId);

    List<LogCount> countLogsBySpanIds(
            @Param("traceIds") Set<String> traceIds,
            @Param("spanIds") Set<String> spanIds);

    int countLogByConditionsWithWhereStatement(
            @Param("fromTime") long fromTime,
            @Param("toTime") long toTime,
            @Param("whereStatement") String whereStatement);

    List<Log> findLogByConditionsWithWhereStatement(
            @Param("fromTime") long fromTime,
            @Param("toTime") long toTime,
            @Param("whereStatement") String whereStatement,
            @Param("offset") Integer offset,
            @Param("limit") Integer pageSize);
}
