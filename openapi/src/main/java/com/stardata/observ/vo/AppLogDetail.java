package com.stardata.observ.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 服务调用span对象，每个服务每调用一次生成一个span
 */

@Schema(name = "AppLogDetail", description = "服务调用span对象，每个服务每调用一次生成一个span")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AppLogDetail {

    private Long timestamp;

    private String traceId;

    private String spanId;

    private NameDisplayPair platform;

    private NameDisplayPair application;

    private String service;

    private String cluster;

    private String instance;

    private Integer severityNumber;

    private String severityText;

    private String scopeName;

    private String body;

    @Valid
    private List<@Valid KeyValuePair> logAttributes = new ArrayList<>();

    @Valid
    private List<@Valid KeyValuePair> scopeAttributes = new ArrayList<>();

    @Valid
    private List<@Valid KeyValuePair> resourceAttributes = new ArrayList<>();

    public AppLogDetail() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public AppLogDetail(Long timestamp, String traceId, String spanId, NameDisplayPair platform, NameDisplayPair application, String service, String cluster, String instance, Integer severityNumber, String severityText, String scopeName, List<@Valid KeyValuePair> logAttributes, List<@Valid KeyValuePair> scopeAttributes, List<@Valid KeyValuePair> resourceAttributes) {
        this.timestamp = timestamp;
        this.traceId = traceId;
        this.spanId = spanId;
        this.platform = platform;
        this.application = application;
        this.service = service;
        this.cluster = cluster;
        this.instance = instance;
        this.severityNumber = severityNumber;
        this.severityText = severityText;
        this.scopeName = scopeName;
        this.logAttributes = logAttributes;
        this.scopeAttributes = scopeAttributes;
        this.resourceAttributes = resourceAttributes;
    }

    public AppLogDetail timestamp(Long timestamp) {
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

    public AppLogDetail traceId(String traceId) {
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

    public AppLogDetail spanId(String spanId) {
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

    public AppLogDetail platform(NameDisplayPair platform) {
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

    public AppLogDetail application(NameDisplayPair application) {
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

    public AppLogDetail service(String service) {
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

    public AppLogDetail cluster(String cluster) {
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

    public AppLogDetail instance(String instance) {
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

    public AppLogDetail severityNumber(Integer severityNumber) {
        this.severityNumber = severityNumber;
        return this;
    }

    /**
     * 数字表示的日志级别
     * minimum: 0
     * maximum: 24
     *
     * @return severityNumber
     */
    @NotNull
    @Min(0)
    @Max(24)
    @Schema(name = "severityNumber", description = "数字表示的日志级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("severityNumber")
    public Integer getSeverityNumber() {
        return severityNumber;
    }

    public void setSeverityNumber(Integer severityNumber) {
        this.severityNumber = severityNumber;
    }

    public AppLogDetail severityText(String severityText) {
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

    public AppLogDetail scopeName(String scopeName) {
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

    public AppLogDetail body(String body) {
        this.body = body;
        return this;
    }

    /**
     * 一个长字符串，保存报错信息
     *
     * @return body
     */

    @Schema(name = "body", description = "一个长字符串，保存报错信息", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public AppLogDetail logAttributes(List<@Valid KeyValuePair> logAttributes) {
        this.logAttributes = logAttributes;
        return this;
    }

    public AppLogDetail addLogAttributesItem(KeyValuePair logAttributesItem) {
        if (this.logAttributes == null) {
            this.logAttributes = new ArrayList<>();
        }
        this.logAttributes.add(logAttributesItem);
        return this;
    }

    /**
     * 对于抛出异常的情况，日志属性中会包含异常类、异常调用栈等信息。这是个JSON对象字符串。
     *
     * @return logAttributes
     */
    @NotNull
    @Valid
    @Schema(name = "logAttributes", description = "对于抛出异常的情况，日志属性中会包含异常类、异常调用栈等信息。这是个JSON对象字符串。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("logAttributes")
    public List<@Valid KeyValuePair> getLogAttributes() {
        return logAttributes;
    }

    public void setLogAttributes(List<@Valid KeyValuePair> logAttributes) {
        this.logAttributes = logAttributes;
    }

    public AppLogDetail scopeAttributes(List<@Valid KeyValuePair> scopeAttributes) {
        this.scopeAttributes = scopeAttributes;
        return this;
    }

    public AppLogDetail addScopeAttributesItem(KeyValuePair scopeAttributesItem) {
        if (this.scopeAttributes == null) {
            this.scopeAttributes = new ArrayList<>();
        }
        this.scopeAttributes.add(scopeAttributesItem);
        return this;
    }

    /**
     * 输出该日志的位置属性全集
     *
     * @return scopeAttributes
     */
    @NotNull
    @Valid
    @Schema(name = "scopeAttributes", description = "输出该日志的位置属性全集", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("scopeAttributes")
    public List<@Valid KeyValuePair> getScopeAttributes() {
        return scopeAttributes;
    }

    public void setScopeAttributes(List<@Valid KeyValuePair> scopeAttributes) {
        this.scopeAttributes = scopeAttributes;
    }

    public AppLogDetail resourceAttributes(List<@Valid KeyValuePair> resourceAttributes) {
        this.resourceAttributes = resourceAttributes;
        return this;
    }

    public AppLogDetail addResourceAttributesItem(KeyValuePair resourceAttributesItem) {
        if (this.resourceAttributes == null) {
            this.resourceAttributes = new ArrayList<>();
        }
        this.resourceAttributes.add(resourceAttributesItem);
        return this;
    }

    /**
     * 该日志捕获时，所运行的资源相关属性。
     *
     * @return resourceAttributes
     */
    @NotNull
    @Valid
    @Schema(name = "resourceAttributes", description = "该日志捕获时，所运行的资源相关属性。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("resourceAttributes")
    public List<@Valid KeyValuePair> getResourceAttributes() {
        return resourceAttributes;
    }

    public void setResourceAttributes(List<@Valid KeyValuePair> resourceAttributes) {
        this.resourceAttributes = resourceAttributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppLogDetail appLogDetail = (AppLogDetail) o;
        return Objects.equals(this.timestamp, appLogDetail.timestamp) &&
                Objects.equals(this.traceId, appLogDetail.traceId) &&
                Objects.equals(this.spanId, appLogDetail.spanId) &&
                Objects.equals(this.platform, appLogDetail.platform) &&
                Objects.equals(this.application, appLogDetail.application) &&
                Objects.equals(this.service, appLogDetail.service) &&
                Objects.equals(this.cluster, appLogDetail.cluster) &&
                Objects.equals(this.instance, appLogDetail.instance) &&
                Objects.equals(this.severityNumber, appLogDetail.severityNumber) &&
                Objects.equals(this.severityText, appLogDetail.severityText) &&
                Objects.equals(this.scopeName, appLogDetail.scopeName) &&
                Objects.equals(this.body, appLogDetail.body) &&
                Objects.equals(this.logAttributes, appLogDetail.logAttributes) &&
                Objects.equals(this.scopeAttributes, appLogDetail.scopeAttributes) &&
                Objects.equals(this.resourceAttributes, appLogDetail.resourceAttributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, traceId, spanId, platform, application, service, cluster, instance, severityNumber, severityText, scopeName, body, logAttributes, scopeAttributes, resourceAttributes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AppLogDetail {\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    traceId: ").append(toIndentedString(traceId)).append("\n");
        sb.append("    spanId: ").append(toIndentedString(spanId)).append("\n");
        sb.append("    platform: ").append(toIndentedString(platform)).append("\n");
        sb.append("    application: ").append(toIndentedString(application)).append("\n");
        sb.append("    service: ").append(toIndentedString(service)).append("\n");
        sb.append("    cluster: ").append(toIndentedString(cluster)).append("\n");
        sb.append("    instance: ").append(toIndentedString(instance)).append("\n");
        sb.append("    severityNumber: ").append(toIndentedString(severityNumber)).append("\n");
        sb.append("    severityText: ").append(toIndentedString(severityText)).append("\n");
        sb.append("    scopeName: ").append(toIndentedString(scopeName)).append("\n");
        sb.append("    body: ").append(toIndentedString(body)).append("\n");
        sb.append("    logAttributes: ").append(toIndentedString(logAttributes)).append("\n");
        sb.append("    scopeAttributes: ").append(toIndentedString(scopeAttributes)).append("\n");
        sb.append("    resourceAttributes: ").append(toIndentedString(resourceAttributes)).append("\n");
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
