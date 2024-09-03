package com.stardata.observ.helper;

import com.stardata.observ.model.ch.SnapshotCondition;

import org.springframework.stereotype.Component;

@Component
public class CHEndWithWhereGenerator implements WhereGenerator {
    // support all strings and attributes
    @Override
    public String generate(SnapshotCondition.SnapshotFieldCondition condition) {
        return String.format("like(%s, '%%%s')", condition.getField(), condition.getValue());
    }
}
