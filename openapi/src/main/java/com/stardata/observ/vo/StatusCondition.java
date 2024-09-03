package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * StatusCondition
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class StatusCondition {

    private SpanStatus status;
    private TrendEnum trend;
    private Long minDelay;

    public StatusCondition() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public StatusCondition(SpanStatus status) {
        this.status = status;
    }

    public StatusCondition status(SpanStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     */
    @NotNull
    @Valid
    @Schema(name = "status", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("status")
    public SpanStatus getStatus() {
        return status;
    }

    public void setStatus(SpanStatus status) {
        this.status = status;
    }

    public StatusCondition trend(TrendEnum trend) {
        this.trend = trend;
        return this;
    }

    /**
     * up-上升；down-下降；空表示不限。
     *
     * @return trend
     */

    @Schema(name = "trend", description = "up-上升；down-下降；空表示不限。", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("trend")
    public TrendEnum getTrend() {
        return trend;
    }

    public void setTrend(TrendEnum trend) {
        this.trend = trend;
    }

    public StatusCondition minDelay(Long minDelay) {
        this.minDelay = minDelay;
        return this;
    }

    /**
     * 单位毫秒，100毫秒~10000毫秒（10秒之间）
     *
     * @return minDelay
     */

    @Schema(name = "minDelay", description = "单位毫秒，100毫秒~10000毫秒（10秒之间）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("minDelay")
    public Long getMinDelay() {
        return minDelay;
    }

    public void setMinDelay(Long minDelay) {
        this.minDelay = minDelay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StatusCondition statusCondition = (StatusCondition) o;
        return Objects.equals(this.status, statusCondition.status) &&
                Objects.equals(this.trend, statusCondition.trend) &&
                Objects.equals(this.minDelay, statusCondition.minDelay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, trend, minDelay);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StatusCondition {\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    trend: ").append(toIndentedString(trend)).append("\n");
        sb.append("    minDelay: ").append(toIndentedString(minDelay)).append("\n");
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

    /**
     * up-上升；down-下降；空表示不限。
     */
    public enum TrendEnum {
        UP("up"),

        DOWN("down");

        private String value;

        TrendEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static TrendEnum fromValue(String value) {
            for (TrendEnum b : TrendEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}
