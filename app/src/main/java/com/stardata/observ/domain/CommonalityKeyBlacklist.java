package com.stardata.observ.domain;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class CommonalityKeyBlacklist {
    private List<String> resourceAttributeBlacklist = new ArrayList<>();
    private List<String> spanAttributeBlacklist = new ArrayList<>();

    public void addResourceBlacklist(String key) {
        if (resourceAttributeBlacklist == null) {
            resourceAttributeBlacklist = Lists.newArrayList(key);
        } else {
            resourceAttributeBlacklist.add(key);
        }
    }

    public void addSpanBlacklist(String key) {
        if (spanAttributeBlacklist == null) {
            spanAttributeBlacklist = Lists.newArrayList(key);
        } else {
            spanAttributeBlacklist.add(key);
        }
    }
}
