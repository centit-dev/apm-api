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
 * TracePage
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TracePage {

    private Integer total;

    private Integer pageSize;

    private Integer pageNo;

    @Valid
    private List<@Valid TraceBrief> content = new ArrayList<>();

    public TracePage() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public TracePage(Integer total, Integer pageSize, Integer pageNo, List<@Valid TraceBrief> content) {
        this.total = total;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.content = content;
    }

    public TracePage total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * Get total
     *
     * @return total
     */
    @NotNull
    @Schema(name = "total", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public TracePage pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * Get pageSize
     *
     * @return pageSize
     */
    @NotNull
    @Schema(name = "pageSize", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("pageSize")
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public TracePage pageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    /**
     * Get pageNo
     *
     * @return pageNo
     */
    @NotNull
    @Schema(name = "pageNo", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("pageNo")
    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public TracePage content(List<@Valid TraceBrief> content) {
        this.content = content;
        return this;
    }

    public TracePage addContentItem(TraceBrief contentItem) {
        if (this.content == null) {
            this.content = new ArrayList<>();
        }
        this.content.add(contentItem);
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
    public List<@Valid TraceBrief> getContent() {
        return content;
    }

    public void setContent(List<@Valid TraceBrief> content) {
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
        TracePage tracePage = (TracePage) o;
        return Objects.equals(this.total, tracePage.total) &&
                Objects.equals(this.pageSize, tracePage.pageSize) &&
                Objects.equals(this.pageNo, tracePage.pageNo) &&
                Objects.equals(this.content, tracePage.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, pageSize, pageNo, content);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TracePage {\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    pageNo: ").append(toIndentedString(pageNo)).append("\n");
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
