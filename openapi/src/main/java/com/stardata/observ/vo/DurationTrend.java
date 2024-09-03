package com.stardata.observ.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DurationTrend
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class DurationTrend {

    private Long fromTime;

    private Long toTime;

    private Integer granularity;

    @Valid
    private List<@Valid Percentile> percentile = new ArrayList<>();

    @Valid
    private List<List<Long>> traceDurations = new ArrayList<>();

    @Valid
    private List<List<Long>> selfDurations = new ArrayList<>();

    @Valid
    private List<List<Long>> gaps = new ArrayList<>();

    public DurationTrend() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public DurationTrend(Long fromTime, Long toTime, Integer granularity, List<@Valid Percentile> percentile, List<List<Long>> traceDurations, List<List<Long>> selfDurations, List<List<Long>> gaps) {
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.granularity = granularity;
        this.percentile = percentile;
        this.traceDurations = traceDurations;
        this.selfDurations = selfDurations;
        this.gaps = gaps;
    }

    public DurationTrend fromTime(Long fromTime) {
        this.fromTime = fromTime;
        return this;
    }

    /**
     * 时延趋势的起始时间，UNIX时间戳
     *
     * @return fromTime
     */
    @NotNull
    @Schema(name = "fromTime", description = "时延趋势的起始时间，UNIX时间戳", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("fromTime")
    public Long getFromTime() {
        return fromTime;
    }

    public void setFromTime(Long fromTime) {
        this.fromTime = fromTime;
    }

    public DurationTrend toTime(Long toTime) {
        this.toTime = toTime;
        return this;
    }

    /**
     * 时延趋势的截止时间，UNIX时间戳
     *
     * @return toTime
     */
    @NotNull
    @Schema(name = "toTime", description = "时延趋势的截止时间，UNIX时间戳", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("toTime")
    public Long getToTime() {
        return toTime;
    }

    public void setToTime(Long toTime) {
        this.toTime = toTime;
    }

    public DurationTrend granularity(Integer granularity) {
        this.granularity = granularity;
        return this;
    }

    /**
     * 时延趋势时间颗粒度，单位是秒。如果请求时未指定，则这里给出服务端自动计算的颗粒度。
     * minimum: 5
     * maximum: 120
     *
     * @return granularity
     */
    @NotNull
    @Min(5)
    @Max(120)
    @Schema(name = "granularity", description = "时延趋势时间颗粒度，单位是秒。如果请求时未指定，则这里给出服务端自动计算的颗粒度。", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("granularity")
    public Integer getGranularity() {
        return granularity;
    }

    public void setGranularity(Integer granularity) {
        this.granularity = granularity;
    }

    public DurationTrend percentile(List<@Valid Percentile> percentile) {
        this.percentile = percentile;
        return this;
    }

    public DurationTrend addPercentileItem(Percentile percentileItem) {
        if (this.percentile == null) {
            this.percentile = new ArrayList<>();
        }
        this.percentile.add(percentileItem);
        return this;
    }

    /**
     * Get percentile
     *
     * @return percentile
     */
    @NotNull
    @Valid
    @Schema(name = "percentile", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("percentile")
    public List<@Valid Percentile> getPercentile() {
        return percentile;
    }

    public void setPercentile(List<@Valid Percentile> percentile) {
        this.percentile = percentile;
    }

    public DurationTrend traceDurations(List<List<Long>> traceDurations) {
        this.traceDurations = traceDurations;
        return this;
    }

    public DurationTrend addTraceDurationsItem(List<Long> traceDurationsItem) {
        if (this.traceDurations == null) {
            this.traceDurations = new ArrayList<>();
        }
        this.traceDurations.add(traceDurationsItem);
        return this;
    }

    /**
     * 二维数组
     *
     * @return traceDurations
     */
    @NotNull
    @Valid
    @Schema(name = "traceDurations", description = "二维数组", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("traceDurations")
    public List<List<Long>> getTraceDurations() {
        return traceDurations;
    }

    public void setTraceDurations(List<List<Long>> traceDurations) {
        this.traceDurations = traceDurations;
    }

    public DurationTrend selfDurations(List<List<Long>> selfDurations) {
        this.selfDurations = selfDurations;
        return this;
    }

    public DurationTrend addSelfDurationsItem(List<Long> selfDurationsItem) {
        if (this.selfDurations == null) {
            this.selfDurations = new ArrayList<>();
        }
        this.selfDurations.add(selfDurationsItem);
        return this;
    }

    /**
     * 二维数组
     *
     * @return selfDurations
     */
    @NotNull
    @Valid
    @Schema(name = "selfDurations", description = "二维数组", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("selfDurations")
    public List<List<Long>> getSelfDurations() {
        return selfDurations;
    }

    public void setSelfDurations(List<List<Long>> selfDurations) {
        this.selfDurations = selfDurations;
    }

    public DurationTrend gaps(List<List<Long>> gaps) {
        this.gaps = gaps;
        return this;
    }

    public DurationTrend addGapsItem(List<Long> gapsItem) {
        if (this.gaps == null) {
            this.gaps = new ArrayList<>();
        }
        this.gaps.add(gapsItem);
        return this;
    }

    /**
     * 二维数组
     *
     * @return gaps
     */
    @NotNull
    @Valid
    @Schema(name = "gaps", description = "二维数组", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("gaps")
    public List<List<Long>> getGaps() {
        return gaps;
    }

    public void setGaps(List<List<Long>> gaps) {
        this.gaps = gaps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DurationTrend durationTrend = (DurationTrend) o;
        return Objects.equals(this.fromTime, durationTrend.fromTime) &&
                Objects.equals(this.toTime, durationTrend.toTime) &&
                Objects.equals(this.granularity, durationTrend.granularity) &&
                Objects.equals(this.percentile, durationTrend.percentile) &&
                Objects.equals(this.traceDurations, durationTrend.traceDurations) &&
                Objects.equals(this.selfDurations, durationTrend.selfDurations) &&
                Objects.equals(this.gaps, durationTrend.gaps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromTime, toTime, granularity, percentile, traceDurations, selfDurations, gaps);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DurationTrend {\n");
        sb.append("    fromTime: ").append(toIndentedString(fromTime)).append("\n");
        sb.append("    toTime: ").append(toIndentedString(toTime)).append("\n");
        sb.append("    granularity: ").append(toIndentedString(granularity)).append("\n");
        sb.append("    percentile: ").append(toIndentedString(percentile)).append("\n");
        sb.append("    traceDurations: ").append(toIndentedString(traceDurations)).append("\n");
        sb.append("    selfDurations: ").append(toIndentedString(selfDurations)).append("\n");
        sb.append("    gaps: ").append(toIndentedString(gaps)).append("\n");
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
