package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 查询记录的时间范围
 */

@Schema(name = "TimeWindow", description = "查询记录的时间范围")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TimeWindow {

    private Long fromTime;

    private Long toTime;

    public TimeWindow fromTime(Long fromTime) {
        this.fromTime = fromTime;
        return this;
    }

    /**
     * 指定时间段情况下的起始时间戳，整数，单位毫秒
     *
     * @return fromTime
     */

    @Schema(name = "fromTime", description = "指定时间段情况下的起始时间戳，整数，单位毫秒", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("fromTime")
    public Long getFromTime() {
        return fromTime;
    }

    public void setFromTime(Long fromTime) {
        this.fromTime = fromTime;
    }

    public TimeWindow toTime(Long toTime) {
        this.toTime = toTime;
        return this;
    }

    /**
     * 指定时间段情况下的截止时间戳，整数，单位毫秒
     *
     * @return toTime
     */

    @Schema(name = "toTime", description = "指定时间段情况下的截止时间戳，整数，单位毫秒", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("toTime")
    public Long getToTime() {
        return toTime;
    }

    public void setToTime(Long toTime) {
        this.toTime = toTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeWindow timeWindow = (TimeWindow) o;
        return Objects.equals(this.fromTime, timeWindow.fromTime) &&
                Objects.equals(this.toTime, timeWindow.toTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromTime, toTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeWindow {\n");
        sb.append("    fromTime: ").append(toIndentedString(fromTime)).append("\n");
        sb.append("    toTime: ").append(toIndentedString(toTime)).append("\n");
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
