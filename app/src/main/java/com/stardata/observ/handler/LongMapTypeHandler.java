package com.stardata.observ.handler;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class LongMapTypeHandler extends BaseTypeHandler<Map<Long, Long>> {
    private final Gson gson = new Gson();
    private final Type type = new TypeToken<Map<Long, Long>>() {}.getType();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<Long, Long> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toString(parameter));
    }

    @Override
    public Map<Long, Long> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : fromString(value);
    }

    @Override
    public Map<Long, Long> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : fromString(value);
    }

    @Override
    public Map<Long, Long> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : fromString(value);
    }

    String toString(Map<Long, Long> parameter) {
        return gson.toJson(parameter);
    }

    Map<Long, Long> fromString(String value) {
        try {
            return gson.fromJson(value, type);
        } catch (JsonSyntaxException | JsonIOException ignored) {
            return Collections.emptyMap();
        }
    }
}
