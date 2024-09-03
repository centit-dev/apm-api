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
 * InitialCauseAppInstance
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class InitialCauseAppInstance {

    private NameDisplayPair platform;

    private NameDisplayPair application;

    private String cluster;

    private String instance;

    private SpanKind spanKind;

    private String software;

    private String serviceName;

    private String spanName;

    private Integer exceptionCount;

    private Integer callCount;

    private String serviceMonitorUrl;

    @Valid
    private List<@Valid AppRelatedResource> resources = new ArrayList<>();

    public InitialCauseAppInstance() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public InitialCauseAppInstance(NameDisplayPair platform, NameDisplayPair application, String cluster, String instance, SpanKind spanKind, String software, String serviceName, String spanName, Integer exceptionCount, Integer callCount, String serviceMonitorUrl, List<@Valid AppRelatedResource> resources) {
        this.platform = platform;
        this.application = application;
        this.cluster = cluster;
        this.instance = instance;
        this.spanKind = spanKind;
        this.software = software;
        this.serviceName = serviceName;
        this.spanName = spanName;
        this.exceptionCount = exceptionCount;
        this.callCount = callCount;
        this.serviceMonitorUrl = serviceMonitorUrl;
        this.resources = resources;
    }

    public InitialCauseAppInstance platform(NameDisplayPair platform) {
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

    public InitialCauseAppInstance application(NameDisplayPair application) {
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

    public InitialCauseAppInstance cluster(String cluster) {
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

    public InitialCauseAppInstance instance(String instance) {
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

    public InitialCauseAppInstance spanKind(SpanKind spanKind) {
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

    public InitialCauseAppInstance software(String software) {
        this.software = software;
        return this;
    }

    /**
     * 该Span运行所在的软件名称。对于CLIENT类型span，需要识别该类型span调用的是哪个软件；对于其它类型span，需要识别该软件运行所在的软件类型。
     *
     * @return software
     */
    @NotNull
    @Schema(name = "software", description = "该Span运行所在的软件名称。对于CLIENT类型span，需要识别该类型span调用的是哪个软件；对于其它类型span，需要识别该软件运行所在的软件类型。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("software")
    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public InitialCauseAppInstance serviceName(String serviceName) {
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

    public InitialCauseAppInstance spanName(String spanName) {
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

    public InitialCauseAppInstance exceptionCount(Integer exceptionCount) {
        this.exceptionCount = exceptionCount;
        return this;
    }

    /**
     * 该应用实例出现异常调用的次数
     *
     * @return exceptionCount
     */
    @NotNull
    @Schema(name = "exceptionCount", description = "该应用实例出现异常调用的次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("exceptionCount")
    public Integer getExceptionCount() {
        return exceptionCount;
    }

    public void setExceptionCount(Integer exceptionCount) {
        this.exceptionCount = exceptionCount;
    }

    public InitialCauseAppInstance callCount(Integer callCount) {
        this.callCount = callCount;
        return this;
    }

    /**
     * 该应用实例总共被调用的次数
     *
     * @return callCount
     */
    @NotNull
    @Schema(name = "callCount", description = "该应用实例总共被调用的次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("callCount")
    public Integer getCallCount() {
        return callCount;
    }

    public void setCallCount(Integer callCount) {
        this.callCount = callCount;
    }

    public InitialCauseAppInstance serviceMonitorUrl(String serviceMonitorUrl) {
        this.serviceMonitorUrl = serviceMonitorUrl;
        return this;
    }

    /**
     * 该应用对应服务的red监控页面
     *
     * @return serviceMonitorUrl
     */
    @NotNull
    @Schema(name = "serviceMonitorUrl", description = "该应用对应服务的red监控页面", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("serviceMonitorUrl")
    public String getServiceMonitorUrl() {
        return serviceMonitorUrl;
    }

    public void setServiceMonitorUrl(String serviceMonitorUrl) {
        this.serviceMonitorUrl = serviceMonitorUrl;
    }

    public InitialCauseAppInstance resources(List<@Valid AppRelatedResource> resources) {
        this.resources = resources;
        return this;
    }

    public InitialCauseAppInstance addResourcesItem(AppRelatedResource resourcesItem) {
        if (this.resources == null) {
            this.resources = new ArrayList<>();
        }
        this.resources.add(resourcesItem);
        return this;
    }

    /**
     * 该应用实例关联的资源列表
     *
     * @return resources
     */
    @NotNull
    @Valid
    @Schema(name = "resources", description = "该应用实例关联的资源列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("resources")
    public List<@Valid AppRelatedResource> getResources() {
        return resources;
    }

    public void setResources(List<@Valid AppRelatedResource> resources) {
        this.resources = resources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InitialCauseAppInstance initialCauseAppInstance = (InitialCauseAppInstance) o;
        return Objects.equals(this.platform, initialCauseAppInstance.platform) &&
                Objects.equals(this.application, initialCauseAppInstance.application) &&
                Objects.equals(this.cluster, initialCauseAppInstance.cluster) &&
                Objects.equals(this.instance, initialCauseAppInstance.instance) &&
                Objects.equals(this.spanKind, initialCauseAppInstance.spanKind) &&
                Objects.equals(this.software, initialCauseAppInstance.software) &&
                Objects.equals(this.serviceName, initialCauseAppInstance.serviceName) &&
                Objects.equals(this.spanName, initialCauseAppInstance.spanName) &&
                Objects.equals(this.exceptionCount, initialCauseAppInstance.exceptionCount) &&
                Objects.equals(this.callCount, initialCauseAppInstance.callCount) &&
                Objects.equals(this.serviceMonitorUrl, initialCauseAppInstance.serviceMonitorUrl) &&
                Objects.equals(this.resources, initialCauseAppInstance.resources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(platform, application, cluster, instance, spanKind, software, serviceName, spanName, exceptionCount, callCount, serviceMonitorUrl, resources);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class InitialCauseAppInstance {\n");
        sb.append("    platform: ").append(toIndentedString(platform)).append("\n");
        sb.append("    application: ").append(toIndentedString(application)).append("\n");
        sb.append("    cluster: ").append(toIndentedString(cluster)).append("\n");
        sb.append("    instance: ").append(toIndentedString(instance)).append("\n");
        sb.append("    spanKind: ").append(toIndentedString(spanKind)).append("\n");
        sb.append("    software: ").append(toIndentedString(software)).append("\n");
        sb.append("    serviceName: ").append(toIndentedString(serviceName)).append("\n");
        sb.append("    spanName: ").append(toIndentedString(spanName)).append("\n");
        sb.append("    exceptionCount: ").append(toIndentedString(exceptionCount)).append("\n");
        sb.append("    callCount: ").append(toIndentedString(callCount)).append("\n");
        sb.append("    serviceMonitorUrl: ").append(toIndentedString(serviceMonitorUrl)).append("\n");
        sb.append("    resources: ").append(toIndentedString(resources)).append("\n");
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
