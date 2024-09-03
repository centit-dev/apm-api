package com.stardata.observ.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CHNotExistWhereGenerator implements WhereGenerator {
    private static final Pattern ATTRIBUTE_PATTERN = Pattern.compile("^([a-zA-Z]+)?Attributes\\[.+?\\]$");

    // support attributes
    @Override
    public String generate(SnapshotFieldCondition condition) {
        Matcher matcher = ATTRIBUTE_PATTERN.matcher(condition.getField());
        if (matcher.find()) {
            return String.format("%s = ''", condition.getField());
        }
        return StringUtils.EMPTY;
    }
}
