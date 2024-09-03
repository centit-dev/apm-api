package com.stardata.observ.handler;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

@Slf4j
public class StringMapListTypeHandler extends BaseTypeHandler<List<Map<String, String>>> {
    private final Gson gson = new Gson();
    private final Type type = new TypeToken<List<Map<String, String>>>() {}.getType();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Map<String, String>> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toString(parameter));
    }

    @Override
    public List<Map<String, String>> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : fromString(value);
    }

    @Override
    public List<Map<String, String>> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : fromString(value);
    }

    @Override
    public List<Map<String, String>> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : fromString(value);
    }

    String toString(List<Map<String, String>> parameter) {
        return gson.toJson(parameter);
    }

    List<Map<String, String>> fromString(String value) {
        try {
            return gson.fromJson(value, type);
        } catch (JsonSyntaxException | JsonIOException ignored) {
            log.error("Failed to parse JSON string: {}", value);
            return Collections.emptyList();
        }
    }
}
