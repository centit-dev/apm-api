package com.stardata.observ.service;

import java.util.regex.Matcher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueryDictionaryServiceTest {

    @Test
    void testName() {
        Matcher matcher = QueryDictionaryService.REQUEST_BODY_PATTERN.matcher("SpanAttributes['http.request.body.Map.status']");
        assertTrue(matcher.find());
        assertEquals("Map.status", matcher.group("body"));

        matcher = QueryDictionaryService.REQUEST_BODY_PATTERN.matcher("SpanAttributes['some.other']");
        assertFalse(matcher.find());

        matcher = QueryDictionaryService.RESPONSE_BODY_PATTERN.matcher("SpanAttributes['http.response.body.Map.status']");
        assertTrue(matcher.find());
        assertEquals("Map.status", matcher.group("body"));

        matcher = QueryDictionaryService.RESPONSE_BODY_PATTERN.matcher("SpanAttributes['some.other']");
        assertFalse(matcher.find());
    }

}
