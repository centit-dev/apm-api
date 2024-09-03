package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 时延区间，为空表示不限
 */

@Schema(name = "DurationRange", description = "时延区间，为空表示不限")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class DurationRange {

    private Long min;

    private Long max;

    public DurationRange() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public DurationRange(Long min, Long max) {
        this.min = min;
        this.max = max;
    }

    public DurationRange min(Long min) {
        this.min = min;
        return this;
    }

    /**
     * 时延区间最小值，单位是毫秒
     *
     * @return min
     */
    @NotNull
    @Schema(name = "min", description = "时延区间最小值，单位是毫秒", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("min")
    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public DurationRange max(Long max) {
        this.max = max;
        return this;
    }

    /**
     * 时延区间最大值，单位是毫秒
     *
     * @return max
     */
    @NotNull
    @Schema(name = "max", description = "时延区间最大值，单位是毫秒", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("max")
    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DurationRange durationRange = (DurationRange) o;
        return Objects.equals(this.min, durationRange.min) &&
                Objects.equals(this.max, durationRange.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DurationRange {\n");
        sb.append("    min: ").append(toIndentedString(min)).append("\n");
        sb.append("    max: ").append(toIndentedString(max)).append("\n");
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
