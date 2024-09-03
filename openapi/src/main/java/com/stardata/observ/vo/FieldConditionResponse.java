package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * span属性字段条件列表。默认现在只支持所有条件的AND组合
 */

@Schema(name = "FieldConditionResponse", description = "span属性字段条件列表。默认现在只支持所有条件的AND组合")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class FieldConditionResponse {

    private NameDisplayPair name;

    private NameDisplayPair operator;

    private String value;

    public FieldConditionResponse() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public FieldConditionResponse(NameDisplayPair name, NameDisplayPair operator, String value) {
        this.name = name;
        this.operator = operator;
        this.value = value;
    }

    public FieldConditionResponse name(NameDisplayPair name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     */
    @NotNull
    @Valid
    @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("name")
    public NameDisplayPair getName() {
        return name;
    }

    public void setName(NameDisplayPair name) {
        this.name = name;
    }

    public FieldConditionResponse operator(NameDisplayPair operator) {
        this.operator = operator;
        return this;
    }

    /**
     * Get operator
     *
     * @return operator
     */
    @NotNull
    @Valid
    @Schema(name = "operator", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("operator")
    public NameDisplayPair getOperator() {
        return operator;
    }

    public void setOperator(NameDisplayPair operator) {
        this.operator = operator;
    }

    public FieldConditionResponse value(String value) {
        this.value = value;
        return this;
    }

    /**
     * 实际上可能是字符串、数字、字符数字的json数组
     *
     * @return value
     */
    @NotNull
    @Schema(name = "value", description = "实际上可能是字符串、数字、字符数字的json数组", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldConditionResponse fieldConditionResponse = (FieldConditionResponse) o;
        return Objects.equals(this.name, fieldConditionResponse.name) &&
                Objects.equals(this.operator, fieldConditionResponse.operator) &&
                Objects.equals(this.value, fieldConditionResponse.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, operator, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FieldConditionResponse {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    operator: ").append(toIndentedString(operator)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
