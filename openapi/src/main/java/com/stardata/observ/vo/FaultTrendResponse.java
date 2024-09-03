package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * FaultTrendResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class FaultTrendResponse {

    private Integer code;

    private String message;

    private FaultTrend data;

    public FaultTrendResponse() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public FaultTrendResponse(Integer code, String message, FaultTrend data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public FaultTrendResponse code(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * 如果请求响应码不为零，则表示出现业务错误。
     *
     * @return code
     */
    @NotNull
    @Schema(name = "code", description = "如果请求响应码不为零，则表示出现业务错误。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public FaultTrendResponse message(String message) {
        this.message = message;
        return this;
    }

    /**
     * 如果响应没有报错，则为“ok”
     *
     * @return message
     */
    @NotNull
    @Schema(name = "message", description = "如果响应没有报错，则为“ok”", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FaultTrendResponse data(FaultTrend data) {
        this.data = data;
        return this;
    }

    /**
     * Get data
     *
     * @return data
     */
    @NotNull
    @Valid
    @Schema(name = "data", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("data")
    public FaultTrend getData() {
        return data;
    }

    public void setData(FaultTrend data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FaultTrendResponse faultTrendResponse = (FaultTrendResponse) o;
        return Objects.equals(this.code, faultTrendResponse.code) &&
                Objects.equals(this.message, faultTrendResponse.message) &&
                Objects.equals(this.data, faultTrendResponse.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FaultTrendResponse {\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    data: ").append(toIndentedString(data)).append("\n");
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
