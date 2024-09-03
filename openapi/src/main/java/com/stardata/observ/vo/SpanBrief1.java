package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * SpanBrief1
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SpanBrief1 {

    private Long timestamp;

    private String traceId;

    private String spanId;

    private String parentSpanId;

    private String serviceName;

    private String spanName;

    private SpanKind spanKind;

    private NameDisplayPair software;

    private NameDisplayPair application;

    private NameDisplayPair platform;

    private String cluster;

    private String instance;

    private Long duration;

    private SpanStatus status;

    private Integer logCount;

    public SpanBrief1() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public SpanBrief1(String traceId, String spanId, String parentSpanId, String serviceName, SpanKind spanKind, NameDisplayPair application, NameDisplayPair platform, String cluster, Long duration) {
        this.traceId = traceId;
        this.spanId = spanId;
        this.parentSpanId = parentSpanId;
        this.serviceName = serviceName;
        this.spanKind = spanKind;
        this.application = application;
        this.platform = platform;
        this.cluster = cluster;
        this.duration = duration;
    }

    public SpanBrief1 timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * long类型的unix时间戳（从1970-1-1 00:00:00计算的毫秒数）
     *
     * @return timestamp
     */

    @Schema(name = "timestamp", description = "long类型的unix时间戳（从1970-1-1 00:00:00计算的毫秒数）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public SpanBrief1 traceId(String traceId) {
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

    public SpanBrief1 spanId(String spanId) {
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

    public SpanBrief1 parentSpanId(String parentSpanId) {
        this.parentSpanId = parentSpanId;
        return this;
    }

    /**
     * 某服务的上级调用跨度ID
     *
     * @return parentSpanId
     */
    @NotNull
    @Schema(name = "parentSpanId", description = "某服务的上级调用跨度ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("parentSpanId")
    public String getParentSpanId() {
        return parentSpanId;
    }

    public void setParentSpanId(String parentSpanId) {
        this.parentSpanId = parentSpanId;
    }

    public SpanBrief1 serviceName(String serviceName) {
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

    public SpanBrief1 spanName(String spanName) {
        this.spanName = spanName;
        return this;
    }

    /**
     * 该Span所执行的操作名称，可能是RESTful PATH、java方法名、数据库操作命令等。
     *
     * @return spanName
     */

    @Schema(name = "spanName", description = "该Span所执行的操作名称，可能是RESTful PATH、java方法名、数据库操作命令等。", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("spanName")
    public String getSpanName() {
        return spanName;
    }

    public void setSpanName(String spanName) {
        this.spanName = spanName;
    }

    public SpanBrief1 spanKind(SpanKind spanKind) {
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
    public SpanKind getSpanKind() {
        return spanKind;
    }

    public void setSpanKind(SpanKind spanKind) {
        this.spanKind = spanKind;
    }

    public SpanBrief1 software(NameDisplayPair software) {
        this.software = software;
        return this;
    }

    /**
     * Get software
     *
     * @return software
     */
    @Valid
    @Schema(name = "software", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("software")
    public NameDisplayPair getSoftware() {
        return software;
    }

    public void setSoftware(NameDisplayPair software) {
        this.software = software;
    }

    public SpanBrief1 application(NameDisplayPair application) {
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

    public SpanBrief1 platform(NameDisplayPair platform) {
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

    public SpanBrief1 cluster(String cluster) {
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

    public SpanBrief1 instance(String instance) {
        this.instance = instance;
        return this;
    }

    /**
     * 服务运行时跨度所在的实例
     *
     * @return instance
     */

    @Schema(name = "instance", description = "服务运行时跨度所在的实例", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("instance")
    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public SpanBrief1 duration(Long duration) {
        this.duration = duration;
        return this;
    }

    /**
     * 服务跨度运行时延（单位：毫秒）
     *
     * @return duration
     */
    @NotNull
    @Schema(name = "duration", description = "服务跨度运行时延（单位：毫秒）", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("duration")
    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public SpanBrief1 status(SpanStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     */
    @Valid
    @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("status")
    public SpanStatus getStatus() {
        return status;
    }

    public void setStatus(SpanStatus status) {
        this.status = status;
    }

    public SpanBrief1 logCount(Integer logCount) {
        this.logCount = logCount;
        return this;
    }

    /**
     * 显示该Span关联的日志数量
     *
     * @return logCount
     */

    @Schema(name = "logCount", description = "显示该Span关联的日志数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("logCount")
    public Integer getLogCount() {
        return logCount;
    }

    public void setLogCount(Integer logCount) {
        this.logCount = logCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpanBrief1 spanBrief1 = (SpanBrief1) o;
        return Objects.equals(this.timestamp, spanBrief1.timestamp) &&
                Objects.equals(this.traceId, spanBrief1.traceId) &&
                Objects.equals(this.spanId, spanBrief1.spanId) &&
                Objects.equals(this.parentSpanId, spanBrief1.parentSpanId) &&
                Objects.equals(this.serviceName, spanBrief1.serviceName) &&
                Objects.equals(this.spanName, spanBrief1.spanName) &&
                Objects.equals(this.spanKind, spanBrief1.spanKind) &&
                Objects.equals(this.software, spanBrief1.software) &&
                Objects.equals(this.application, spanBrief1.application) &&
                Objects.equals(this.platform, spanBrief1.platform) &&
                Objects.equals(this.cluster, spanBrief1.cluster) &&
                Objects.equals(this.instance, spanBrief1.instance) &&
                Objects.equals(this.duration, spanBrief1.duration) &&
                Objects.equals(this.status, spanBrief1.status) &&
                Objects.equals(this.logCount, spanBrief1.logCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, traceId, spanId, parentSpanId, serviceName, spanName, spanKind, software, application, platform, cluster, instance, duration, status, logCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SpanBrief1 {\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    traceId: ").append(toIndentedString(traceId)).append("\n");
        sb.append("    spanId: ").append(toIndentedString(spanId)).append("\n");
        sb.append("    parentSpanId: ").append(toIndentedString(parentSpanId)).append("\n");
        sb.append("    serviceName: ").append(toIndentedString(serviceName)).append("\n");
        sb.append("    spanName: ").append(toIndentedString(spanName)).append("\n");
        sb.append("    spanKind: ").append(toIndentedString(spanKind)).append("\n");
        sb.append("    software: ").append(toIndentedString(software)).append("\n");
        sb.append("    application: ").append(toIndentedString(application)).append("\n");
        sb.append("    platform: ").append(toIndentedString(platform)).append("\n");
        sb.append("    cluster: ").append(toIndentedString(cluster)).append("\n");
        sb.append("    instance: ").append(toIndentedString(instance)).append("\n");
        sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    logCount: ").append(toIndentedString(logCount)).append("\n");
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
