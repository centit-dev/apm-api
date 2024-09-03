package com.stardata.observ.helper;

import com.stardata.observ.model.ch.SnapshotCondition;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author Samson Shu
 * @version 1.0
 * @email shush@stardata.top
 * @date 2023/12/26 23:57
 */
@Component
public class EmptyWhereGenerator implements WhereGenerator {
    @Override
    public String generate(SnapshotCondition.SnapshotFieldCondition condition) {
        return StringUtils.EMPTY;
    }
}
