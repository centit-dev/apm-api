package com.stardata.observ.model.ch;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MapFieldCondition {

    private String type; // D:Date / N:Number
    private String fieldName;
    private String operator;
    private Object value;

}
