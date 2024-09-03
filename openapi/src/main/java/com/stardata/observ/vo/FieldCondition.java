package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * span属性字段条件列表。默认现在只支持所有条件的AND组合
 */

@Schema(name = "FieldCondition", description = "span属性字段条件列表。默认现在只支持所有条件的AND组合")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class FieldCondition {

    private String name;

    private String operator;

    private String value;

    public FieldCondition() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public FieldCondition(String name, String operator, String value) {
        this.name = name;
        this.operator = operator;
        this.value = value;
    }

    public FieldCondition name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 搜索条件的span属性字段名称
     *
     * @return name
     */
    @NotNull
    @Schema(name = "name", description = "搜索条件的span属性字段名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldCondition operator(String operator) {
        this.operator = operator;
        return this;
    }

    /**
     * 诸如：>=，<>, contains, starts-with等操作符
     *
     * @return operator
     */
    @NotNull
    @Schema(name = "operator", description = "诸如：>=，<>, contains, starts-with等操作符", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public FieldCondition value(String value) {
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
        FieldCondition fieldCondition = (FieldCondition) o;
        return Objects.equals(this.name, fieldCondition.name) &&
                Objects.equals(this.operator, fieldCondition.operator) &&
                Objects.equals(this.value, fieldCondition.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, operator, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FieldCondition {\n");
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
