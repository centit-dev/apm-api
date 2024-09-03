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
 * SpanCommonality
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SpanCommonality {

    private SpanAttributeType type;

    private String name;

    private String displayName;

    @Valid
    private List<@Valid SpanCommonalityValue> values = new ArrayList<>();

    public SpanCommonality() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public SpanCommonality(SpanAttributeType type, String name, String displayName, List<@Valid SpanCommonalityValue> values) {
        this.type = type;
        this.name = name;
        this.displayName = displayName;
        this.values = values;
    }

    public SpanCommonality type(SpanAttributeType type) {
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

    public SpanCommonality name(String name) {
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

    public SpanCommonality displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * 该系统属性字段的显示名称。如：k8s.deplolyment.name显示为“应用集群”
     *
     * @return displayName
     */
    @NotNull
    @Schema(name = "displayName", description = "该系统属性字段的显示名称。如：k8s.deplolyment.name显示为“应用集群”", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public SpanCommonality values(List<@Valid SpanCommonalityValue> values) {
        this.values = values;
        return this;
    }

    public SpanCommonality addValuesItem(SpanCommonalityValue valuesItem) {
        if (this.values == null) {
            this.values = new ArrayList<>();
        }
        this.values.add(valuesItem);
        return this;
    }

    /**
     * 计算出该共有属性下的span统计量，可能是span计数、也可能是占比等。
     *
     * @return values
     */
    @NotNull
    @Valid
    @Schema(name = "values", description = "计算出该共有属性下的span统计量，可能是span计数、也可能是占比等。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("values")
    public List<@Valid SpanCommonalityValue> getValues() {
        return values;
    }

    public void setValues(List<@Valid SpanCommonalityValue> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpanCommonality spanCommonality = (SpanCommonality) o;
        return Objects.equals(this.type, spanCommonality.type) &&
                Objects.equals(this.name, spanCommonality.name) &&
                Objects.equals(this.displayName, spanCommonality.displayName) &&
                Objects.equals(this.values, spanCommonality.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, displayName, values);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SpanCommonality {\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
        sb.append("    values: ").append(toIndentedString(values)).append("\n");
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
