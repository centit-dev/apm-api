package com.stardata.observ.model.ch;

import java.util.List;

import lombok.Data;

@Data
public class SpanFaultGroup {
    private List<String> labels;
    private Integer calls;
    private Integer faultCalls;
    private List<String> rootServices;
    private List<String> instances;
}
