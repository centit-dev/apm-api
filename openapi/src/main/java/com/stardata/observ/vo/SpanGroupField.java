package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * SpanGroupField
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SpanGroupField {

    private SpanAttributeType type;

    private String name;

    public SpanGroupField() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public SpanGroupField(SpanAttributeType type, String name) {
        this.type = type;
        this.name = name;
    }

    public SpanGroupField type(SpanAttributeType type) {
        this.type = type;
        return this;
    }

    /**
     * Get type
     *
     * @return type
     */
    @NotNull
    @Valid
    @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("type")
    public SpanAttributeType getType() {
        return type;
    }

    public void setType(SpanAttributeType type) {
        this.type = type;
    }

    public SpanGroupField name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 用于系统处理的属性名称，符合OTEL语义规范
     *
     * @return name
     */
    @NotNull
    @Schema(name = "name", description = "用于系统处理的属性名称，符合OTEL语义规范", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpanGroupField spanGroupField = (SpanGroupField) o;
        return Objects.equals(this.type, spanGroupField.type) &&
                Objects.equals(this.name, spanGroupField.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SpanGroupField {\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
