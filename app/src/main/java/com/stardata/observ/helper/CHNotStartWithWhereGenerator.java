package com.stardata.observ.helper;

import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;

import org.springframework.stereotype.Component;

@Component
public class CHNotStartWithWhereGenerator implements WhereGenerator {
    // support all strings and attributes
    @Override
    public String generate(SnapshotFieldCondition condition) {
        return String.format("notLike(%s, '%s%%')", condition.getField(), condition.getValue());
    }
}
