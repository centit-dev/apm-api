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
 * 可能是列表，也可能是对象，也可能不返回。
 */

@Schema(name = "Heatmap", description = "可能是列表，也可能是对象，也可能不返回。")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Heatmap {

    private Long fromTime;

    private Long toTime;

    private Long fromDuration;

    private Long toDuration;

    private Integer timeGranularity;

    private Integer durationGranularity;

    @Valid
    private List<List<@Valid GraphPointData>> values;

    public Heatmap() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public Heatmap(Long fromDuration, Long toDuration, Integer timeGranularity, Integer durationGranularity) {
        this.fromDuration = fromDuration;
        this.toDuration = toDuration;
        this.timeGranularity = timeGranularity;
        this.durationGranularity = durationGranularity;
    }

    public Heatmap fromTime(Long fromTime) {
        this.fromTime = fromTime;
        return this;
    }

    /**
     * Unix时间戳
     *
     * @return fromTime
     */

    @Schema(name = "fromTime", description = "Unix时间戳", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("fromTime")
    public Long getFromTime() {
        return fromTime;
    }

    public void setFromTime(Long fromTime) {
        this.fromTime = fromTime;
    }

    public Heatmap toTime(Long toTime) {
        this.toTime = toTime;
        return this;
    }

    /**
     * Unix时间戳
     *
     * @return toTime
     */

    @Schema(name = "toTime", description = "Unix时间戳", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("toTime")
    public Long getToTime() {
        return toTime;
    }

    public void setToTime(Long toTime) {
        this.toTime = toTime;
    }

    public Heatmap fromDuration(Long fromDuration) {
        this.fromDuration = fromDuration;
        return this;
    }

    /**
     * 时延起始值，单位ms
     *
     * @return fromDuration
     */
    @NotNull
    @Schema(name = "fromDuration", description = "时延起始值，单位ms", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("fromDuration")
    public Long getFromDuration() {
        return fromDuration;
    }

    public void setFromDuration(Long fromDuration) {
        this.fromDuration = fromDuration;
    }

    public Heatmap toDuration(Long toDuration) {
        this.toDuration = toDuration;
        return this;
    }

    /**
     * 时延截止值，单位ms
     *
     * @return toDuration
     */
    @NotNull
    @Schema(name = "toDuration", description = "时延截止值，单位ms", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("toDuration")
    public Long getToDuration() {
        return toDuration;
    }

    public void setToDuration(Long toDuration) {
        this.toDuration = toDuration;
    }

    public Heatmap timeGranularity(Integer timeGranularity) {
        this.timeGranularity = timeGranularity;
        return this;
    }

    /**
     * 返回数据的时间颗粒度，单位秒
     *
     * @return timeGranularity
     */
    @NotNull
    @Schema(name = "timeGranularity", description = "返回数据的时间颗粒度，单位秒", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("timeGranularity")
    public Integer getTimeGranularity() {
        return timeGranularity;
    }

    public void setTimeGranularity(Integer timeGranularity) {
        this.timeGranularity = timeGranularity;
    }

    public Heatmap durationGranularity(Integer durationGranularity) {
        this.durationGranularity = durationGranularity;
        return this;
    }

    /**
     * 返回数据的时延颗粒度，单位毫秒
     *
     * @return durationGranularity
     */
    @NotNull
    @Schema(name = "durationGranularity", description = "返回数据的时延颗粒度，单位毫秒", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("durationGranularity")
    public Integer getDurationGranularity() {
        return durationGranularity;
    }

    public void setDurationGranularity(Integer durationGranularity) {
        this.durationGranularity = durationGranularity;
    }

    public Heatmap values(List<List<@Valid GraphPointData>> values) {
        this.values = values;
        return this;
    }

    public Heatmap addValuesItem(List<@Valid GraphPointData> valuesItem) {
        if (this.values == null) {
            this.values = new ArrayList<>();
        }
        this.values.add(valuesItem);
        return this;
    }

    /**
     * Get values
     *
     * @return values
     */
    @Valid
    @Schema(name = "values", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("values")
    public List<List<@Valid GraphPointData>> getValues() {
        return values;
    }

    public void setValues(List<List<@Valid GraphPointData>> values) {
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
        Heatmap heatmap = (Heatmap) o;
        return Objects.equals(this.fromTime, heatmap.fromTime) &&
                Objects.equals(this.toTime, heatmap.toTime) &&
                Objects.equals(this.fromDuration, heatmap.fromDuration) &&
                Objects.equals(this.toDuration, heatmap.toDuration) &&
                Objects.equals(this.timeGranularity, heatmap.timeGranularity) &&
                Objects.equals(this.durationGranularity, heatmap.durationGranularity) &&
                Objects.equals(this.values, heatmap.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromTime, toTime, fromDuration, toDuration, timeGranularity, durationGranularity, values);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Heatmap {\n");
        sb.append("    fromTime: ").append(toIndentedString(fromTime)).append("\n");
        sb.append("    toTime: ").append(toIndentedString(toTime)).append("\n");
        sb.append("    fromDuration: ").append(toIndentedString(fromDuration)).append("\n");
        sb.append("    toDuration: ").append(toIndentedString(toDuration)).append("\n");
        sb.append("    timeGranularity: ").append(toIndentedString(timeGranularity)).append("\n");
        sb.append("    durationGranularity: ").append(toIndentedString(durationGranularity)).append("\n");
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
