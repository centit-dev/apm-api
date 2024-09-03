package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 查询数据所限制的时间范围，可以是指定时间和最近时间两种方式。为空表示不限时间范围
 */

@Schema(name = "TimeRange", description = "查询数据所限制的时间范围，可以是指定时间和最近时间两种方式。为空表示不限时间范围")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TimeRange {

    private TimeRangeType timeType;

    private Long fromTime;

    private Long toTime;

    private Integer recentSeconds;

    public TimeRange() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public TimeRange(TimeRangeType timeType) {
        this.timeType = timeType;
    }

    public TimeRange timeType(TimeRangeType timeType) {
        this.timeType = timeType;
        return this;
    }

    /**
     * Get timeType
     *
     * @return timeType
     */
    @NotNull
    @Valid
    @Schema(name = "timeType", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("timeType")
    public TimeRangeType getTimeType() {
        return timeType;
    }

    public void setTimeType(TimeRangeType timeType) {
        this.timeType = timeType;
    }

    public TimeRange fromTime(Long fromTime) {
        this.fromTime = fromTime;
        return this;
    }

    /**
     * 指定时间段情况下的起始时间戳，整数，UTC 1970-1-1开始的毫秒数
     *
     * @return fromTime
     */

    @Schema(name = "fromTime", description = "指定时间段情况下的起始时间戳，整数，UTC 1970-1-1开始的毫秒数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("fromTime")
    public Long getFromTime() {
        return fromTime;
    }

    public void setFromTime(Long fromTime) {
        this.fromTime = fromTime;
    }

    public TimeRange toTime(Long toTime) {
        this.toTime = toTime;
        return this;
    }

    /**
     * 指定时间段情况下的截止时间戳，整数，UTC 1970-1-1开始的毫秒数
     *
     * @return toTime
     */

    @Schema(name = "toTime", description = "指定时间段情况下的截止时间戳，整数，UTC 1970-1-1开始的毫秒数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("toTime")
    public Long getToTime() {
        return toTime;
    }

    public void setToTime(Long toTime) {
        this.toTime = toTime;
    }

    public TimeRange recentSeconds(Integer recentSeconds) {
        this.recentSeconds = recentSeconds;
        return this;
    }

    /**
     * timeType为1时，最近时间的秒数，整数
     *
     * @return recentSeconds
     */

    @Schema(name = "recentSeconds", description = "timeType为1时，最近时间的秒数，整数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("recentSeconds")
    public Integer getRecentSeconds() {
        return recentSeconds;
    }

    public void setRecentSeconds(Integer recentSeconds) {
        this.recentSeconds = recentSeconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeRange timeRange = (TimeRange) o;
        return Objects.equals(this.timeType, timeRange.timeType) &&
                Objects.equals(this.fromTime, timeRange.fromTime) &&
                Objects.equals(this.toTime, timeRange.toTime) &&
                Objects.equals(this.recentSeconds, timeRange.recentSeconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeType, fromTime, toTime, recentSeconds);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeRange {\n");
        sb.append("    timeType: ").append(toIndentedString(timeType)).append("\n");
        sb.append("    fromTime: ").append(toIndentedString(fromTime)).append("\n");
        sb.append("    toTime: ").append(toIndentedString(toTime)).append("\n");
        sb.append("    recentSeconds: ").append(toIndentedString(recentSeconds)).append("\n");
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
