package com.stardata.observ.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.common.ApplicationValidationException;

import org.springframework.stereotype.Component;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-02-24 19:34
 */
@Component
public class CHInWhereGenerator implements WhereGenerator {
    private static final Pattern ARRAY_PATTERN = Pattern.compile("[\\[(].*[\\])]");

    // support all strings, numbers and attributes
    @Override
    public String generate(SnapshotCondition.SnapshotFieldCondition condition) {
        Matcher matcher = ARRAY_PATTERN.matcher(condition.getValue());
        if (!matcher.find()) {
            throw new ApplicationValidationException("The value of the condition is not an array.");
        }
        return String.format("%s IN %s", condition.getField(), condition.getValue().replace('"', '\''));
    }
}
