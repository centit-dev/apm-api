package com.stardata.observ.service;

import java.util.regex.Matcher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueryKeyServiceTest {

    @Test
    void testPattern() {
        String attribute = "ResourceAttributes[\"k8s.deployment.name\"]";
        Matcher matcher = QueryKeyService.RESOURCE_ATTRIBUTE_BLOCK_PATTERN.matcher(attribute);
        assertTrue(matcher.find());
        assertEquals("k8s.deployment.name", matcher.group("key"));
    }

}
