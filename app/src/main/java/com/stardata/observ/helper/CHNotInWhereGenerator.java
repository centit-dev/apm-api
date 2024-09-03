package com.stardata.observ.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.stardata.observ.common.ApplicationValidationException;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;

import org.springframework.stereotype.Component;

@Component
public class CHNotInWhereGenerator implements WhereGenerator {
    private final Pattern ARRAY_PATTERN = Pattern.compile("[\\[(].*[\\])]");

    // support all strings, numbers and attributes
    @Override
    public String generate(SnapshotFieldCondition condition) {
        Matcher matcher = ARRAY_PATTERN.matcher(condition.getValue());
        if (!matcher.find()) {
            throw new ApplicationValidationException("The value of the condition is not an array.");
        }
        return String.format("%s NOT IN %s", condition.getField(), condition.getValue());
    }
}
