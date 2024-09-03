package com.stardata.observ.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 可能是列表，也可能是对象，也可能不返回。
 */

@Schema(name = "ServiceMapResponse_data", description = "可能是列表，也可能是对象，也可能不返回。")
@JsonTypeName("ServiceMapResponse_data")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceMapResponseData {

    @Valid
    private List<@Valid ServiceMapNode> nodes = new ArrayList<>();

    @Valid
    private List<@Valid ServiceMapEdge> edges = new ArrayList<>();

    public ServiceMapResponseData() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public ServiceMapResponseData(List<@Valid ServiceMapNode> nodes, List<@Valid ServiceMapEdge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public ServiceMapResponseData nodes(List<@Valid ServiceMapNode> nodes) {
        this.nodes = nodes;
        return this;
    }

    public ServiceMapResponseData addNodesItem(ServiceMapNode nodesItem) {
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

    public ServiceMapResponseData edges(List<@Valid ServiceMapEdge> edges) {
        this.edges = edges;
        return this;
    }

    public ServiceMapResponseData addEdgesItem(ServiceMapEdge edgesItem) {
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
        ServiceMapResponseData serviceMapResponseData = (ServiceMapResponseData) o;
        return Objects.equals(this.nodes, serviceMapResponseData.nodes) &&
                Objects.equals(this.edges, serviceMapResponseData.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes, edges);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ServiceMapResponseData {\n");
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
