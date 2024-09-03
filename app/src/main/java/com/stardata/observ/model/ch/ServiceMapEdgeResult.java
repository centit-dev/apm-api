package com.stardata.observ.model.ch;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-03-04 21:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceMapEdgeResult {
    private String fromService;

    private String toService;

    private String platformName;

    private int callCount;

    private int businessFaultCount;

    private int systemFaultCount;

    private Long duration;

    private List<String> instanceNames;

    private String clientApplicationType;

    private String clientApplicationCluster;

    private String clientSoftware;

    private String serverApplicationType;

    private String serverApplicationCluster;

    private String serverSoftware;
}
