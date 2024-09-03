package com.stardata.observ.common;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum QueryOperator {
    EQUAL("="),
    NOT_EQUAL("!="),
    GreaterThan(">"),
    GreaterThanOrEqual(">="),
    LessThan("<"),
    LessThanOrEqual("<="),
    StartsWith("startsWith"),
    NotStartWith("notStartWith"),
    Exists("exists"),
    NotExist("notExist"),
    Contains("contains"),
    NotContain("notContain"),
    In("in"),
    NotIn("notIn");

    private static final Set<String> values = Stream.of(values()).map(QueryOperator::getOperator).collect(Collectors.toSet());

    private final String operator;

    QueryOperator(String operator) {
        this.operator = operator;
    }

    public static boolean contains(String operator) {
        return values.contains(operator);
    }

    public String getOperator() {
        return operator;
    }
}
