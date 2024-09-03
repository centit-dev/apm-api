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
 * QueryTemplate
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class QueryTemplate {

    private Integer id;

    private String name;

    @Valid
    private List<@Valid FieldConditionResponse> fieldConditions = new ArrayList<>();

    @Valid
    private List<@Valid StatusCondition> statusConditions;

    private TimeRange timeCondition;

    private QueryForType _for;

    public QueryTemplate() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public QueryTemplate(Integer id, String name, List<@Valid FieldConditionResponse> fieldConditions, TimeRange timeCondition, QueryForType _for) {
        this.id = id;
        this.name = name;
        this.fieldConditions = fieldConditions;
        this.timeCondition = timeCondition;
        this._for = _for;
    }

    public QueryTemplate id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 模板编码
     *
     * @return id
     */
    @NotNull
    @Schema(name = "id", description = "模板编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public QueryTemplate name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 模板名称
     *
     * @return name
     */
    @NotNull
    @Schema(name = "name", description = "模板名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QueryTemplate fieldConditions(List<@Valid FieldConditionResponse> fieldConditions) {
        this.fieldConditions = fieldConditions;
        return this;
    }

    public QueryTemplate addFieldConditionsItem(FieldConditionResponse fieldConditionsItem) {
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
    public List<@Valid FieldConditionResponse> getFieldConditions() {
        return fieldConditions;
    }

    public void setFieldConditions(List<@Valid FieldConditionResponse> fieldConditions) {
        this.fieldConditions = fieldConditions;
    }

    public QueryTemplate statusConditions(List<@Valid StatusCondition> statusConditions) {
        this.statusConditions = statusConditions;
        return this;
    }

    public QueryTemplate addStatusConditionsItem(StatusCondition statusConditionsItem) {
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

    public QueryTemplate timeCondition(TimeRange timeCondition) {
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

    public QueryTemplate _for(QueryForType _for) {
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
        QueryTemplate queryTemplate = (QueryTemplate) o;
        return Objects.equals(this.id, queryTemplate.id) &&
                Objects.equals(this.name, queryTemplate.name) &&
                Objects.equals(this.fieldConditions, queryTemplate.fieldConditions) &&
                Objects.equals(this.statusConditions, queryTemplate.statusConditions) &&
                Objects.equals(this.timeCondition, queryTemplate.timeCondition) &&
                Objects.equals(this._for, queryTemplate._for);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fieldConditions, statusConditions, timeCondition, _for);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class QueryTemplate {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
