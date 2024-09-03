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
 * QueryConditions
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class QueryConditions {

    @Valid
    private List<@Valid FieldCondition> fieldConditions = new ArrayList<>();

    @Valid
    private List<@Valid StatusCondition> statusConditions;

    private TimeRange timeCondition;

    private QueryForType _for;

    public QueryConditions() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public QueryConditions(List<@Valid FieldCondition> fieldConditions, TimeRange timeCondition, QueryForType _for) {
        this.fieldConditions = fieldConditions;
        this.timeCondition = timeCondition;
        this._for = _for;
    }

    public QueryConditions fieldConditions(List<@Valid FieldCondition> fieldConditions) {
        this.fieldConditions = fieldConditions;
        return this;
    }

    public QueryConditions addFieldConditionsItem(FieldCondition fieldConditionsItem) {
        if (this.fieldConditions == null) {
            this.fieldConditions = new ArrayList<>();
        }
        this.fieldConditions.add(fieldConditionsItem);
        return this;
    }

    /**
     * Get fieldConditions
     *
     * @return fieldConditions
     */
    @NotNull
    @Valid
    @Schema(name = "fieldConditions", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("fieldConditions")
    public List<@Valid FieldCondition> getFieldConditions() {
        return fieldConditions;
    }

    public void setFieldConditions(List<@Valid FieldCondition> fieldConditions) {
        this.fieldConditions = fieldConditions;
    }

    public QueryConditions statusConditions(List<@Valid StatusCondition> statusConditions) {
        this.statusConditions = statusConditions;
        return this;
    }

    public QueryConditions addStatusConditionsItem(StatusCondition statusConditionsItem) {
        if (this.statusConditions == null) {
            this.statusConditions = new ArrayList<>();
        }
        this.statusConditions.add(statusConditionsItem);
        return this;
    }

    /**
     * 按服务状态的指定条件筛选
     *
     * @return statusConditions
     */
    @Valid
    @Schema(name = "statusConditions", description = "按服务状态的指定条件筛选", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("statusConditions")
    public List<@Valid StatusCondition> getStatusConditions() {
        return statusConditions;
    }

    public void setStatusConditions(List<@Valid StatusCondition> statusConditions) {
        this.statusConditions = statusConditions;
    }

    public QueryConditions timeCondition(TimeRange timeCondition) {
        this.timeCondition = timeCondition;
        return this;
    }

    /**
     * Get timeCondition
     *
     * @return timeCondition
     */
    @NotNull
    @Valid
    @Schema(name = "timeCondition", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("timeCondition")
    public TimeRange getTimeCondition() {
        return timeCondition;
    }

    public void setTimeCondition(TimeRange timeCondition) {
        this.timeCondition = timeCondition;
    }

    public QueryConditions _for(QueryForType _for) {
        this._for = _for;
        return this;
    }

    /**
     * Get _for
     *
     * @return _for
     */
    @NotNull
    @Valid
    @Schema(name = "for", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("for")
    public QueryForType getFor() {
        return _for;
    }

    public void setFor(QueryForType _for) {
        this._for = _for;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QueryConditions queryConditions = (QueryConditions) o;
        return Objects.equals(this.fieldConditions, queryConditions.fieldConditions) &&
                Objects.equals(this.statusConditions, queryConditions.statusConditions) &&
                Objects.equals(this.timeCondition, queryConditions.timeCondition) &&
                Objects.equals(this._for, queryConditions._for);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldConditions, statusConditions, timeCondition, _for);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class QueryConditions {\n");
        sb.append("    fieldConditions: ").append(toIndentedString(fieldConditions)).append("\n");
        sb.append("    statusConditions: ").append(toIndentedString(statusConditions)).append("\n");
        sb.append("    timeCondition: ").append(toIndentedString(timeCondition)).append("\n");
        sb.append("    _for: ").append(toIndentedString(_for)).append("\n");
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
