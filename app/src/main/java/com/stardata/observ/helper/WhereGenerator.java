package com.stardata.observ.helper;

import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;


/**
 * @author Samson Shu
 * @version 1.0
 * @email shush@stardata.top
 * @date 2023/12/26 23:24
 */
public interface WhereGenerator {
    String generate(SnapshotFieldCondition condition);
}
