package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * AppRelatedResource
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AppRelatedResource {

    private String id;

    private String type;

    private String name;

    private String displayName;

    private String monitorURL;

    private String logo;

    public AppRelatedResource() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public AppRelatedResource(String id, String type, String name, String displayName, String monitorURL, String logo) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.displayName = displayName;
        this.monitorURL = monitorURL;
        this.logo = logo;
    }

    public AppRelatedResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * CMDB资源ID
     *
     * @return id
     */
    @NotNull
    @Schema(name = "id", description = "CMDB资源ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppRelatedResource type(String type) {
        this.type = type;
        return this;
    }

    /**
     * JVM、k8s pod、k8s node、oracle、redis等
     *
     * @return type
     */
    @NotNull
    @Schema(name = "type", description = "JVM、k8s pod、k8s node、oracle、redis等", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AppRelatedResource name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     */
    @NotNull
    @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AppRelatedResource displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Get displayName
     *
     * @return displayName
     */
    @NotNull
    @Schema(name = "displayName", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public AppRelatedResource monitorURL(String monitorURL) {
        this.monitorURL = monitorURL;
        return this;
    }

    /**
     * 资源监控页面的URL路径
     *
     * @return monitorURL
     */
    @NotNull
    @Schema(name = "monitorURL", description = "资源监控页面的URL路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("monitorURL")
    public String getMonitorURL() {
        return monitorURL;
    }

    public void setMonitorURL(String monitorURL) {
        this.monitorURL = monitorURL;
    }

    public AppRelatedResource logo(String logo) {
        this.logo = logo;
        return this;
    }

    /**
     * 显示该资源的LOGO图片URL
     *
     * @return logo
     */
    @NotNull
    @Schema(name = "logo", description = "显示该资源的LOGO图片URL", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("logo")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppRelatedResource appRelatedResource = (AppRelatedResource) o;
        return Objects.equals(this.id, appRelatedResource.id) &&
                Objects.equals(this.type, appRelatedResource.type) &&
                Objects.equals(this.name, appRelatedResource.name) &&
                Objects.equals(this.displayName, appRelatedResource.displayName) &&
                Objects.equals(this.monitorURL, appRelatedResource.monitorURL) &&
                Objects.equals(this.logo, appRelatedResource.logo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, displayName, monitorURL, logo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AppRelatedResource {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
        sb.append("    monitorURL: ").append(toIndentedString(monitorURL)).append("\n");
        sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
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
