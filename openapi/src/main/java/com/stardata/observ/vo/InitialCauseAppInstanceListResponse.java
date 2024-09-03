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
 * InitialCauseAppInstanceListResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class InitialCauseAppInstanceListResponse {

    private Integer code;

    private String message;

    @Valid
    private List<@Valid InitialCauseAppInstance> data = new ArrayList<>();

    public InitialCauseAppInstanceListResponse() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public InitialCauseAppInstanceListResponse(Integer code, String message, List<@Valid InitialCauseAppInstance> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public InitialCauseAppInstanceListResponse code(Integer code) {
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

    public InitialCauseAppInstanceListResponse message(String message) {
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

    public InitialCauseAppInstanceListResponse data(List<@Valid InitialCauseAppInstance> data) {
        this.data = data;
        return this;
    }

    public InitialCauseAppInstanceListResponse addDataItem(InitialCauseAppInstance dataItem) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data.add(dataItem);
        return this;
    }

    /**
     * 异常应用实例的列表数据
     *
     * @return data
     */
    @NotNull
    @Valid
    @Schema(name = "data", description = "异常应用实例的列表数据", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("data")
    public List<@Valid InitialCauseAppInstance> getData() {
        return data;
    }

    public void setData(List<@Valid InitialCauseAppInstance> data) {
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
        InitialCauseAppInstanceListResponse initialCauseAppInstanceListResponse = (InitialCauseAppInstanceListResponse) o;
        return Objects.equals(this.code, initialCauseAppInstanceListResponse.code) &&
                Objects.equals(this.message, initialCauseAppInstanceListResponse.message) &&
                Objects.equals(this.data, initialCauseAppInstanceListResponse.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class InitialCauseAppInstanceListResponse {\n");
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
