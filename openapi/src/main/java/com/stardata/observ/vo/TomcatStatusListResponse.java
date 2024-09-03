package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * TomcatStatusListResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TomcatStatusListResponse {

    private Integer code;

    private String message;

    private TomcatStatusList data;

    public TomcatStatusListResponse() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public TomcatStatusListResponse(Integer code, String message, TomcatStatusList data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public TomcatStatusListResponse code(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * Get code
     *
     * @return code
     */
    @NotNull
    @Schema(name = "code", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public TomcatStatusListResponse message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get message
     *
     * @return message
     */
    @NotNull
    @Schema(name = "message", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TomcatStatusListResponse data(TomcatStatusList data) {
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
    public TomcatStatusList getData() {
        return data;
    }

    public void setData(TomcatStatusList data) {
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
        TomcatStatusListResponse tomcatStatusListResponse = (TomcatStatusListResponse) o;
        return Objects.equals(this.code, tomcatStatusListResponse.code) &&
                Objects.equals(this.message, tomcatStatusListResponse.message) &&
                Objects.equals(this.data, tomcatStatusListResponse.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TomcatStatusListResponse {\n");
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
