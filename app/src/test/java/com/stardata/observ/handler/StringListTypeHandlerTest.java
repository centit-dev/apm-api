package com.stardata.observ.handler;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringListTypeHandlerTest {
    private StringListTypeHandler handler = new StringListTypeHandler();

    @Test
    void testStringToList() {
        String input = "[a, b]";
        List<String> expected = handler.fromString(input);
        List<String> actual = Arrays.asList("a", "b");
        assertEquals(expected, actual);
    }

    @Test
    void testListToString() {
        List<String> input = Arrays.asList("a", "b");
        String expected = handler.toString(input);
        String actual = "[\"a\",\"b\"]";
        assertEquals(expected, actual);
    }
}
