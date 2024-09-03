package com.stardata.observ.vo;

import java.math.BigDecimal;
import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * InstanceRedData
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class InstanceRedData {

    private String serviceName;

    private String spanName;

    private Long calls;

    private BigDecimal successRate;

    private Long durationP90;

    public InstanceRedData() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public InstanceRedData(String serviceName, String spanName, Long calls, BigDecimal successRate, Long durationP90) {
        this.serviceName = serviceName;
        this.spanName = spanName;
        this.calls = calls;
        this.successRate = successRate;
        this.durationP90 = durationP90;
    }

    public InstanceRedData serviceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    /**
     * Get serviceName
     *
     * @return serviceName
     */
    @NotNull
    @Schema(name = "serviceName", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("serviceName")
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public InstanceRedData spanName(String spanName) {
        this.spanName = spanName;
        return this;
    }

    /**
     * Get spanName
     *
     * @return spanName
     */
    @NotNull
    @Schema(name = "spanName", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("spanName")
    public String getSpanName() {
        return spanName;
    }

    public void setSpanName(String spanName) {
        this.spanName = spanName;
    }

    public InstanceRedData calls(Long calls) {
        this.calls = calls;
        return this;
    }

    /**
     * Get calls
     *
     * @return calls
     */
    @NotNull
    @Schema(name = "calls", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("calls")
    public Long getCalls() {
        return calls;
    }

    public void setCalls(Long calls) {
        this.calls = calls;
    }

    public InstanceRedData successRate(BigDecimal successRate) {
        this.successRate = successRate;
        return this;
    }

    /**
     * Get successRate
     *
     * @return successRate
     */
    @NotNull
    @Valid
    @Schema(name = "successRate", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("successRate")
    public BigDecimal getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(BigDecimal successRate) {
        this.successRate = successRate;
    }

    public InstanceRedData durationP90(Long durationP90) {
        this.durationP90 = durationP90;
        return this;
    }

    /**
     * Get durationP90
     *
     * @return durationP90
     */
    @NotNull
    @Schema(name = "durationP90", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("durationP90")
    public Long getDurationP90() {
        return durationP90;
    }

    public void setDurationP90(Long durationP90) {
        this.durationP90 = durationP90;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InstanceRedData instanceRedData = (InstanceRedData) o;
        return Objects.equals(this.serviceName, instanceRedData.serviceName) &&
                Objects.equals(this.spanName, instanceRedData.spanName) &&
                Objects.equals(this.calls, instanceRedData.calls) &&
                Objects.equals(this.successRate, instanceRedData.successRate) &&
                Objects.equals(this.durationP90, instanceRedData.durationP90);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceName, spanName, calls, successRate, durationP90);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class InstanceRedData {\n");
        sb.append("    serviceName: ").append(toIndentedString(serviceName)).append("\n");
        sb.append("    spanName: ").append(toIndentedString(spanName)).append("\n");
        sb.append("    calls: ").append(toIndentedString(calls)).append("\n");
        sb.append("    successRate: ").append(toIndentedString(successRate)).append("\n");
        sb.append("    durationP90: ").append(toIndentedString(durationP90)).append("\n");
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
