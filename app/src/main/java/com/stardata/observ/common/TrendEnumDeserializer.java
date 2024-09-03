package com.stardata.observ.common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.stardata.observ.vo.StatusCondition;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-01-22 23:01
 */
public class TrendEnumDeserializer extends JsonDeserializer<StatusCondition.TrendEnum> {
    @Override
    public StatusCondition.TrendEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        if (value != null) {
            return StatusCondition.TrendEnum.fromValue(value);
        }
        return null;
    }
}
