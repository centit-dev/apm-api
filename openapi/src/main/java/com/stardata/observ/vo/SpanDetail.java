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
 * SpanDetail
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SpanDetail {

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

    @Valid
    private List<@Valid KeyValuePair> spanAttributes = new ArrayList<>();

    @Valid
    private List<@Valid KeyValuePair> resourceAttributes = new ArrayList<>();

    @Valid
    private List<@Valid KeyValuePair> requestAttributes = new ArrayList<>();

    @Valid
    private List<@Valid KeyValuePair> responseAttributes = new ArrayList<>();

    @Valid
    private List<@Valid AppLogDetail> logs = new ArrayList<>();

    public SpanDetail() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public SpanDetail(String traceId, String spanId, String parentSpanId, String serviceName, SpanKind spanKind, NameDisplayPair software, NameDisplayPair application, NameDisplayPair platform, String cluster, Long duration, List<@Valid KeyValuePair> spanAttributes, List<@Valid KeyValuePair> resourceAttributes, List<@Valid KeyValuePair> requestAttributes, List<@Valid KeyValuePair> responseAttributes, List<@Valid AppLogDetail> logs) {
        this.traceId = traceId;
        this.spanId = spanId;
        this.parentSpanId = parentSpanId;
        this.serviceName = serviceName;
        this.spanKind = spanKind;
        this.software = software;
        this.application = application;
        this.platform = platform;
        this.cluster = cluster;
        this.duration = duration;
        this.spanAttributes = spanAttributes;
        this.resourceAttributes = resourceAttributes;
        this.requestAttributes = requestAttributes;
        this.responseAttributes = responseAttributes;
        this.logs = logs;
    }

    public SpanDetail timestamp(Long timestamp) {
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

    public SpanDetail traceId(String traceId) {
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

    public SpanDetail spanId(String spanId) {
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

    public SpanDetail parentSpanId(String parentSpanId) {
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

    public SpanDetail serviceName(String serviceName) {
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

    public SpanDetail spanName(String spanName) {
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

    public SpanDetail spanKind(SpanKind spanKind) {
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

    public SpanDetail software(NameDisplayPair software) {
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

    public SpanDetail application(NameDisplayPair application) {
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

    public SpanDetail platform(NameDisplayPair platform) {
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

    public SpanDetail cluster(String cluster) {
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

    public SpanDetail instance(String instance) {
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

    public SpanDetail duration(Long duration) {
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

    public SpanDetail status(SpanStatus status) {
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

    public SpanDetail spanAttributes(List<@Valid KeyValuePair> spanAttributes) {
        this.spanAttributes = spanAttributes;
        return this;
    }

    public SpanDetail addSpanAttributesItem(KeyValuePair spanAttributesItem) {
        if (this.spanAttributes == null) {
            this.spanAttributes = new ArrayList<>();
        }
        this.spanAttributes.add(spanAttributesItem);
        return this;
    }

    /**
     * 以键值对列表方式表达的span属性全集
     *
     * @return spanAttributes
     */
    @NotNull
    @Valid
    @Schema(name = "spanAttributes", description = "以键值对列表方式表达的span属性全集", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("spanAttributes")
    public List<@Valid KeyValuePair> getSpanAttributes() {
        return spanAttributes;
    }

    public void setSpanAttributes(List<@Valid KeyValuePair> spanAttributes) {
        this.spanAttributes = spanAttributes;
    }

    public SpanDetail resourceAttributes(List<@Valid KeyValuePair> resourceAttributes) {
        this.resourceAttributes = resourceAttributes;
        return this;
    }

    public SpanDetail addResourceAttributesItem(KeyValuePair resourceAttributesItem) {
        if (this.resourceAttributes == null) {
            this.resourceAttributes = new ArrayList<>();
        }
        this.resourceAttributes.add(resourceAttributesItem);
        return this;
    }

    /**
     * 以键值对列表方式表达的resource属性全集
     *
     * @return resourceAttributes
     */
    @NotNull
    @Valid
    @Schema(name = "resourceAttributes", description = "以键值对列表方式表达的resource属性全集", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("resourceAttributes")
    public List<@Valid KeyValuePair> getResourceAttributes() {
        return resourceAttributes;
    }

    public void setResourceAttributes(List<@Valid KeyValuePair> resourceAttributes) {
        this.resourceAttributes = resourceAttributes;
    }

    public SpanDetail requestAttributes(List<@Valid KeyValuePair> requestAttributes) {
        this.requestAttributes = requestAttributes;
        return this;
    }

    public SpanDetail addRequestAttributesItem(KeyValuePair requestAttributesItem) {
        if (this.requestAttributes == null) {
            this.requestAttributes = new ArrayList<>();
        }
        this.requestAttributes.add(requestAttributesItem);
        return this;
    }

    /**
     * HTTP请求时输入的属性
     *
     * @return requestAttributes
     */
    @NotNull
    @Valid
    @Schema(name = "requestAttributes", description = "HTTP请求时输入的属性", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("requestAttributes")
    public List<@Valid KeyValuePair> getRequestAttributes() {
        return requestAttributes;
    }

    public void setRequestAttributes(List<@Valid KeyValuePair> requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    public SpanDetail responseAttributes(List<@Valid KeyValuePair> responseAttributes) {
        this.responseAttributes = responseAttributes;
        return this;
    }

    public SpanDetail addResponseAttributesItem(KeyValuePair responseAttributesItem) {
        if (this.responseAttributes == null) {
            this.responseAttributes = new ArrayList<>();
        }
        this.responseAttributes.add(responseAttributesItem);
        return this;
    }

    /**
     * HTTP响应时输出的属性
     *
     * @return responseAttributes
     */
    @NotNull
    @Valid
    @Schema(name = "responseAttributes", description = "HTTP响应时输出的属性", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("responseAttributes")
    public List<@Valid KeyValuePair> getResponseAttributes() {
        return responseAttributes;
    }

    public void setResponseAttributes(List<@Valid KeyValuePair> responseAttributes) {
        this.responseAttributes = responseAttributes;
    }

    public SpanDetail logs(List<@Valid AppLogDetail> logs) {
        this.logs = logs;
        return this;
    }

    public SpanDetail addLogsItem(AppLogDetail logsItem) {
        if (this.logs == null) {
            this.logs = new ArrayList<>();
        }
        this.logs.add(logsItem);
        return this;
    }

    /**
     * Get logs
     *
     * @return logs
     */
    @NotNull
    @Valid
    @Schema(name = "logs", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("logs")
    public List<@Valid AppLogDetail> getLogs() {
        return logs;
    }

    public void setLogs(List<@Valid AppLogDetail> logs) {
        this.logs = logs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpanDetail spanDetail = (SpanDetail) o;
        return Objects.equals(this.timestamp, spanDetail.timestamp) &&
                Objects.equals(this.traceId, spanDetail.traceId) &&
                Objects.equals(this.spanId, spanDetail.spanId) &&
                Objects.equals(this.parentSpanId, spanDetail.parentSpanId) &&
                Objects.equals(this.serviceName, spanDetail.serviceName) &&
                Objects.equals(this.spanName, spanDetail.spanName) &&
                Objects.equals(this.spanKind, spanDetail.spanKind) &&
                Objects.equals(this.software, spanDetail.software) &&
                Objects.equals(this.application, spanDetail.application) &&
                Objects.equals(this.platform, spanDetail.platform) &&
                Objects.equals(this.cluster, spanDetail.cluster) &&
                Objects.equals(this.instance, spanDetail.instance) &&
                Objects.equals(this.duration, spanDetail.duration) &&
                Objects.equals(this.status, spanDetail.status) &&
                Objects.equals(this.spanAttributes, spanDetail.spanAttributes) &&
                Objects.equals(this.resourceAttributes, spanDetail.resourceAttributes) &&
                Objects.equals(this.requestAttributes, spanDetail.requestAttributes) &&
                Objects.equals(this.responseAttributes, spanDetail.responseAttributes) &&
                Objects.equals(this.logs, spanDetail.logs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, traceId, spanId, parentSpanId, serviceName, spanName, spanKind, software, application, platform, cluster, instance, duration, status, spanAttributes, resourceAttributes, requestAttributes, responseAttributes, logs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SpanDetail {\n");
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
        sb.append("    spanAttributes: ").append(toIndentedString(spanAttributes)).append("\n");
        sb.append("    resourceAttributes: ").append(toIndentedString(resourceAttributes)).append("\n");
        sb.append("    requestAttributes: ").append(toIndentedString(requestAttributes)).append("\n");
        sb.append("    responseAttributes: ").append(toIndentedString(responseAttributes)).append("\n");
        sb.append("    logs: ").append(toIndentedString(logs)).append("\n");
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
