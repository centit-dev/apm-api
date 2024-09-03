package com.stardata.observ.handler;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.stardata.observ.model.ch.Instance.ServerSoftware;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class ServerSoftwareListTypHandler extends BaseTypeHandler<List<ServerSoftware>> {
    private final Gson gson = new Gson();
    private final Type type = new TypeToken<List<Double>>() {}.getType();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<ServerSoftware> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toString(parameter));
    }

    @Override
    public List<ServerSoftware> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : fromString(value);
    }

    @Override
    public List<ServerSoftware> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : fromString(value);
    }

    @Override
    public List<ServerSoftware> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : fromString(value);
    }

    private String toString(List<ServerSoftware> parameter) {
        return gson.toJson(parameter);
    }

    private List<ServerSoftware> fromString(String value) {
        try {
            return gson.fromJson(value, type);
        } catch (JsonSyntaxException | JsonIOException ignored) {
            return Collections.emptyList();
        }
    }
}
