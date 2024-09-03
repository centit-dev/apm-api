package com.stardata.observ.helper;

import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;

import org.springframework.stereotype.Component;

@Component
public class CHStartWithWhereGenerator implements WhereGenerator {
    // support all strings and attributes
    @Override
    public String generate(SnapshotFieldCondition condition) {
        return String.format("like(%s, '%s%%')", condition.getField(), condition.getValue());
    }
}
