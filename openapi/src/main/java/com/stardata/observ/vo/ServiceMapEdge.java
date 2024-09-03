package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ServiceMapEdge
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceMapEdge {

    private String fromCluster;

    private String toCluster;

    private Long duration;

    private Integer callCount;

    private Integer successCount;

    private Integer businessFaultCount;

    private Integer systemFaultCount;

    public ServiceMapEdge() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public ServiceMapEdge(String fromCluster, String toCluster, Long duration, Integer callCount, Integer successCount, Integer businessFaultCount, Integer systemFaultCount) {
        this.fromCluster = fromCluster;
        this.toCluster = toCluster;
        this.duration = duration;
        this.callCount = callCount;
        this.successCount = successCount;
        this.businessFaultCount = businessFaultCount;
        this.systemFaultCount = systemFaultCount;
    }

    public ServiceMapEdge fromCluster(String fromCluster) {
        this.fromCluster = fromCluster;
        return this;
    }

    /**
     * 服务地图边长的起点的应用集群编码（K8S API获取）
     *
     * @return fromCluster
     */
    @NotNull
    @Schema(name = "fromCluster", description = "服务地图边长的起点的应用集群编码（K8S API获取）", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("fromCluster")
    public String getFromCluster() {
        return fromCluster;
    }

    public void setFromCluster(String fromCluster) {
        this.fromCluster = fromCluster;
    }

    public ServiceMapEdge toCluster(String toCluster) {
        this.toCluster = toCluster;
        return this;
    }

    /**
     * 服务地图边长的终点的应用集群编码（K8S API获取）
     *
     * @return toCluster
     */
    @NotNull
    @Schema(name = "toCluster", description = "服务地图边长的终点的应用集群编码（K8S API获取）", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("toCluster")
    public String getToCluster() {
        return toCluster;
    }

    public void setToCluster(String toCluster) {
        this.toCluster = toCluster;
    }

    public ServiceMapEdge duration(Long duration) {
        this.duration = duration;
        return this;
    }

    /**
     * 显示在边长上的集群间span调用时延值，单位毫秒。该时延值具体显示平均、还是P50、还是P75、还是P90等时延，取决于系统后台配置参数dag_latency。
     *
     * @return duration
     */
    @NotNull
    @Schema(name = "duration", description = "显示在边长上的集群间span调用时延值，单位毫秒。该时延值具体显示平均、还是P50、还是P75、还是P90等时延，取决于系统后台配置参数dag_latency。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("duration")
    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public ServiceMapEdge callCount(Integer callCount) {
        this.callCount = callCount;
        return this;
    }

    /**
     * 显示在边长上的集群间span调用的总次数。
     *
     * @return callCount
     */
    @NotNull
    @Schema(name = "callCount", description = "显示在边长上的集群间span调用的总次数。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("callCount")
    public Integer getCallCount() {
        return callCount;
    }

    public void setCallCount(Integer callCount) {
        this.callCount = callCount;
    }

    public ServiceMapEdge successCount(Integer successCount) {
        this.successCount = successCount;
        return this;
    }

    /**
     * Get successCount
     *
     * @return successCount
     */
    @NotNull
    @Schema(name = "successCount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("successCount")
    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public ServiceMapEdge businessFaultCount(Integer businessFaultCount) {
        this.businessFaultCount = businessFaultCount;
        return this;
    }

    /**
     * Get businessFaultCount
     *
     * @return businessFaultCount
     */
    @NotNull
    @Schema(name = "businessFaultCount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("businessFaultCount")
    public Integer getBusinessFaultCount() {
        return businessFaultCount;
    }

    public void setBusinessFaultCount(Integer businessFaultCount) {
        this.businessFaultCount = businessFaultCount;
    }

    public ServiceMapEdge systemFaultCount(Integer systemFaultCount) {
        this.systemFaultCount = systemFaultCount;
        return this;
    }

    /**
     * Get systemFaultCount
     *
     * @return systemFaultCount
     */
    @NotNull
    @Schema(name = "systemFaultCount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("systemFaultCount")
    public Integer getSystemFaultCount() {
        return systemFaultCount;
    }

    public void setSystemFaultCount(Integer systemFaultCount) {
        this.systemFaultCount = systemFaultCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceMapEdge serviceMapEdge = (ServiceMapEdge) o;
        return Objects.equals(this.fromCluster, serviceMapEdge.fromCluster) &&
                Objects.equals(this.toCluster, serviceMapEdge.toCluster) &&
                Objects.equals(this.duration, serviceMapEdge.duration) &&
                Objects.equals(this.callCount, serviceMapEdge.callCount) &&
                Objects.equals(this.successCount, serviceMapEdge.successCount) &&
                Objects.equals(this.businessFaultCount, serviceMapEdge.businessFaultCount) &&
                Objects.equals(this.systemFaultCount, serviceMapEdge.systemFaultCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromCluster, toCluster, duration, callCount, successCount, businessFaultCount, systemFaultCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ServiceMapEdge {\n");
        sb.append("    fromCluster: ").append(toIndentedString(fromCluster)).append("\n");
        sb.append("    toCluster: ").append(toIndentedString(toCluster)).append("\n");
        sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
        sb.append("    callCount: ").append(toIndentedString(callCount)).append("\n");
        sb.append("    successCount: ").append(toIndentedString(successCount)).append("\n");
        sb.append("    businessFaultCount: ").append(toIndentedString(businessFaultCount)).append("\n");
        sb.append("    systemFaultCount: ").append(toIndentedString(systemFaultCount)).append("\n");
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
