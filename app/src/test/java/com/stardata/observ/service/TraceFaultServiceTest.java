package com.stardata.observ.service;

import java.util.regex.Matcher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TraceFaultServiceTest {

    @Test
    void testKeyValuePattern() {
        String keyValue = "messaging.system=kafka";
        Matcher matcher = TraceFaultService.KEY_VALUE_PATTERN.matcher(keyValue);
        assertTrue(matcher.find());
        assertEquals("messaging.system", matcher.group("key"));
        assertEquals("kafka", matcher.group("value"));
    }

}
