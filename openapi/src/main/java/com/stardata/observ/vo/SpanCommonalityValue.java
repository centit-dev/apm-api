package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * SpanCommonalityValue
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SpanCommonalityValue {

    private String code;

    private Double value;

    public SpanCommonalityValue() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public SpanCommonalityValue(String code, Double value) {
        this.code = code;
        this.value = value;
    }

    public SpanCommonalityValue code(String code) {
        this.code = code;
        return this;
    }

    /**
     * 某共有属性（如：集群编码等）可显示的具体值编码（如：busi-ord-exe等）
     *
     * @return code
     */
    @NotNull
    @Schema(name = "code", description = "某共有属性（如：集群编码等）可显示的具体值编码（如：busi-ord-exe等）", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SpanCommonalityValue value(Double value) {
        this.value = value;
        return this;
    }

    /**
     * 某共有属性（如：集群编码等）的统计值，可能是计数、也可能是比例等。用于在共有属性累积柱状图中显示该属性、在该编码值下的柱子高度。
     * minimum: 0
     *
     * @return value
     */
    @NotNull
    @DecimalMin("0")
    @Schema(name = "value", description = "某共有属性（如：集群编码等）的统计值，可能是计数、也可能是比例等。用于在共有属性累积柱状图中显示该属性、在该编码值下的柱子高度。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("value")
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
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
        SpanCommonalityValue spanCommonalityValue = (SpanCommonalityValue) o;
        return Objects.equals(this.code, spanCommonalityValue.code) &&
                Objects.equals(this.value, spanCommonalityValue.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SpanCommonalityValue {\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
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
