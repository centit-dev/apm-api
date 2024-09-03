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
 * SpanSnapshotCondition
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SpanSnapshotCondition {

    private String snapshotId;

    @Valid
    private List<@Valid StatusCondition> statusConditions;

    private TimeWindow timeWindow;

    private DurationRange durationInterval;

    @Valid
    private List<@Valid FieldCondition> fieldConditions;

    public SpanSnapshotCondition() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public SpanSnapshotCondition(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    public SpanSnapshotCondition snapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
        return this;
    }

    /**
     * span快照ID
     *
     * @return snapshotId
     */
    @NotNull
    @Schema(name = "snapshotId", description = "span快照ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("snapshotId")
    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    public SpanSnapshotCondition statusConditions(List<@Valid StatusCondition> statusConditions) {
        this.statusConditions = statusConditions;
        return this;
    }

    public SpanSnapshotCondition addStatusConditionsItem(StatusCondition statusConditionsItem) {
        if (this.statusConditions == null) {
            this.statusConditions = new ArrayList<>();
        }
        this.statusConditions.add(statusConditionsItem);
        return this;
    }

    /**
     * 该快照对应的状态条件，是个数组
     *
     * @return statusConditions
     */
    @Valid
    @Schema(name = "statusConditions", description = "该快照对应的状态条件，是个数组", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("statusConditions")
    public List<@Valid StatusCondition> getStatusConditions() {
        return statusConditions;
    }

    public void setStatusConditions(List<@Valid StatusCondition> statusConditions) {
        this.statusConditions = statusConditions;
    }

    public SpanSnapshotCondition timeWindow(TimeWindow timeWindow) {
        this.timeWindow = timeWindow;
        return this;
    }

    /**
     * Get timeWindow
     *
     * @return timeWindow
     */
    @Valid
    @Schema(name = "timeWindow", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("timeWindow")
    public TimeWindow getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(TimeWindow timeWindow) {
        this.timeWindow = timeWindow;
    }

    public SpanSnapshotCondition durationInterval(DurationRange durationInterval) {
        this.durationInterval = durationInterval;
        return this;
    }

    /**
     * Get durationInterval
     *
     * @return durationInterval
     */
    @Valid
    @Schema(name = "durationInterval", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("durationInterval")
    public DurationRange getDurationInterval() {
        return durationInterval;
    }

    public void setDurationInterval(DurationRange durationInterval) {
        this.durationInterval = durationInterval;
    }

    public SpanSnapshotCondition fieldConditions(List<@Valid FieldCondition> fieldConditions) {
        this.fieldConditions = fieldConditions;
        return this;
    }

    public SpanSnapshotCondition addFieldConditionsItem(FieldCondition fieldConditionsItem) {
        if (this.fieldConditions == null) {
            this.fieldConditions = new ArrayList<>();
        }
        this.fieldConditions.add(fieldConditionsItem);
        return this;
    }

    /**
     * 用于建立子快照的字段条件数组，为空表示不限
     *
     * @return fieldConditions
     */
    @Valid
    @Schema(name = "fieldConditions", description = "用于建立子快照的字段条件数组，为空表示不限", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("fieldConditions")
    public List<@Valid FieldCondition> getFieldConditions() {
        return fieldConditions;
    }

    public void setFieldConditions(List<@Valid FieldCondition> fieldConditions) {
        this.fieldConditions = fieldConditions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpanSnapshotCondition spanSnapshotCondition = (SpanSnapshotCondition) o;
        return Objects.equals(this.snapshotId, spanSnapshotCondition.snapshotId) &&
                Objects.equals(this.statusConditions, spanSnapshotCondition.statusConditions) &&
                Objects.equals(this.timeWindow, spanSnapshotCondition.timeWindow) &&
                Objects.equals(this.durationInterval, spanSnapshotCondition.durationInterval) &&
                Objects.equals(this.fieldConditions, spanSnapshotCondition.fieldConditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(snapshotId, statusConditions, timeWindow, durationInterval, fieldConditions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SpanSnapshotCondition {\n");
        sb.append("    snapshotId: ").append(toIndentedString(snapshotId)).append("\n");
        sb.append("    statusConditions: ").append(toIndentedString(statusConditions)).append("\n");
        sb.append("    timeWindow: ").append(toIndentedString(timeWindow)).append("\n");
        sb.append("    durationInterval: ").append(toIndentedString(durationInterval)).append("\n");
        sb.append("    fieldConditions: ").append(toIndentedString(fieldConditions)).append("\n");
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
