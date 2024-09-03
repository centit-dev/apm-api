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
 * SpanGroup
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SpanGroup {

    private String snapshotId;

    @Valid
    private List<String> labels = new ArrayList<>();

    private Integer innormalCount;

    private Integer callCount;

    @Valid
    private List<String> innormalInstances = new ArrayList<>();

    @Valid
    private List<String> innormalFirstServices = new ArrayList<>();

    public SpanGroup() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public SpanGroup(String snapshotId, List<String> labels, Integer innormalCount, Integer callCount, List<String> innormalInstances, List<String> innormalFirstServices) {
        this.snapshotId = snapshotId;
        this.labels = labels;
        this.innormalCount = innormalCount;
        this.callCount = callCount;
        this.innormalInstances = innormalInstances;
        this.innormalFirstServices = innormalFirstServices;
    }

    public SpanGroup snapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
        return this;
    }

    /**
     * span分组后每组的快照ID
     *
     * @return snapshotId
     */
    @NotNull
    @Schema(name = "snapshotId", description = "span分组后每组的快照ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("snapshotId")
    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    public SpanGroup labels(List<String> labels) {
        this.labels = labels;
        return this;
    }

    public SpanGroup addLabelsItem(String labelsItem) {
        if (this.labels == null) {
            this.labels = new ArrayList<>();
        }
        this.labels.add(labelsItem);
        return this;
    }

    /**
     * 根据分组属性分组后，每组的分组取值标签列表
     *
     * @return labels
     */
    @NotNull
    @Schema(name = "labels", description = "根据分组属性分组后，每组的分组取值标签列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("labels")
    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public SpanGroup innormalCount(Integer innormalCount) {
        this.innormalCount = innormalCount;
        return this;
    }

    /**
     * 该组span中，呈现为异常（报错或超时）的span数量。
     *
     * @return innormalCount
     */
    @NotNull
    @Schema(name = "innormalCount", description = "该组span中，呈现为异常（报错或超时）的span数量。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("innormalCount")
    public Integer getInnormalCount() {
        return innormalCount;
    }

    public void setInnormalCount(Integer innormalCount) {
        this.innormalCount = innormalCount;
    }

    public SpanGroup callCount(Integer callCount) {
        this.callCount = callCount;
        return this;
    }

    /**
     * 该组span中，所有span的数量。
     *
     * @return callCount
     */
    @NotNull
    @Schema(name = "callCount", description = "该组span中，所有span的数量。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("callCount")
    public Integer getCallCount() {
        return callCount;
    }

    public void setCallCount(Integer callCount) {
        this.callCount = callCount;
    }

    public SpanGroup innormalInstances(List<String> innormalInstances) {
        this.innormalInstances = innormalInstances;
        return this;
    }

    public SpanGroup addInnormalInstancesItem(String innormalInstancesItem) {
        if (this.innormalInstances == null) {
            this.innormalInstances = new ArrayList<>();
        }
        this.innormalInstances.add(innormalInstancesItem);
        return this;
    }

    /**
     * 该组span中，运行所处的应用实例列表。
     *
     * @return innormalInstances
     */
    @NotNull
    @Schema(name = "innormalInstances", description = "该组span中，运行所处的应用实例列表。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("innormalInstances")
    public List<String> getInnormalInstances() {
        return innormalInstances;
    }

    public void setInnormalInstances(List<String> innormalInstances) {
        this.innormalInstances = innormalInstances;
    }

    public SpanGroup innormalFirstServices(List<String> innormalFirstServices) {
        this.innormalFirstServices = innormalFirstServices;
        return this;
    }

    public SpanGroup addInnormalFirstServicesItem(String innormalFirstServicesItem) {
        if (this.innormalFirstServices == null) {
            this.innormalFirstServices = new ArrayList<>();
        }
        this.innormalFirstServices.add(innormalFirstServicesItem);
        return this;
    }

    /**
     * 该组span中，所影响到的首服务异常列表。
     *
     * @return innormalFirstServices
     */
    @NotNull
    @Schema(name = "innormalFirstServices", description = "该组span中，所影响到的首服务异常列表。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("innormalFirstServices")
    public List<String> getInnormalFirstServices() {
        return innormalFirstServices;
    }

    public void setInnormalFirstServices(List<String> innormalFirstServices) {
        this.innormalFirstServices = innormalFirstServices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpanGroup spanGroup = (SpanGroup) o;
        return Objects.equals(this.snapshotId, spanGroup.snapshotId) &&
                Objects.equals(this.labels, spanGroup.labels) &&
                Objects.equals(this.innormalCount, spanGroup.innormalCount) &&
                Objects.equals(this.callCount, spanGroup.callCount) &&
                Objects.equals(this.innormalInstances, spanGroup.innormalInstances) &&
                Objects.equals(this.innormalFirstServices, spanGroup.innormalFirstServices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(snapshotId, labels, innormalCount, callCount, innormalInstances, innormalFirstServices);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SpanGroup {\n");
        sb.append("    snapshotId: ").append(toIndentedString(snapshotId)).append("\n");
        sb.append("    labels: ").append(toIndentedString(labels)).append("\n");
        sb.append("    innormalCount: ").append(toIndentedString(innormalCount)).append("\n");
        sb.append("    callCount: ").append(toIndentedString(callCount)).append("\n");
        sb.append("    innormalInstances: ").append(toIndentedString(innormalInstances)).append("\n");
        sb.append("    innormalFirstServices: ").append(toIndentedString(innormalFirstServices)).append("\n");
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
