package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * InitialCauseSpan
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class InitialCauseSpan {

    private String traceId;

    private String spanId;

    private String rootServiceName;

    private String rootSpanName;

    private String serviceName;

    private String spanName;

    private SpanKind spanKind;

    private SpanStatus status;

    private NameDisplayPair platform;

    private String cluster;

    private String instance;

    private Long timestamp;

    private Long duration;

    private Long gap;

    private Long selfDuration;

    private String localAddress;

    private String peerAddress;

    public InitialCauseSpan() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public InitialCauseSpan(String traceId, String spanId, String rootServiceName, String rootSpanName, String serviceName, String spanName, SpanStatus status, NameDisplayPair platform, String cluster, String instance, Long timestamp, Long duration, Long gap, Long selfDuration, String localAddress, String peerAddress) {
        this.traceId = traceId;
        this.spanId = spanId;
        this.rootServiceName = rootServiceName;
        this.rootSpanName = rootSpanName;
        this.serviceName = serviceName;
        this.spanName = spanName;
        this.status = status;
        this.platform = platform;
        this.cluster = cluster;
        this.instance = instance;
        this.timestamp = timestamp;
        this.duration = duration;
        this.gap = gap;
        this.selfDuration = selfDuration;
        this.localAddress = localAddress;
        this.peerAddress = peerAddress;
    }

    public InitialCauseSpan traceId(String traceId) {
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

    public InitialCauseSpan spanId(String spanId) {
        this.spanId = spanId;
        return this;
    }

    /**
     * Get spanId
     *
     * @return spanId
     */
    @NotNull
    @Schema(name = "spanId", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("spanId")
    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public InitialCauseSpan rootServiceName(String rootServiceName) {
        this.rootServiceName = rootServiceName;
        return this;
    }

    /**
     * Get rootServiceName
     *
     * @return rootServiceName
     */
    @NotNull
    @Schema(name = "rootServiceName", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("rootServiceName")
    public String getRootServiceName() {
        return rootServiceName;
    }

    public void setRootServiceName(String rootServiceName) {
        this.rootServiceName = rootServiceName;
    }

    public InitialCauseSpan rootSpanName(String rootSpanName) {
        this.rootSpanName = rootSpanName;
        return this;
    }

    /**
     * Get rootSpanName
     *
     * @return rootSpanName
     */
    @NotNull
    @Schema(name = "rootSpanName", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("rootSpanName")
    public String getRootSpanName() {
        return rootSpanName;
    }

    public void setRootSpanName(String rootSpanName) {
        this.rootSpanName = rootSpanName;
    }

    public InitialCauseSpan serviceName(String serviceName) {
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

    public InitialCauseSpan spanName(String spanName) {
        this.spanName = spanName;
        return this;
    }

    /**
     * Get spanName
     *
     * @return spanName
     */
    @NotNull
    @Schema(name = "spanName", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("spanName")
    public String getSpanName() {
        return spanName;
    }

    public void setSpanName(String spanName) {
        this.spanName = spanName;
    }

    public InitialCauseSpan spanKind(SpanKind spanKind) {
        this.spanKind = spanKind;
        return this;
    }

    /**
     * Get spanKind
     *
     * @return spanKind
     */
    @Valid
    @Schema(name = "spanKind", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("spanKind")
    public SpanKind getSpanKind() {
        return spanKind;
    }

    public void setSpanKind(SpanKind spanKind) {
        this.spanKind = spanKind;
    }

    public InitialCauseSpan status(SpanStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     */
    @NotNull
    @Valid
    @Schema(name = "status", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("status")
    public SpanStatus getStatus() {
        return status;
    }

    public void setStatus(SpanStatus status) {
        this.status = status;
    }

    public InitialCauseSpan platform(NameDisplayPair platform) {
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

    public InitialCauseSpan cluster(String cluster) {
        this.cluster = cluster;
        return this;
    }

    /**
     * Get cluster
     *
     * @return cluster
     */
    @NotNull
    @Schema(name = "cluster", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("cluster")
    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public InitialCauseSpan instance(String instance) {
        this.instance = instance;
        return this;
    }

    /**
     * Get instance
     *
     * @return instance
     */
    @NotNull
    @Schema(name = "instance", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("instance")
    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public InitialCauseSpan timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Get timestamp
     *
     * @return timestamp
     */
    @NotNull
    @Schema(name = "timestamp", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public InitialCauseSpan duration(Long duration) {
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

    public InitialCauseSpan gap(Long gap) {
        this.gap = gap;
        return this;
    }

    /**
     * Get gap
     *
     * @return gap
     */
    @NotNull
    @Schema(name = "gap", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("gap")
    public Long getGap() {
        return gap;
    }

    public void setGap(Long gap) {
        this.gap = gap;
    }

    public InitialCauseSpan selfDuration(Long selfDuration) {
        this.selfDuration = selfDuration;
        return this;
    }

    /**
     * Get selfDuration
     *
     * @return selfDuration
     */
    @NotNull
    @Schema(name = "selfDuration", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("selfDuration")
    public Long getSelfDuration() {
        return selfDuration;
    }

    public void setSelfDuration(Long selfDuration) {
        this.selfDuration = selfDuration;
    }

    public InitialCauseSpan localAddress(String localAddress) {
        this.localAddress = localAddress;
        return this;
    }

    /**
     * Get localAddress
     *
     * @return localAddress
     */
    @NotNull
    @Schema(name = "localAddress", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("localAddress")
    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public InitialCauseSpan peerAddress(String peerAddress) {
        this.peerAddress = peerAddress;
        return this;
    }

    /**
     * Get peerAddress
     *
     * @return peerAddress
     */
    @NotNull
    @Schema(name = "peerAddress", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("peerAddress")
    public String getPeerAddress() {
        return peerAddress;
    }

    public void setPeerAddress(String peerAddress) {
        this.peerAddress = peerAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InitialCauseSpan initialCauseSpan = (InitialCauseSpan) o;
        return Objects.equals(this.traceId, initialCauseSpan.traceId) &&
                Objects.equals(this.spanId, initialCauseSpan.spanId) &&
                Objects.equals(this.rootServiceName, initialCauseSpan.rootServiceName) &&
                Objects.equals(this.rootSpanName, initialCauseSpan.rootSpanName) &&
                Objects.equals(this.serviceName, initialCauseSpan.serviceName) &&
                Objects.equals(this.spanName, initialCauseSpan.spanName) &&
                Objects.equals(this.spanKind, initialCauseSpan.spanKind) &&
                Objects.equals(this.status, initialCauseSpan.status) &&
                Objects.equals(this.platform, initialCauseSpan.platform) &&
                Objects.equals(this.cluster, initialCauseSpan.cluster) &&
                Objects.equals(this.instance, initialCauseSpan.instance) &&
                Objects.equals(this.timestamp, initialCauseSpan.timestamp) &&
                Objects.equals(this.duration, initialCauseSpan.duration) &&
                Objects.equals(this.gap, initialCauseSpan.gap) &&
                Objects.equals(this.selfDuration, initialCauseSpan.selfDuration) &&
                Objects.equals(this.localAddress, initialCauseSpan.localAddress) &&
                Objects.equals(this.peerAddress, initialCauseSpan.peerAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(traceId, spanId, rootServiceName, rootSpanName, serviceName, spanName, spanKind, status, platform, cluster, instance, timestamp, duration, gap, selfDuration, localAddress, peerAddress);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class InitialCauseSpan {\n");
        sb.append("    traceId: ").append(toIndentedString(traceId)).append("\n");
        sb.append("    spanId: ").append(toIndentedString(spanId)).append("\n");
        sb.append("    rootServiceName: ").append(toIndentedString(rootServiceName)).append("\n");
        sb.append("    rootSpanName: ").append(toIndentedString(rootSpanName)).append("\n");
        sb.append("    serviceName: ").append(toIndentedString(serviceName)).append("\n");
        sb.append("    spanName: ").append(toIndentedString(spanName)).append("\n");
        sb.append("    spanKind: ").append(toIndentedString(spanKind)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    platform: ").append(toIndentedString(platform)).append("\n");
        sb.append("    cluster: ").append(toIndentedString(cluster)).append("\n");
        sb.append("    instance: ").append(toIndentedString(instance)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
        sb.append("    gap: ").append(toIndentedString(gap)).append("\n");
        sb.append("    selfDuration: ").append(toIndentedString(selfDuration)).append("\n");
        sb.append("    localAddress: ").append(toIndentedString(localAddress)).append("\n");
        sb.append("    peerAddress: ").append(toIndentedString(peerAddress)).append("\n");
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
