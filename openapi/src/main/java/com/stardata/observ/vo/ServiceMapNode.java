package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ServiceMapNode
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceMapNode {

    private String cluster;

    private NameDisplayPair platform;

    private NameDisplayPair software;
    private TypeEnum type;
    private Integer instanceCount;
    private Integer successCount;
    private Integer systemFaultCount;
    private Integer businessFaultCount;

    public ServiceMapNode() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public ServiceMapNode(String cluster, NameDisplayPair platform, NameDisplayPair software, TypeEnum type, Integer instanceCount, Integer successCount, Integer systemFaultCount, Integer businessFaultCount) {
        this.cluster = cluster;
        this.platform = platform;
        this.software = software;
        this.type = type;
        this.instanceCount = instanceCount;
        this.successCount = successCount;
        this.systemFaultCount = systemFaultCount;
        this.businessFaultCount = businessFaultCount;
    }

    public ServiceMapNode cluster(String cluster) {
        this.cluster = cluster;
        return this;
    }

    /**
     * 服务地图的节点名称，使用应用集群编码（K8S API获取）标识。
     *
     * @return cluster
     */
    @NotNull
    @Schema(name = "cluster", description = "服务地图的节点名称，使用应用集群编码（K8S API获取）标识。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("cluster")
    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public ServiceMapNode platform(NameDisplayPair platform) {
        this.platform = platform;
        return this;
    }

    /**
     * Get platform
     *
     * @return platform
     */
    @NotNull
    @Valid
    @Schema(name = "platform", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("platform")
    public NameDisplayPair getPlatform() {
        return platform;
    }

    public void setPlatform(NameDisplayPair platform) {
        this.platform = platform;
    }

    public ServiceMapNode software(NameDisplayPair software) {
        this.software = software;
        return this;
    }

    /**
     * Get software
     *
     * @return software
     */
    @NotNull
    @Valid
    @Schema(name = "software", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("software")
    public NameDisplayPair getSoftware() {
        return software;
    }

    public void setSoftware(NameDisplayPair software) {
        this.software = software;
    }

    public ServiceMapNode type(TypeEnum type) {
        this.type = type;
        return this;
    }

    /**
     * 该节点运行的程序类型，有app-cluster，software两种
     *
     * @return type
     */
    @NotNull
    @Schema(name = "type", description = "该节点运行的程序类型，有app-cluster，software两种", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("type")
    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public ServiceMapNode instanceCount(Integer instanceCount) {
        this.instanceCount = instanceCount;
        return this;
    }

    /**
     * 该服务地图的集群节点下，满足查询条件的应用实例数量。
     *
     * @return instanceCount
     */
    @NotNull
    @Schema(name = "instanceCount", description = "该服务地图的集群节点下，满足查询条件的应用实例数量。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("instanceCount")
    public Integer getInstanceCount() {
        return instanceCount;
    }

    public void setInstanceCount(Integer instanceCount) {
        this.instanceCount = instanceCount;
    }

    public ServiceMapNode successCount(Integer successCount) {
        this.successCount = successCount;
        return this;
    }

    /**
     * 该集群在指定条件下的span被调用总成功次数。
     *
     * @return successCount
     */
    @NotNull
    @Schema(name = "successCount", description = "该集群在指定条件下的span被调用总成功次数。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("successCount")
    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public ServiceMapNode systemFaultCount(Integer systemFaultCount) {
        this.systemFaultCount = systemFaultCount;
        return this;
    }

    /**
     * 该集群在指定条件下的span被调用总系统报错次数。
     *
     * @return systemFaultCount
     */
    @NotNull
    @Schema(name = "systemFaultCount", description = "该集群在指定条件下的span被调用总系统报错次数。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("systemFaultCount")
    public Integer getSystemFaultCount() {
        return systemFaultCount;
    }

    public void setSystemFaultCount(Integer systemFaultCount) {
        this.systemFaultCount = systemFaultCount;
    }

    public ServiceMapNode businessFaultCount(Integer businessFaultCount) {
        this.businessFaultCount = businessFaultCount;
        return this;
    }

    /**
     * 该集群在指定条件下的span被调用总业务报错次数。
     *
     * @return businessFaultCount
     */
    @NotNull
    @Schema(name = "businessFaultCount", description = "该集群在指定条件下的span被调用总业务报错次数。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("businessFaultCount")
    public Integer getBusinessFaultCount() {
        return businessFaultCount;
    }

    public void setBusinessFaultCount(Integer businessFaultCount) {
        this.businessFaultCount = businessFaultCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceMapNode serviceMapNode = (ServiceMapNode) o;
        return Objects.equals(this.cluster, serviceMapNode.cluster) &&
                Objects.equals(this.platform, serviceMapNode.platform) &&
                Objects.equals(this.software, serviceMapNode.software) &&
                Objects.equals(this.type, serviceMapNode.type) &&
                Objects.equals(this.instanceCount, serviceMapNode.instanceCount) &&
                Objects.equals(this.successCount, serviceMapNode.successCount) &&
                Objects.equals(this.systemFaultCount, serviceMapNode.systemFaultCount) &&
                Objects.equals(this.businessFaultCount, serviceMapNode.businessFaultCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cluster, platform, software, type, instanceCount, successCount, systemFaultCount, businessFaultCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ServiceMapNode {\n");
        sb.append("    cluster: ").append(toIndentedString(cluster)).append("\n");
        sb.append("    platform: ").append(toIndentedString(platform)).append("\n");
        sb.append("    software: ").append(toIndentedString(software)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    instanceCount: ").append(toIndentedString(instanceCount)).append("\n");
        sb.append("    successCount: ").append(toIndentedString(successCount)).append("\n");
        sb.append("    systemFaultCount: ").append(toIndentedString(systemFaultCount)).append("\n");
        sb.append("    businessFaultCount: ").append(toIndentedString(businessFaultCount)).append("\n");
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

    /**
     * 该节点运行的程序类型，有app-cluster，software两种
     */
    public enum TypeEnum {
        APP_CLUSTER("APP_CLUSTER"),

        SOFTWARE("SOFTWARE");

        private String value;

        TypeEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static TypeEnum fromValue(String value) {
            for (TypeEnum b : TypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}
