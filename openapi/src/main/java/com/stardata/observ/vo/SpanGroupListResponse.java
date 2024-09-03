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
 * SpanGroupListResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SpanGroupListResponse {

    private Integer code;

    private String message;

    @Valid
    private List<@Valid SpanGroup> groups = new ArrayList<>();

    public SpanGroupListResponse() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public SpanGroupListResponse(Integer code, String message, List<@Valid SpanGroup> groups) {
        this.code = code;
        this.message = message;
        this.groups = groups;
    }

    public SpanGroupListResponse code(Integer code) {
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

    public SpanGroupListResponse message(String message) {
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

    public SpanGroupListResponse groups(List<@Valid SpanGroup> groups) {
        this.groups = groups;
        return this;
    }

    public SpanGroupListResponse addGroupsItem(SpanGroup groupsItem) {
        if (this.groups == null) {
            this.groups = new ArrayList<>();
        }
        this.groups.add(groupsItem);
        return this;
    }

    /**
     * 分组
     *
     * @return groups
     */
    @NotNull
    @Valid
    @Schema(name = "groups", description = "分组", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("groups")
    public List<@Valid SpanGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<@Valid SpanGroup> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpanGroupListResponse spanGroupListResponse = (SpanGroupListResponse) o;
        return Objects.equals(this.code, spanGroupListResponse.code) &&
                Objects.equals(this.message, spanGroupListResponse.message) &&
                Objects.equals(this.groups, spanGroupListResponse.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, groups);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SpanGroupListResponse {\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    groups: ").append(toIndentedString(groups)).append("\n");
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
