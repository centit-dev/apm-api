package com.stardata.observ.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * SpanSubSnapshotRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SpanSubSnapshotRequest {

    private TimeWindow timeWindow;

    private DurationRange durationInterval;

    @Valid
    private List<@Valid FieldCondition> fieldConditions;

    public SpanSubSnapshotRequest timeWindow(TimeWindow timeWindow) {
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

    public SpanSubSnapshotRequest durationInterval(DurationRange durationInterval) {
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

    public SpanSubSnapshotRequest fieldConditions(List<@Valid FieldCondition> fieldConditions) {
        this.fieldConditions = fieldConditions;
        return this;
    }

    public SpanSubSnapshotRequest addFieldConditionsItem(FieldCondition fieldConditionsItem) {
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
        SpanSubSnapshotRequest spanSubSnapshotRequest = (SpanSubSnapshotRequest) o;
        return Objects.equals(this.timeWindow, spanSubSnapshotRequest.timeWindow) &&
                Objects.equals(this.durationInterval, spanSubSnapshotRequest.durationInterval) &&
                Objects.equals(this.fieldConditions, spanSubSnapshotRequest.fieldConditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeWindow, durationInterval, fieldConditions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SpanSubSnapshotRequest {\n");
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
