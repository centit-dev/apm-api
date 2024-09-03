package com.stardata.observ.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-02-24 10:02
 */
@Component
public class CHEqualWhereGenerator implements WhereGenerator {
    private static final Pattern ATTRIBUTE_PATTERN = Pattern.compile("^([a-zA-Z]+)?Attributes\\[.+?\\]$");

    // support all strings, numbers and attributes
    @Override
    public String generate(SnapshotFieldCondition condition) {
        Matcher matcher = ATTRIBUTE_PATTERN.matcher(condition.getField());
        if (matcher.find() || !StringUtils.isNumeric(condition.getValue())) {
            return String.format("%s = '%s'", condition.getField(), condition.getValue());
        }
        return String.format("%s = %s", condition.getField(), condition.getValue());
    }
}
