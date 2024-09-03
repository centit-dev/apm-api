package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * GraphPointData
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class GraphPointData {

    private Long businessFaultCount;

    private Long systemFaultCount;

    private Long successCount;

    public GraphPointData() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public GraphPointData(Long businessFaultCount, Long systemFaultCount) {
        this.businessFaultCount = businessFaultCount;
        this.systemFaultCount = systemFaultCount;
    }

    public GraphPointData businessFaultCount(Long businessFaultCount) {
        this.businessFaultCount = businessFaultCount;
        return this;
    }

    /**
     * 在趋势图、热点图上显示时，某个图形点的业务失败次数。
     *
     * @return businessFaultCount
     */
    @NotNull
    @Schema(name = "businessFaultCount", description = "在趋势图、热点图上显示时，某个图形点的业务失败次数。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("businessFaultCount")
    public Long getBusinessFaultCount() {
        return businessFaultCount;
    }

    public void setBusinessFaultCount(Long businessFaultCount) {
        this.businessFaultCount = businessFaultCount;
    }

    public GraphPointData systemFaultCount(Long systemFaultCount) {
        this.systemFaultCount = systemFaultCount;
        return this;
    }

    /**
     * 在趋势图、热点图上显示时，某个图形点的系统失败次数。
     *
     * @return systemFaultCount
     */
    @NotNull
    @Schema(name = "systemFaultCount", description = "在趋势图、热点图上显示时，某个图形点的系统失败次数。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("systemFaultCount")
    public Long getSystemFaultCount() {
        return systemFaultCount;
    }

    public void setSystemFaultCount(Long systemFaultCount) {
        this.systemFaultCount = systemFaultCount;
    }

    public GraphPointData successCount(Long successCount) {
        this.successCount = successCount;
        return this;
    }

    /**
     * 在热点图上显示时，某个图形点的业务成功次数。
     *
     * @return successCount
     */

    @Schema(name = "successCount", description = "在热点图上显示时，某个图形点的业务成功次数。", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("successCount")
    public Long getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Long successCount) {
        this.successCount = successCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GraphPointData graphPointData = (GraphPointData) o;
        return Objects.equals(this.businessFaultCount, graphPointData.businessFaultCount) &&
                Objects.equals(this.systemFaultCount, graphPointData.systemFaultCount) &&
                Objects.equals(this.successCount, graphPointData.successCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessFaultCount, systemFaultCount, successCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GraphPointData {\n");
        sb.append("    businessFaultCount: ").append(toIndentedString(businessFaultCount)).append("\n");
        sb.append("    systemFaultCount: ").append(toIndentedString(systemFaultCount)).append("\n");
        sb.append("    successCount: ").append(toIndentedString(successCount)).append("\n");
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
