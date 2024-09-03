package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ServiceMapRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceMapRequest {

    private TimeRange timeCondition;

    private String platform;

    private String cluster;

    private Integer entranceDepth;

    private Integer exitDepth;

    public ServiceMapRequest() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public ServiceMapRequest(TimeRange timeCondition, String platform, String cluster, Integer entranceDepth, Integer exitDepth) {
        this.timeCondition = timeCondition;
        this.platform = platform;
        this.cluster = cluster;
        this.entranceDepth = entranceDepth;
        this.exitDepth = exitDepth;
    }

    public ServiceMapRequest timeCondition(TimeRange timeCondition) {
        this.timeCondition = timeCondition;
        return this;
    }

    /**
     * Get timeCondition
     *
     * @return timeCondition
     */
    @NotNull
    @Valid
    @Schema(name = "timeCondition", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("timeCondition")
    public TimeRange getTimeCondition() {
        return timeCondition;
    }

    public void setTimeCondition(TimeRange timeCondition) {
        this.timeCondition = timeCondition;
    }

    public ServiceMapRequest platform(String platform) {
        this.platform = platform;
        return this;
    }

    /**
     * 从K8S api或自定义的获得的数据中心平面编码
     *
     * @return platform
     */
    @NotNull
    @Schema(name = "platform", description = "从K8S api或自定义的获得的数据中心平面编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("platform")
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public ServiceMapRequest cluster(String cluster) {
        this.cluster = cluster;
        return this;
    }

    /**
     * 从k8s API获得的应用集群编码。原则上，全系统的应用集群编码是唯一的。
     *
     * @return cluster
     */
    @NotNull
    @Schema(name = "cluster", description = "从k8s API获得的应用集群编码。原则上，全系统的应用集群编码是唯一的。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("cluster")
    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public ServiceMapRequest entranceDepth(Integer entranceDepth) {
        this.entranceDepth = entranceDepth;
        return this;
    }

    /**
     * 用于计算数据层次
     *
     * @return entranceDepth
     */
    @NotNull
    @Schema(name = "entranceDepth", description = "用于计算数据层次", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("entranceDepth")
    public Integer getEntranceDepth() {
        return entranceDepth;
    }

    public void setEntranceDepth(Integer entranceDepth) {
        this.entranceDepth = entranceDepth;
    }

    public ServiceMapRequest exitDepth(Integer exitDepth) {
        this.exitDepth = exitDepth;
        return this;
    }

    /**
     * 用于计算数据层次
     *
     * @return exitDepth
     */
    @NotNull
    @Schema(name = "exitDepth", description = "用于计算数据层次", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("exitDepth")
    public Integer getExitDepth() {
        return exitDepth;
    }

    public void setExitDepth(Integer exitDepth) {
        this.exitDepth = exitDepth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceMapRequest serviceMapRequest = (ServiceMapRequest) o;
        return Objects.equals(this.timeCondition, serviceMapRequest.timeCondition) &&
                Objects.equals(this.platform, serviceMapRequest.platform) &&
                Objects.equals(this.cluster, serviceMapRequest.cluster) &&
                Objects.equals(this.entranceDepth, serviceMapRequest.entranceDepth) &&
                Objects.equals(this.exitDepth, serviceMapRequest.exitDepth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeCondition, platform, cluster, entranceDepth, exitDepth);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ServiceMapRequest {\n");
        sb.append("    timeCondition: ").append(toIndentedString(timeCondition)).append("\n");
        sb.append("    platform: ").append(toIndentedString(platform)).append("\n");
        sb.append("    cluster: ").append(toIndentedString(cluster)).append("\n");
        sb.append("    entranceDepth: ").append(toIndentedString(entranceDepth)).append("\n");
        sb.append("    exitDepth: ").append(toIndentedString(exitDepth)).append("\n");
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
