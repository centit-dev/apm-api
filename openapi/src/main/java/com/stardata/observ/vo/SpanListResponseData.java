package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * SpanListResponseData
 */

@JsonTypeName("SpanListResponse_data")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SpanListResponseData {

    private Integer total;

    private Integer pageNo;

    private Integer pageSize;

    private SpanBrief content;

    public SpanListResponseData() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public SpanListResponseData(Integer total, Integer pageNo, Integer pageSize, SpanBrief content) {
        this.total = total;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.content = content;
    }

    public SpanListResponseData total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 总页数
     *
     * @return total
     */
    @NotNull
    @Schema(name = "total", description = "总页数", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public SpanListResponseData pageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    /**
     * 页数，从0开始
     *
     * @return pageNo
     */
    @NotNull
    @Schema(name = "pageNo", description = "页数，从0开始", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("pageNo")
    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public SpanListResponseData pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页元素数量
     *
     * @return pageSize
     */
    @NotNull
    @Schema(name = "pageSize", description = "每页元素数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("pageSize")
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public SpanListResponseData content(SpanBrief content) {
        this.content = content;
        return this;
    }

    /**
     * Get content
     *
     * @return content
     */
    @NotNull
    @Valid
    @Schema(name = "content", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("content")
    public SpanBrief getContent() {
        return content;
    }

    public void setContent(SpanBrief content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpanListResponseData spanListResponseData = (SpanListResponseData) o;
        return Objects.equals(this.total, spanListResponseData.total) &&
                Objects.equals(this.pageNo, spanListResponseData.pageNo) &&
                Objects.equals(this.pageSize, spanListResponseData.pageSize) &&
                Objects.equals(this.content, spanListResponseData.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, pageNo, pageSize, content);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SpanListResponseData {\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    pageNo: ").append(toIndentedString(pageNo)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
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
