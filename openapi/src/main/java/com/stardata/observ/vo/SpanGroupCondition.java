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
 * SpanGroupCondition
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SpanGroupCondition {

    @Valid
    private List<@Valid FieldCondition> fieldConditions;

    private TimeWindow timeWindow;

    private Integer granularity;

    private DurationRange durationInterval;

    private String userId;

    private String id;

    public SpanGroupCondition() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public SpanGroupCondition(TimeWindow timeWindow, Integer granularity, DurationRange durationInterval, String userId, String id) {
        this.timeWindow = timeWindow;
        this.granularity = granularity;
        this.durationInterval = durationInterval;
        this.userId = userId;
        this.id = id;
    }

    public SpanGroupCondition fieldConditions(List<@Valid FieldCondition> fieldConditions) {
        this.fieldConditions = fieldConditions;
        return this;
    }

    public SpanGroupCondition addFieldConditionsItem(FieldCondition fieldConditionsItem) {
        if (this.fieldConditions == null) {
            this.fieldConditions = new ArrayList<>();
        }
        this.fieldConditions.add(fieldConditionsItem);
        return this;
    }

    /**
     * 选择span的字段名称、操作符、字段值作为条件组合
     *
     * @return fieldConditions
     */
    @Valid
    @Schema(name = "fieldConditions", description = "选择span的字段名称、操作符、字段值作为条件组合", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("fieldConditions")
    public List<@Valid FieldCondition> getFieldConditions() {
        return fieldConditions;
    }

    public void setFieldConditions(List<@Valid FieldCondition> fieldConditions) {
        this.fieldConditions = fieldConditions;
    }

    public SpanGroupCondition timeWindow(TimeWindow timeWindow) {
        this.timeWindow = timeWindow;
        return this;
    }

    /**
     * Get timeWindow
     *
     * @return timeWindow
     */
    @NotNull
    @Valid
    @Schema(name = "timeWindow", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("timeWindow")
    public TimeWindow getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(TimeWindow timeWindow) {
        this.timeWindow = timeWindow;
    }

    public SpanGroupCondition granularity(Integer granularity) {
        this.granularity = granularity;
        return this;
    }

    /**
     * 时间颗粒度，单位是秒
     *
     * @return granularity
     */
    @NotNull
    @Schema(name = "granularity", description = "时间颗粒度，单位是秒", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("granularity")
    public Integer getGranularity() {
        return granularity;
    }

    public void setGranularity(Integer granularity) {
        this.granularity = granularity;
    }

    public SpanGroupCondition durationInterval(DurationRange durationInterval) {
        this.durationInterval = durationInterval;
        return this;
    }

    /**
     * Get durationInterval
     *
     * @return durationInterval
     */
    @NotNull
    @Valid
    @Schema(name = "durationInterval", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("durationInterval")
    public DurationRange getDurationInterval() {
        return durationInterval;
    }

    public void setDurationInterval(DurationRange durationInterval) {
        this.durationInterval = durationInterval;
    }

    public SpanGroupCondition userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 关联用户ID，用户登录任何机器都能看到历史条件
     *
     * @return userId
     */
    @NotNull
    @Schema(name = "userId", description = "关联用户ID，用户登录任何机器都能看到历史条件", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SpanGroupCondition id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 数据库编号
     *
     * @return id
     */
    @NotNull
    @Schema(name = "id", description = "数据库编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpanGroupCondition spanGroupCondition = (SpanGroupCondition) o;
        return Objects.equals(this.fieldConditions, spanGroupCondition.fieldConditions) &&
                Objects.equals(this.timeWindow, spanGroupCondition.timeWindow) &&
                Objects.equals(this.granularity, spanGroupCondition.granularity) &&
                Objects.equals(this.durationInterval, spanGroupCondition.durationInterval) &&
                Objects.equals(this.userId, spanGroupCondition.userId) &&
                Objects.equals(this.id, spanGroupCondition.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldConditions, timeWindow, granularity, durationInterval, userId, id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SpanGroupCondition {\n");
        sb.append("    fieldConditions: ").append(toIndentedString(fieldConditions)).append("\n");
        sb.append("    timeWindow: ").append(toIndentedString(timeWindow)).append("\n");
        sb.append("    granularity: ").append(toIndentedString(granularity)).append("\n");
        sb.append("    durationInterval: ").append(toIndentedString(durationInterval)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
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
