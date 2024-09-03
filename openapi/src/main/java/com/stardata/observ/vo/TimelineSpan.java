package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * TimelineSpan
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TimelineSpan {

    private Long timestamp;

    private String traceId;

    private String spanId;

    private String parentSpanId;

    private String serviceName;

    private String spanName;

    private NameDisplayPair spanKind;

    private Long duration;

    private NameDisplayPair faultKind;

    private Boolean isCause;

    private NameDisplayPair application;

    private NameDisplayPair platform;

    private String cluster;

    public TimelineSpan() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public TimelineSpan(Long timestamp, String traceId, String spanId, String serviceName, String spanName, NameDisplayPair spanKind, Long duration, Boolean isCause, NameDisplayPair application, NameDisplayPair platform, String cluster) {
        this.timestamp = timestamp;
        this.traceId = traceId;
        this.spanId = spanId;
        this.serviceName = serviceName;
        this.spanName = spanName;
        this.spanKind = spanKind;
        this.duration = duration;
        this.isCause = isCause;
        this.application = application;
        this.platform = platform;
        this.cluster = cluster;
    }

    public TimelineSpan timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * long类型的unix时间戳（从1970-1-1 00:00:00计算的毫秒数）
     *
     * @return timestamp
     */
    @NotNull
    @Schema(name = "timestamp", description = "long类型的unix时间戳（从1970-1-1 00:00:00计算的毫秒数）", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public TimelineSpan traceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    /**
     * 调用链ID
     *
     * @return traceId
     */
    @NotNull
    @Schema(name = "traceId", description = "调用链ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("traceId")
    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public TimelineSpan spanId(String spanId) {
        this.spanId = spanId;
        return this;
    }

    /**
     * 某服务的调用跨度ID
     *
     * @return spanId
     */
    @NotNull
    @Schema(name = "spanId", description = "某服务的调用跨度ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("spanId")
    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public TimelineSpan parentSpanId(String parentSpanId) {
        this.parentSpanId = parentSpanId;
        return this;
    }

    /**
     * 某服务的上级调用跨度ID
     *
     * @return parentSpanId
     */

    @Schema(name = "parentSpanId", description = "某服务的上级调用跨度ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("parentSpanId")
    public String getParentSpanId() {
        return parentSpanId;
    }

    public void setParentSpanId(String parentSpanId) {
        this.parentSpanId = parentSpanId;
    }

    public TimelineSpan serviceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    /**
     * Get serviceName
     *
     * @return serviceName
     */
    @NotNull
    @Schema(name = "serviceName", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("serviceName")
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public TimelineSpan spanName(String spanName) {
        this.spanName = spanName;
        return this;
    }

    /**
     * 该Span所执行的操作名称，可能是RESTful PATH、java方法名、数据库操作命令等。
     *
     * @return spanName
     */
    @NotNull
    @Schema(name = "spanName", description = "该Span所执行的操作名称，可能是RESTful PATH、java方法名、数据库操作命令等。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("spanName")
    public String getSpanName() {
        return spanName;
    }

    public void setSpanName(String spanName) {
        this.spanName = spanName;
    }

    public TimelineSpan spanKind(NameDisplayPair spanKind) {
        this.spanKind = spanKind;
        return this;
    }

    /**
     * Get spanKind
     *
     * @return spanKind
     */
    @NotNull
    @Valid
    @Schema(name = "spanKind", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("spanKind")
    public NameDisplayPair getSpanKind() {
        return spanKind;
    }

    public void setSpanKind(NameDisplayPair spanKind) {
        this.spanKind = spanKind;
    }

    public TimelineSpan duration(Long duration) {
        this.duration = duration;
        return this;
    }

    /**
     * Get duration
     *
     * @return duration
     */
    @NotNull
    @Schema(name = "duration", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("duration")
    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public TimelineSpan faultKind(NameDisplayPair faultKind) {
        this.faultKind = faultKind;
        return this;
    }

    /**
     * Get faultKind
     *
     * @return faultKind
     */
    @Valid
    @Schema(name = "faultKind", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("faultKind")
    public NameDisplayPair getFaultKind() {
        return faultKind;
    }

    public void setFaultKind(NameDisplayPair faultKind) {
        this.faultKind = faultKind;
    }

    public TimelineSpan isCause(Boolean isCause) {
        this.isCause = isCause;
        return this;
    }

    /**
     * 一般为包含异常类型的叶子Span
     *
     * @return isCause
     */
    @NotNull
    @Schema(name = "isCause", description = "一般为包含异常类型的叶子Span", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("isCause")
    public Boolean getIsCause() {
        return isCause;
    }

    public void setIsCause(Boolean isCause) {
        this.isCause = isCause;
    }

    public TimelineSpan application(NameDisplayPair application) {
        this.application = application;
        return this;
    }

    /**
     * Get application
     *
     * @return application
     */
    @NotNull
    @Valid
    @Schema(name = "application", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("application")
    public NameDisplayPair getApplication() {
        return application;
    }

    public void setApplication(NameDisplayPair application) {
        this.application = application;
    }

    public TimelineSpan platform(NameDisplayPair platform) {
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

    public TimelineSpan cluster(String cluster) {
        this.cluster = cluster;
        return this;
    }

    /**
     * 服务运行时跨度所在的集群
     *
     * @return cluster
     */
    @NotNull
    @Schema(name = "cluster", description = "服务运行时跨度所在的集群", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("cluster")
    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimelineSpan timelineSpan = (TimelineSpan) o;
        return Objects.equals(this.timestamp, timelineSpan.timestamp) &&
                Objects.equals(this.traceId, timelineSpan.traceId) &&
                Objects.equals(this.spanId, timelineSpan.spanId) &&
                Objects.equals(this.parentSpanId, timelineSpan.parentSpanId) &&
                Objects.equals(this.serviceName, timelineSpan.serviceName) &&
                Objects.equals(this.spanName, timelineSpan.spanName) &&
                Objects.equals(this.spanKind, timelineSpan.spanKind) &&
                Objects.equals(this.duration, timelineSpan.duration) &&
                Objects.equals(this.faultKind, timelineSpan.faultKind) &&
                Objects.equals(this.isCause, timelineSpan.isCause) &&
                Objects.equals(this.application, timelineSpan.application) &&
                Objects.equals(this.platform, timelineSpan.platform) &&
                Objects.equals(this.cluster, timelineSpan.cluster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, traceId, spanId, parentSpanId, serviceName, spanName, spanKind, duration, faultKind, isCause, application, platform, cluster);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimelineSpan {\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    traceId: ").append(toIndentedString(traceId)).append("\n");
        sb.append("    spanId: ").append(toIndentedString(spanId)).append("\n");
        sb.append("    parentSpanId: ").append(toIndentedString(parentSpanId)).append("\n");
        sb.append("    serviceName: ").append(toIndentedString(serviceName)).append("\n");
        sb.append("    spanName: ").append(toIndentedString(spanName)).append("\n");
        sb.append("    spanKind: ").append(toIndentedString(spanKind)).append("\n");
        sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
        sb.append("    faultKind: ").append(toIndentedString(faultKind)).append("\n");
        sb.append("    isCause: ").append(toIndentedString(isCause)).append("\n");
        sb.append("    application: ").append(toIndentedString(application)).append("\n");
        sb.append("    platform: ").append(toIndentedString(platform)).append("\n");
        sb.append("    cluster: ").append(toIndentedString(cluster)).append("\n");
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
