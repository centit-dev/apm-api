package com.stardata.observ.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * SpanPage
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SpanPage {

    private Integer pageNo;

    private Integer pageSize;

    private Integer pageCount;

    private Long totalCount;

    @Valid
    private List<@Valid SpanBrief> spans;

    public SpanPage pageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    /**
     * 其实是请求参数的复制
     *
     * @return pageNo
     */

    @Schema(name = "pageNo", description = "其实是请求参数的复制", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("pageNo")
    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public SpanPage pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 其实是请求参数的复制
     *
     * @return pageSize
     */

    @Schema(name = "pageSize", description = "其实是请求参数的复制", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("pageSize")
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public SpanPage pageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    /**
     * 根据记录数计算结果
     *
     * @return pageCount
     */

    @Schema(name = "pageCount", description = "根据记录数计算结果", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("pageCount")
    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public SpanPage totalCount(Long totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    /**
     * 按条件查询结果的总记录数
     *
     * @return totalCount
     */

    @Schema(name = "totalCount", description = "按条件查询结果的总记录数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("totalCount")
    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public SpanPage spans(List<@Valid SpanBrief> spans) {
        this.spans = spans;
        return this;
    }

    public SpanPage addSpansItem(SpanBrief spansItem) {
        if (this.spans == null) {
            this.spans = new ArrayList<>();
        }
        this.spans.add(spansItem);
        return this;
    }

    /**
     * 查询结果列表
     *
     * @return spans
     */
    @Valid
    @Schema(name = "spans", description = "查询结果列表", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("spans")
    public List<@Valid SpanBrief> getSpans() {
        return spans;
    }

    public void setSpans(List<@Valid SpanBrief> spans) {
        this.spans = spans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpanPage spanPage = (SpanPage) o;
        return Objects.equals(this.pageNo, spanPage.pageNo) &&
                Objects.equals(this.pageSize, spanPage.pageSize) &&
                Objects.equals(this.pageCount, spanPage.pageCount) &&
                Objects.equals(this.totalCount, spanPage.totalCount) &&
                Objects.equals(this.spans, spanPage.spans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNo, pageSize, pageCount, totalCount, spans);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SpanPage {\n");
        sb.append("    pageNo: ").append(toIndentedString(pageNo)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
        sb.append("    spans: ").append(toIndentedString(spans)).append("\n");
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
