package com.stardata.observ.handler;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class LongListTypeHandler extends BaseTypeHandler<List<Long>> {
    private final Gson gson = new Gson();
    private final Type type = new TypeToken<List<Double>>() {}.getType();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Long> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toString(parameter));
    }

    @Override
    public List<Long> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : fromString(value);
    }

    @Override
    public List<Long> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : fromString(value);
    }

    @Override
    public List<Long> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : fromString(value);
    }

    private String toString(List<Long> parameter) {
        return gson.toJson(parameter);
    }

    private List<Long> fromString(String value) {
        try {
            List<Double> values = gson.fromJson(value, type);
            return values.stream().map(Double::longValue).collect(Collectors.toList());
        } catch (JsonSyntaxException | JsonIOException ignored) {
            return Collections.emptyList();
        }
    }
}
