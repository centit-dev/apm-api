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
 * TomcatStatusList
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TomcatStatusList {

    private Long fromTime;

    private Long toTime;

    private Integer granularity;

    @Valid
    private List<@Valid TomcatStatus> values = new ArrayList<>();

    public TomcatStatusList() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public TomcatStatusList(Long fromTime, Long toTime, Integer granularity, List<@Valid TomcatStatus> values) {
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.granularity = granularity;
        this.values = values;
    }

    public TomcatStatusList fromTime(Long fromTime) {
        this.fromTime = fromTime;
        return this;
    }

    /**
     * Unix时间戳
     *
     * @return fromTime
     */
    @NotNull
    @Schema(name = "fromTime", description = "Unix时间戳", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("fromTime")
    public Long getFromTime() {
        return fromTime;
    }

    public void setFromTime(Long fromTime) {
        this.fromTime = fromTime;
    }

    public TomcatStatusList toTime(Long toTime) {
        this.toTime = toTime;
        return this;
    }

    /**
     * Unix时间戳
     *
     * @return toTime
     */
    @NotNull
    @Schema(name = "toTime", description = "Unix时间戳", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("toTime")
    public Long getToTime() {
        return toTime;
    }

    public void setToTime(Long toTime) {
        this.toTime = toTime;
    }

    public TomcatStatusList granularity(Integer granularity) {
        this.granularity = granularity;
        return this;
    }

    /**
     * 返回数据的时间颗粒度，单位秒
     *
     * @return granularity
     */
    @NotNull
    @Schema(name = "granularity", description = "返回数据的时间颗粒度，单位秒", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("granularity")
    public Integer getGranularity() {
        return granularity;
    }

    public void setGranularity(Integer granularity) {
        this.granularity = granularity;
    }

    public TomcatStatusList values(List<@Valid TomcatStatus> values) {
        this.values = values;
        return this;
    }

    public TomcatStatusList addValuesItem(TomcatStatus valuesItem) {
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
    @NotNull
    @Valid
    @Schema(name = "values", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("values")
    public List<@Valid TomcatStatus> getValues() {
        return values;
    }

    public void setValues(List<@Valid TomcatStatus> values) {
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
        TomcatStatusList tomcatStatusList = (TomcatStatusList) o;
        return Objects.equals(this.fromTime, tomcatStatusList.fromTime) &&
                Objects.equals(this.toTime, tomcatStatusList.toTime) &&
                Objects.equals(this.granularity, tomcatStatusList.granularity) &&
                Objects.equals(this.values, tomcatStatusList.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromTime, toTime, granularity, values);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TomcatStatusList {\n");
        sb.append("    fromTime: ").append(toIndentedString(fromTime)).append("\n");
        sb.append("    toTime: ").append(toIndentedString(toTime)).append("\n");
        sb.append("    granularity: ").append(toIndentedString(granularity)).append("\n");
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
