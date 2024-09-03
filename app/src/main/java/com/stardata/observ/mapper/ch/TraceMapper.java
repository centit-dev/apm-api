package com.stardata.observ.mapper.ch;

import java.util.List;

import com.stardata.observ.model.ch.Trace;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TraceMapper {
    Trace findByTraceId(String traceId);

    List<Trace> findTracesBySpanSnapshots(
            @Param("snapshotQuery") SnapshotQuery query,
            @Param("offset") Integer offset,
            @Param("limit") Integer pageSize);
}
