package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 服务调用span对象，每个服务每调用一次生成一个span
 */

@Schema(name = "AppLogBrief", description = "服务调用span对象，每个服务每调用一次生成一个span")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AppLogBrief {

    private String traceId;

    private String spanId;

    private Long timestamp;

    private NameDisplayPair platform;

    private NameDisplayPair application;

    private String service;

    private String cluster;

    private String instance;

    private Integer severityNumber;

    private String severityText;

    private String scopeName;

    private String body;

    public AppLogBrief() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public AppLogBrief(String traceId, String spanId, Long timestamp, NameDisplayPair platform, NameDisplayPair application, String service, String cluster, String instance, Integer severityNumber, String severityText, String scopeName, String body) {
        this.traceId = traceId;
        this.spanId = spanId;
        this.timestamp = timestamp;
        this.platform = platform;
        this.application = application;
        this.service = service;
        this.cluster = cluster;
        this.instance = instance;
        this.severityNumber = severityNumber;
        this.severityText = severityText;
        this.scopeName = scopeName;
        this.body = body;
    }

    public AppLogBrief traceId(String traceId) {
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

    public AppLogBrief spanId(String spanId) {
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

    public AppLogBrief timestamp(Long timestamp) {
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

    public AppLogBrief platform(NameDisplayPair platform) {
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

    public AppLogBrief application(NameDisplayPair application) {
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

    public AppLogBrief service(String service) {
        this.service = service;
        return this;
    }

    /**
     * 服务运行时跨度所在的服务接口
     *
     * @return service
     */
    @NotNull
    @Schema(name = "service", description = "服务运行时跨度所在的服务接口", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("service")
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public AppLogBrief cluster(String cluster) {
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

    public AppLogBrief instance(String instance) {
        this.instance = instance;
        return this;
    }

    /**
     * 服务运行时跨度所在的实例
     *
     * @return instance
     */
    @NotNull
    @Schema(name = "instance", description = "服务运行时跨度所在的实例", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("instance")
    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public AppLogBrief severityNumber(Integer severityNumber) {
        this.severityNumber = severityNumber;
        return this;
    }

    /**
     * 数字表示的日志级别
     *
     * @return severityNumber
     */
    @NotNull
    @Schema(name = "severityNumber", description = "数字表示的日志级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("severityNumber")
    public Integer getSeverityNumber() {
        return severityNumber;
    }

    public void setSeverityNumber(Integer severityNumber) {
        this.severityNumber = severityNumber;
    }

    public AppLogBrief severityText(String severityText) {
        this.severityText = severityText;
        return this;
    }

    /**
     * 文本表示的日志级别
     *
     * @return severityText
     */
    @NotNull
    @Schema(name = "severityText", description = "文本表示的日志级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("severityText")
    public String getSeverityText() {
        return severityText;
    }

    public void setSeverityText(String severityText) {
        this.severityText = severityText;
    }

    public AppLogBrief scopeName(String scopeName) {
        this.scopeName = scopeName;
        return this;
    }

    /**
     * 输出该日志的类名path
     *
     * @return scopeName
     */
    @NotNull
    @Schema(name = "scopeName", description = "输出该日志的类名path", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("scopeName")
    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    public AppLogBrief body(String body) {
        this.body = body;
        return this;
    }

    /**
     * 日志文本内容，可能很多行
     *
     * @return body
     */
    @NotNull
    @Schema(name = "body", description = "日志文本内容，可能很多行", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppLogBrief appLogBrief = (AppLogBrief) o;
        return Objects.equals(this.traceId, appLogBrief.traceId) &&
                Objects.equals(this.spanId, appLogBrief.spanId) &&
                Objects.equals(this.timestamp, appLogBrief.timestamp) &&
                Objects.equals(this.platform, appLogBrief.platform) &&
                Objects.equals(this.application, appLogBrief.application) &&
                Objects.equals(this.service, appLogBrief.service) &&
                Objects.equals(this.cluster, appLogBrief.cluster) &&
                Objects.equals(this.instance, appLogBrief.instance) &&
                Objects.equals(this.severityNumber, appLogBrief.severityNumber) &&
                Objects.equals(this.severityText, appLogBrief.severityText) &&
                Objects.equals(this.scopeName, appLogBrief.scopeName) &&
                Objects.equals(this.body, appLogBrief.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(traceId, spanId, timestamp, platform, application, service, cluster, instance, severityNumber, severityText, scopeName, body);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AppLogBrief {\n");
        sb.append("    traceId: ").append(toIndentedString(traceId)).append("\n");
        sb.append("    spanId: ").append(toIndentedString(spanId)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    platform: ").append(toIndentedString(platform)).append("\n");
        sb.append("    application: ").append(toIndentedString(application)).append("\n");
        sb.append("    service: ").append(toIndentedString(service)).append("\n");
        sb.append("    cluster: ").append(toIndentedString(cluster)).append("\n");
        sb.append("    instance: ").append(toIndentedString(instance)).append("\n");
        sb.append("    severityNumber: ").append(toIndentedString(severityNumber)).append("\n");
        sb.append("    severityText: ").append(toIndentedString(severityText)).append("\n");
        sb.append("    scopeName: ").append(toIndentedString(scopeName)).append("\n");
        sb.append("    body: ").append(toIndentedString(body)).append("\n");
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
