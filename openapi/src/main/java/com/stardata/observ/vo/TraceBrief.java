package com.stardata.observ.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * TraceBrief
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TraceBrief {

    private String traceId;
    private StatusEnum status;
    private String endpointAddress;
    private Long startTime;
    private Long duration;
    private Integer spanCount;
    @Valid
    private List<@Valid NameDisplayPair> platforms = new ArrayList<>();
    private String appCluster;
    @Valid
    private List<String> instanceNames = new ArrayList<>();
    private String firstService;
    @Valid
    private List<@Valid NameDisplayPair> applications = new ArrayList<>();

    public TraceBrief() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public TraceBrief(String traceId, StatusEnum status, String endpointAddress, Long startTime, Long duration, Integer spanCount, List<@Valid NameDisplayPair> platforms, String appCluster, List<String> instanceNames, String firstService, List<@Valid NameDisplayPair> applications) {
        this.traceId = traceId;
        this.status = status;
        this.endpointAddress = endpointAddress;
        this.startTime = startTime;
        this.duration = duration;
        this.spanCount = spanCount;
        this.platforms = platforms;
        this.appCluster = appCluster;
        this.instanceNames = instanceNames;
        this.firstService = firstService;
        this.applications = applications;
    }

    public TraceBrief traceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    /**
     * Get traceId
     *
     * @return traceId
     */
    @NotNull
    @Schema(name = "traceId", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("traceId")
    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public TraceBrief status(StatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * TODO: 是否为枚举?
     *
     * @return status
     */
    @NotNull
    @Schema(name = "status", description = "TODO: 是否为枚举?", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("status")
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public TraceBrief endpointAddress(String endpointAddress) {
        this.endpointAddress = endpointAddress;
        return this;
    }

    /**
     * 客户端调用服务端的端点地址，可能是域名:端口，也可能是ip:port这种格式。
     *
     * @return endpointAddress
     */
    @NotNull
    @Schema(name = "endpointAddress", description = "客户端调用服务端的端点地址，可能是域名:端口，也可能是ip:port这种格式。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("endpointAddress")
    public String getEndpointAddress() {
        return endpointAddress;
    }

    public void setEndpointAddress(String endpointAddress) {
        this.endpointAddress = endpointAddress;
    }

    public TraceBrief startTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Trace root span开始时间，整数，Unix时间戳，单位毫秒
     *
     * @return startTime
     */
    @NotNull
    @Schema(name = "startTime", description = "Trace root span开始时间，整数，Unix时间戳，单位毫秒", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("startTime")
    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public TraceBrief duration(Long duration) {
        this.duration = duration;
        return this;
    }

    /**
     * 整个Trace的响应时延（其实是root span时延），单位毫秒
     *
     * @return duration
     */
    @NotNull
    @Schema(name = "duration", description = "整个Trace的响应时延（其实是root span时延），单位毫秒", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("duration")
    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public TraceBrief spanCount(Integer spanCount) {
        this.spanCount = spanCount;
        return this;
    }

    /**
     * Get spanCount
     *
     * @return spanCount
     */
    @NotNull
    @Schema(name = "spanCount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("spanCount")
    public Integer getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(Integer spanCount) {
        this.spanCount = spanCount;
    }

    public TraceBrief platforms(List<@Valid NameDisplayPair> platforms) {
        this.platforms = platforms;
        return this;
    }

    public TraceBrief addPlatformsItem(NameDisplayPair platformsItem) {
        if (this.platforms == null) {
            this.platforms = new ArrayList<>();
        }
        this.platforms.add(platformsItem);
        return this;
    }

    /**
     * 该trace运行所在的平面，可能有多个（假设支持跨平面调用）
     *
     * @return platforms
     */
    @NotNull
    @Valid
    @Schema(name = "platforms", description = "该trace运行所在的平面，可能有多个（假设支持跨平面调用）", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("platforms")
    public List<@Valid NameDisplayPair> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<@Valid NameDisplayPair> platforms) {
        this.platforms = platforms;
    }

    public TraceBrief appCluster(String appCluster) {
        this.appCluster = appCluster;
        return this;
    }

    /**
     * Get appCluster
     *
     * @return appCluster
     */
    @NotNull
    @Schema(name = "appCluster", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("appCluster")
    public String getAppCluster() {
        return appCluster;
    }

    public void setAppCluster(String appCluster) {
        this.appCluster = appCluster;
    }

    public TraceBrief instanceNames(List<String> instanceNames) {
        this.instanceNames = instanceNames;
        return this;
    }

    public TraceBrief addInstanceNamesItem(String instanceNamesItem) {
        if (this.instanceNames == null) {
            this.instanceNames = new ArrayList<>();
        }
        this.instanceNames.add(instanceNamesItem);
        return this;
    }

    /**
     * Get instanceNames
     *
     * @return instanceNames
     */
    @NotNull
    @Schema(name = "instanceNames", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("instanceNames")
    public List<String> getInstanceNames() {
        return instanceNames;
    }

    public void setInstanceNames(List<String> instanceNames) {
        this.instanceNames = instanceNames;
    }

    public TraceBrief firstService(String firstService) {
        this.firstService = firstService;
        return this;
    }

    /**
     * Get firstService
     *
     * @return firstService
     */
    @NotNull
    @Schema(name = "firstService", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("firstService")
    public String getFirstService() {
        return firstService;
    }

    public void setFirstService(String firstService) {
        this.firstService = firstService;
    }

    public TraceBrief applications(List<@Valid NameDisplayPair> applications) {
        this.applications = applications;
        return this;
    }

    public TraceBrief addApplicationsItem(NameDisplayPair applicationsItem) {
        if (this.applications == null) {
            this.applications = new ArrayList<>();
        }
        this.applications.add(applicationsItem);
        return this;
    }

    /**
     * Get applications
     *
     * @return applications
     */
    @NotNull
    @Valid
    @Schema(name = "applications", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("applications")
    public List<@Valid NameDisplayPair> getApplications() {
        return applications;
    }

    public void setApplications(List<@Valid NameDisplayPair> applications) {
        this.applications = applications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TraceBrief traceBrief = (TraceBrief) o;
        return Objects.equals(this.traceId, traceBrief.traceId) &&
                Objects.equals(this.status, traceBrief.status) &&
                Objects.equals(this.endpointAddress, traceBrief.endpointAddress) &&
                Objects.equals(this.startTime, traceBrief.startTime) &&
                Objects.equals(this.duration, traceBrief.duration) &&
                Objects.equals(this.spanCount, traceBrief.spanCount) &&
                Objects.equals(this.platforms, traceBrief.platforms) &&
                Objects.equals(this.appCluster, traceBrief.appCluster) &&
                Objects.equals(this.instanceNames, traceBrief.instanceNames) &&
                Objects.equals(this.firstService, traceBrief.firstService) &&
                Objects.equals(this.applications, traceBrief.applications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(traceId, status, endpointAddress, startTime, duration, spanCount, platforms, appCluster, instanceNames, firstService, applications);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TraceBrief {\n");
        sb.append("    traceId: ").append(toIndentedString(traceId)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    endpointAddress: ").append(toIndentedString(endpointAddress)).append("\n");
        sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
        sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
        sb.append("    spanCount: ").append(toIndentedString(spanCount)).append("\n");
        sb.append("    platforms: ").append(toIndentedString(platforms)).append("\n");
        sb.append("    appCluster: ").append(toIndentedString(appCluster)).append("\n");
        sb.append("    instanceNames: ").append(toIndentedString(instanceNames)).append("\n");
        sb.append("    firstService: ").append(toIndentedString(firstService)).append("\n");
        sb.append("    applications: ").append(toIndentedString(applications)).append("\n");
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
     * TODO: 是否为枚举?
     */
    public enum StatusEnum {
        UNKNOWN("Unknown"),

        BUSINESSFAULT("BusinessFault"),

        SYSTEMFAULT("SystemFault"),

        SUCCESS("Success"),

        TIMEOUT("Timeout");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static StatusEnum fromValue(String value) {
            for (StatusEnum b : StatusEnum.values()) {
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
