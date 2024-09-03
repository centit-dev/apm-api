package com.stardata.observ.domain;

import java.util.Map;
import java.util.Set;

import com.stardata.observ.vo.ServiceMapNode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A data transfer object
 */
@Data
@ToString(exclude = {"clients", "servers", "edges"})
@EqualsAndHashCode(exclude = {"clients", "servers", "edges"})
public class ServiceMapNodeDto {

    private String appCluster;

    private String platform;

    /**
     * application type of this node. e.g. "java", "go"
     * If there is no application type, use server.software from the client side
     */
    private String applicationType;

    /**
     * node type of this node. e.g. "app-cluster", "software"
     */
    private ServiceMapNode.TypeEnum nodeType;

    private Set<String> instanceNames;

    /**
     * a map using client cluster as key and client node as value
     */
    private Map<String, ServiceMapNodeDto> clients;

    /**
     * a map using server cluster as key and server node as value
     */
    private Map<String, ServiceMapNodeDto> servers;

    /**
     * a map using app cluster as key and edge as value
     */
    private Map<String, ServiceMapEdgeDto> edges;

    private int successCount;
    private int businessFaultCount;
    private int systemFaultCount;

    @Data
    public static class ServiceMapEdgeDto {
        private String serverSoftware;
        private int callCount;
        private int successCount;
        private int businessFaultCount;
        private int systemFaultCount;
        private long duration;
    }
}
