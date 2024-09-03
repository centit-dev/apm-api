package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 服务报错解析结果的每类异常下的每个异常信息
 */

@Schema(name = "ServiceExceptionInfo", description = "服务报错解析结果的每类异常下的每个异常信息")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceExceptionInfo {

    private Integer exceptionId;

    private String exceptionName;

    private String rootExceptionBody;

    private Integer spanCount;

    public ServiceExceptionInfo() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public ServiceExceptionInfo(Integer exceptionId, String exceptionName, String rootExceptionBody, Integer spanCount) {
        this.exceptionId = exceptionId;
        this.exceptionName = exceptionName;
        this.rootExceptionBody = rootExceptionBody;
        this.spanCount = spanCount;
    }

    public ServiceExceptionInfo exceptionId(Integer exceptionId) {
        this.exceptionId = exceptionId;
        return this;
    }

    /**
     * 可观测后台配置的异常ID
     *
     * @return exceptionId
     */
    @NotNull
    @Schema(name = "exceptionId", description = "可观测后台配置的异常ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("exceptionId")
    public Integer getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Integer exceptionId) {
        this.exceptionId = exceptionId;
    }

    public ServiceExceptionInfo exceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
        return this;
    }

    /**
     * 可观测后台配置的异常名称
     *
     * @return exceptionName
     */
    @NotNull
    @Schema(name = "exceptionName", description = "可观测后台配置的异常名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("exceptionName")
    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public ServiceExceptionInfo rootExceptionBody(String rootExceptionBody) {
        this.rootExceptionBody = rootExceptionBody;
        return this;
    }

    /**
     * 被解析出该异常类型的根因异常日志文本
     *
     * @return rootExceptionBody
     */
    @NotNull
    @Schema(name = "rootExceptionBody", description = "被解析出该异常类型的根因异常日志文本", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("rootExceptionBody")
    public String getRootExceptionBody() {
        return rootExceptionBody;
    }

    public void setRootExceptionBody(String rootExceptionBody) {
        this.rootExceptionBody = rootExceptionBody;
    }

    public ServiceExceptionInfo spanCount(Integer spanCount) {
        this.spanCount = spanCount;
        return this;
    }

    /**
     * 该异常对应的span记录数
     *
     * @return spanCount
     */
    @NotNull
    @Schema(name = "spanCount", description = "该异常对应的span记录数", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("spanCount")
    public Integer getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(Integer spanCount) {
        this.spanCount = spanCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceExceptionInfo serviceExceptionInfo = (ServiceExceptionInfo) o;
        return Objects.equals(this.exceptionId, serviceExceptionInfo.exceptionId) &&
                Objects.equals(this.exceptionName, serviceExceptionInfo.exceptionName) &&
                Objects.equals(this.rootExceptionBody, serviceExceptionInfo.rootExceptionBody) &&
                Objects.equals(this.spanCount, serviceExceptionInfo.spanCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exceptionId, exceptionName, rootExceptionBody, spanCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ServiceExceptionInfo {\n");
        sb.append("    exceptionId: ").append(toIndentedString(exceptionId)).append("\n");
        sb.append("    exceptionName: ").append(toIndentedString(exceptionName)).append("\n");
        sb.append("    rootExceptionBody: ").append(toIndentedString(rootExceptionBody)).append("\n");
        sb.append("    spanCount: ").append(toIndentedString(spanCount)).append("\n");
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
