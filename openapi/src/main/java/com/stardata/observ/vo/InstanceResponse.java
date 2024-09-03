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
 * InstanceResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class InstanceResponse {

    private String name;

    @Valid
    private List<@Valid AppRelatedResource> resources = new ArrayList<>();

    public InstanceResponse() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public InstanceResponse(String name, List<@Valid AppRelatedResource> resources) {
        this.name = name;
        this.resources = resources;
    }

    public InstanceResponse name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 名称
     *
     * @return name
     */
    @NotNull
    @Schema(name = "name", description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InstanceResponse resources(List<@Valid AppRelatedResource> resources) {
        this.resources = resources;
        return this;
    }

    public InstanceResponse addResourcesItem(AppRelatedResource resourcesItem) {
        if (this.resources == null) {
            this.resources = new ArrayList<>();
        }
        this.resources.add(resourcesItem);
        return this;
    }

    /**
     * Get resources
     *
     * @return resources
     */
    @NotNull
    @Valid
    @Schema(name = "resources", requiredMode = Schema.RequiredMode.REQUIRED)
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
        InstanceResponse instanceResponse = (InstanceResponse) o;
        return Objects.equals(this.name, instanceResponse.name) &&
                Objects.equals(this.resources, instanceResponse.resources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, resources);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class InstanceResponse {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
