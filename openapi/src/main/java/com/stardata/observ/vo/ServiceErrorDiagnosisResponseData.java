package com.stardata.observ.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ServiceErrorDiagnosisResponseData
 */

@JsonTypeName("ServiceErrorDiagnosisResponse_data")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceErrorDiagnosisResponseData {

    private Integer spanCount;

    @Valid
    private List<@Valid ServiceExceptionCategory> categories = new ArrayList<>();

    public ServiceErrorDiagnosisResponseData() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public ServiceErrorDiagnosisResponseData(Integer spanCount, List<@Valid ServiceExceptionCategory> categories) {
        this.spanCount = spanCount;
        this.categories = categories;
    }

    public ServiceErrorDiagnosisResponseData spanCount(Integer spanCount) {
        this.spanCount = spanCount;
        return this;
    }

    /**
     * 有报错的服务span总记录数
     *
     * @return spanCount
     */
    @NotNull
    @Schema(name = "spanCount", description = "有报错的服务span总记录数", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("spanCount")
    public Integer getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(Integer spanCount) {
        this.spanCount = spanCount;
    }

    public ServiceErrorDiagnosisResponseData categories(List<@Valid ServiceExceptionCategory> categories) {
        this.categories = categories;
        return this;
    }

    public ServiceErrorDiagnosisResponseData addCategoriesItem(ServiceExceptionCategory categoriesItem) {
        if (this.categories == null) {
            this.categories = new ArrayList<>();
        }
        this.categories.add(categoriesItem);
        return this;
    }

    /**
     * 针对参与解析的服务报错记录，解析出来的异常分类数组
     *
     * @return categories
     */
    @NotNull
    @Valid
    @Schema(name = "categories", description = "针对参与解析的服务报错记录，解析出来的异常分类数组", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("categories")
    public List<@Valid ServiceExceptionCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<@Valid ServiceExceptionCategory> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceErrorDiagnosisResponseData serviceErrorDiagnosisResponseData = (ServiceErrorDiagnosisResponseData) o;
        return Objects.equals(this.spanCount, serviceErrorDiagnosisResponseData.spanCount) &&
                Objects.equals(this.categories, serviceErrorDiagnosisResponseData.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spanCount, categories);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ServiceErrorDiagnosisResponseData {\n");
        sb.append("    spanCount: ").append(toIndentedString(spanCount)).append("\n");
        sb.append("    categories: ").append(toIndentedString(categories)).append("\n");
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
