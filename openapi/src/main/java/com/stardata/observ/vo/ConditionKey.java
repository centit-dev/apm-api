package com.stardata.observ.vo;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ConditionKey
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ConditionKey {

    private Long id;

    private String name;

    private String displayName;
    private DataTypeEnum dataType;

    public ConditionKey() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public ConditionKey(Long id, String name, String displayName, DataTypeEnum dataType) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.dataType = dataType;
    }

    public ConditionKey id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * 该条件key的主键，用于后续查询取值范围时作为url path
     *
     * @return id
     */
    @NotNull
    @Schema(name = "id", description = "该条件key的主键，用于后续查询取值范围时作为url path", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConditionKey name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 用于条件查询匹配
     *
     * @return name
     */
    @NotNull
    @Schema(name = "name", description = "用于条件查询匹配", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConditionKey displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * 用于界面显示
     *
     * @return displayName
     */
    @NotNull
    @Schema(name = "displayName", description = "用于界面显示", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public ConditionKey dataType(DataTypeEnum dataType) {
        this.dataType = dataType;
        return this;
    }

    /**
     * 条件数据类型
     *
     * @return dataType
     */
    @NotNull
    @Schema(name = "dataType", description = "条件数据类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("dataType")
    public DataTypeEnum getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeEnum dataType) {
        this.dataType = dataType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConditionKey conditionKey = (ConditionKey) o;
        return Objects.equals(this.id, conditionKey.id) &&
                Objects.equals(this.name, conditionKey.name) &&
                Objects.equals(this.displayName, conditionKey.displayName) &&
                Objects.equals(this.dataType, conditionKey.dataType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, displayName, dataType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ConditionKey {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
        sb.append("    dataType: ").append(toIndentedString(dataType)).append("\n");
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

    /**
     * 条件数据类型
     */
    public enum DataTypeEnum {
        NUMBER("N"),

        STRING("S");

        private String value;

        DataTypeEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static DataTypeEnum fromValue(String value) {
            for (DataTypeEnum b : DataTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}
