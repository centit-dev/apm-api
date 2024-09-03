package com.stardata.observ.model.pg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-02-19 21:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionKeyDto {
    private Integer id;

    private String name;

    private String displayName;

    private String dataType;
}
