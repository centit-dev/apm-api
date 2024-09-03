package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * NameDisplayPair
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class NameDisplayPair {

    private String name;

    private String displayName;

    public NameDisplayPair() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public NameDisplayPair(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public NameDisplayPair name(String name) {
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

    public NameDisplayPair displayName(String displayName) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NameDisplayPair nameDisplayPair = (NameDisplayPair) o;
        return Objects.equals(this.name, nameDisplayPair.name) &&
                Objects.equals(this.displayName, nameDisplayPair.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, displayName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NameDisplayPair {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
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
