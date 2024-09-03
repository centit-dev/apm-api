package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * TomcatStatus
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TomcatStatus {

    private Long threads;

    private Long activeSessions;

    private Long requestCount;

    public TomcatStatus() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public TomcatStatus(Long threads, Long activeSessions, Long requestCount) {
        this.threads = threads;
        this.activeSessions = activeSessions;
        this.requestCount = requestCount;
    }

    public TomcatStatus threads(Long threads) {
        this.threads = threads;
        return this;
    }

    /**
     * Get threads
     *
     * @return threads
     */
    @NotNull
    @Schema(name = "threads", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("threads")
    public Long getThreads() {
        return threads;
    }

    public void setThreads(Long threads) {
        this.threads = threads;
    }

    public TomcatStatus activeSessions(Long activeSessions) {
        this.activeSessions = activeSessions;
        return this;
    }

    /**
     * Get activeSessions
     *
     * @return activeSessions
     */
    @NotNull
    @Schema(name = "activeSessions", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("activeSessions")
    public Long getActiveSessions() {
        return activeSessions;
    }

    public void setActiveSessions(Long activeSessions) {
        this.activeSessions = activeSessions;
    }

    public TomcatStatus requestCount(Long requestCount) {
        this.requestCount = requestCount;
        return this;
    }

    /**
     * Get requestCount
     *
     * @return requestCount
     */
    @NotNull
    @Schema(name = "requestCount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("requestCount")
    public Long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Long requestCount) {
        this.requestCount = requestCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TomcatStatus tomcatStatus = (TomcatStatus) o;
        return Objects.equals(this.threads, tomcatStatus.threads) &&
                Objects.equals(this.activeSessions, tomcatStatus.activeSessions) &&
                Objects.equals(this.requestCount, tomcatStatus.requestCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(threads, activeSessions, requestCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TomcatStatus {\n");
        sb.append("    threads: ").append(toIndentedString(threads)).append("\n");
        sb.append("    activeSessions: ").append(toIndentedString(activeSessions)).append("\n");
        sb.append("    requestCount: ").append(toIndentedString(requestCount)).append("\n");
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
