package com.stardata.observ.model.ch;

import java.util.Map;

import lombok.Data;

@Data
public class SpanAttributeSum {
    private Map<String, Integer> resourceSum;
    private Map<String, Integer> spanSum;
}
