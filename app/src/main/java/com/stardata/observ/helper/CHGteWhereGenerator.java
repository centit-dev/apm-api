package com.stardata.observ.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.stardata.observ.model.ch.SnapshotCondition;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CHGteWhereGenerator implements WhereGenerator {
    private static final Pattern ATTRIBUTE_PATTERN = Pattern.compile("^([a-zA-Z]+)?Attributes\\[.+?\\]$");

    // support numbers
    @Override
    public String generate(SnapshotCondition.SnapshotFieldCondition condition) {
        Matcher matcher = ATTRIBUTE_PATTERN.matcher(condition.getField());
        if (matcher.find()) {
            return StringUtils.EMPTY;
        }
        return String.format("%s >= %s", condition.getField(), condition.getValue());
    }
}
