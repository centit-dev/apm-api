package com.stardata.observ.common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.stardata.observ.vo.StatusCondition;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-01-22 23:01
 */
public class TrendEnumSerializer extends JsonSerializer<StatusCondition.TrendEnum> {
    @Override
    public void serialize(StatusCondition.TrendEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            gen.writeString(value.getValue());
        } else {
            gen.writeNull();
        }
    }
}
