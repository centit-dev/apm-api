package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Software
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Software {

    private String name;

    private String displayName;

    private String logo;

    public Software() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public Software(String name, String displayName, String logo) {
        this.name = name;
        this.displayName = displayName;
        this.logo = logo;
    }

    public Software name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 软件名称
     *
     * @return name
     */
    @NotNull
    @Schema(name = "name", description = "软件名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Software displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * 软件显示名称
     *
     * @return displayName
     */
    @NotNull
    @Schema(name = "displayName", description = "软件显示名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Software logo(String logo) {
        this.logo = logo;
        return this;
    }

    /**
     * 用于显示软件LOGO的图片文件PATH
     *
     * @return logo
     */
    @NotNull
    @Schema(name = "logo", description = "用于显示软件LOGO的图片文件PATH", requiredMode = Schema.RequiredMode.REQUIRED)
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
        Software software = (Software) o;
        return Objects.equals(this.name, software.name) &&
                Objects.equals(this.displayName, software.displayName) &&
                Objects.equals(this.logo, software.logo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, displayName, logo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Software {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
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
