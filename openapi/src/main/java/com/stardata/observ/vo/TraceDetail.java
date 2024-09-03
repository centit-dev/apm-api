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
 * TraceDetail
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TraceDetail {

    private TraceBrief brief;

    @Valid
    private List<@Valid TimelineSpan> spans = new ArrayList<>();

    public TraceDetail() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public TraceDetail(TraceBrief brief, List<@Valid TimelineSpan> spans) {
        this.brief = brief;
        this.spans = spans;
    }

    public TraceDetail brief(TraceBrief brief) {
        this.brief = brief;
        return this;
    }

    /**
     * Get brief
     *
     * @return brief
     */
    @NotNull
    @Valid
    @Schema(name = "brief", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("brief")
    public TraceBrief getBrief() {
        return brief;
    }

    public void setBrief(TraceBrief brief) {
        this.brief = brief;
    }

    public TraceDetail spans(List<@Valid TimelineSpan> spans) {
        this.spans = spans;
        return this;
    }

    public TraceDetail addSpansItem(TimelineSpan spansItem) {
        if (this.spans == null) {
            this.spans = new ArrayList<>();
        }
        this.spans.add(spansItem);
        return this;
    }

    /**
     * 该trace包含的所有span列表
     *
     * @return spans
     */
    @NotNull
    @Valid
    @Schema(name = "spans", description = "该trace包含的所有span列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("spans")
    public List<@Valid TimelineSpan> getSpans() {
        return spans;
    }

    public void setSpans(List<@Valid TimelineSpan> spans) {
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
        TraceDetail traceDetail = (TraceDetail) o;
        return Objects.equals(this.brief, traceDetail.brief) &&
                Objects.equals(this.spans, traceDetail.spans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brief, spans);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TraceDetail {\n");
        sb.append("    brief: ").append(toIndentedString(brief)).append("\n");
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
