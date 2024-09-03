package com.stardata.observ.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ServiceMap
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceMap {

    @Valid
    private List<@Valid ServiceMapNode> nodes = new ArrayList<>();

    @Valid
    private List<@Valid ServiceMapEdge> edges = new ArrayList<>();

    public ServiceMap() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public ServiceMap(List<@Valid ServiceMapNode> nodes, List<@Valid ServiceMapEdge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public ServiceMap nodes(List<@Valid ServiceMapNode> nodes) {
        this.nodes = nodes;
        return this;
    }

    public ServiceMap addNodesItem(ServiceMapNode nodesItem) {
        if (this.nodes == null) {
            this.nodes = new ArrayList<>();
        }
        this.nodes.add(nodesItem);
        return this;
    }

    /**
     * 通过数组的形式，列出来所有节点的列表
     *
     * @return nodes
     */
    @NotNull
    @Valid
    @Schema(name = "nodes", description = "通过数组的形式，列出来所有节点的列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("nodes")
    public List<@Valid ServiceMapNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<@Valid ServiceMapNode> nodes) {
        this.nodes = nodes;
    }

    public ServiceMap edges(List<@Valid ServiceMapEdge> edges) {
        this.edges = edges;
        return this;
    }

    public ServiceMap addEdgesItem(ServiceMapEdge edgesItem) {
        if (this.edges == null) {
            this.edges = new ArrayList<>();
        }
        this.edges.add(edgesItem);
        return this;
    }

    /**
     * 通过数组的形式，列出来所有服务地图的边长。
     *
     * @return edges
     */
    @NotNull
    @Valid
    @Schema(name = "edges", description = "通过数组的形式，列出来所有服务地图的边长。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("edges")
    public List<@Valid ServiceMapEdge> getEdges() {
        return edges;
    }

    public void setEdges(List<@Valid ServiceMapEdge> edges) {
        this.edges = edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceMap serviceMap = (ServiceMap) o;
        return Objects.equals(this.nodes, serviceMap.nodes) &&
                Objects.equals(this.edges, serviceMap.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes, edges);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ServiceMap {\n");
        sb.append("    nodes: ").append(toIndentedString(nodes)).append("\n");
        sb.append("    edges: ").append(toIndentedString(edges)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
