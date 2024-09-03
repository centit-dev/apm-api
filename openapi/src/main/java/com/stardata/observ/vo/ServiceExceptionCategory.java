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
 * 服务报错解析结果的每类异常信息
 */

@Schema(name = "ServiceExceptionCategory", description = "服务报错解析结果的每类异常信息")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceExceptionCategory {

    private Integer categoryId;

    private String categoryName;

    private Integer spanCount;

    @Valid
    private List<@Valid ServiceExceptionInfo> exceptions = new ArrayList<>();

    public ServiceExceptionCategory() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public ServiceExceptionCategory(Integer categoryId, String categoryName, Integer spanCount, List<@Valid ServiceExceptionInfo> exceptions) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.spanCount = spanCount;
        this.exceptions = exceptions;
    }

    public ServiceExceptionCategory categoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    /**
     * 可观测后台配置的异常分类ID
     *
     * @return categoryId
     */
    @NotNull
    @Schema(name = "categoryId", description = "可观测后台配置的异常分类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("categoryId")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public ServiceExceptionCategory categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    /**
     * 可观测后台配置的异常分类名称
     *
     * @return categoryName
     */
    @NotNull
    @Schema(name = "categoryName", description = "可观测后台配置的异常分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("categoryName")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ServiceExceptionCategory spanCount(Integer spanCount) {
        this.spanCount = spanCount;
        return this;
    }

    /**
     * 该异常分类下服务span记录数
     *
     * @return spanCount
     */
    @NotNull
    @Schema(name = "spanCount", description = "该异常分类下服务span记录数", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("spanCount")
    public Integer getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(Integer spanCount) {
        this.spanCount = spanCount;
    }

    public ServiceExceptionCategory exceptions(List<@Valid ServiceExceptionInfo> exceptions) {
        this.exceptions = exceptions;
        return this;
    }

    public ServiceExceptionCategory addExceptionsItem(ServiceExceptionInfo exceptionsItem) {
        if (this.exceptions == null) {
            this.exceptions = new ArrayList<>();
        }
        this.exceptions.add(exceptionsItem);
        return this;
    }

    /**
     * 该分组下出现的异常列表
     *
     * @return exceptions
     */
    @NotNull
    @Valid
    @Schema(name = "exceptions", description = "该分组下出现的异常列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("exceptions")
    public List<@Valid ServiceExceptionInfo> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<@Valid ServiceExceptionInfo> exceptions) {
        this.exceptions = exceptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceExceptionCategory serviceExceptionCategory = (ServiceExceptionCategory) o;
        return Objects.equals(this.categoryId, serviceExceptionCategory.categoryId) &&
                Objects.equals(this.categoryName, serviceExceptionCategory.categoryName) &&
                Objects.equals(this.spanCount, serviceExceptionCategory.spanCount) &&
                Objects.equals(this.exceptions, serviceExceptionCategory.exceptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName, spanCount, exceptions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ServiceExceptionCategory {\n");
        sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
        sb.append("    categoryName: ").append(toIndentedString(categoryName)).append("\n");
        sb.append("    spanCount: ").append(toIndentedString(spanCount)).append("\n");
        sb.append("    exceptions: ").append(toIndentedString(exceptions)).append("\n");
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
