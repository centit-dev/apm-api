package com.stardata.observ.model.ch;

import java.util.List;

import lombok.Data;

@Data
public class Instance {
    private String name;
    private String clusterName;
    private List<String> serviceNames;
    private List<String> applicationTypes;
    private List<ServerSoftware> serverSoftwares;

    @Data
    public static class ServerSoftware {
        private String type;
        private String host;
    }
}
