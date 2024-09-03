package com.stardata.observ.model.pg;

import lombok.Data;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-17 23:12
 */
@Data
public class FaultKind {

    private String trending;
    private String serviceName;
    private String spanName;
    private String faultKind;
}
