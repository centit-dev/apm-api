package com.stardata.observ.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * AppLogPage
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AppLogPage {

    private Integer pageNo;

    private Integer pageSize;

    private Integer pageCount;

    private Integer totalCount;

    @Valid
    private List<@Valid AppLogBrief> logs;

    public AppLogPage pageNo(Integer pageNo) {
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

    public AppLogPage pageSize(Integer pageSize) {
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

    public AppLogPage pageCount(Integer pageCount) {
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

    public AppLogPage totalCount(Integer totalCount) {
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
    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public AppLogPage logs(List<@Valid AppLogBrief> logs) {
        this.logs = logs;
        return this;
    }

    public AppLogPage addLogsItem(AppLogBrief logsItem) {
        if (this.logs == null) {
            this.logs = new ArrayList<>();
        }
        this.logs.add(logsItem);
        return this;
    }

    /**
     * 查询结果列表
     *
     * @return logs
     */
    @Valid
    @Schema(name = "logs", description = "查询结果列表", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("logs")
    public List<@Valid AppLogBrief> getLogs() {
        return logs;
    }

    public void setLogs(List<@Valid AppLogBrief> logs) {
        this.logs = logs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppLogPage appLogPage = (AppLogPage) o;
        return Objects.equals(this.pageNo, appLogPage.pageNo) &&
                Objects.equals(this.pageSize, appLogPage.pageSize) &&
                Objects.equals(this.pageCount, appLogPage.pageCount) &&
                Objects.equals(this.totalCount, appLogPage.totalCount) &&
                Objects.equals(this.logs, appLogPage.logs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNo, pageSize, pageCount, totalCount, logs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AppLogPage {\n");
        sb.append("    pageNo: ").append(toIndentedString(pageNo)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
        sb.append("    logs: ").append(toIndentedString(logs)).append("\n");
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
