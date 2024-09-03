package com.stardata.observ.handler;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class KeyCountMapTypeHandler extends BaseTypeHandler<Map<String, Integer>> {

    private final Gson gson = new Gson();
    private final Type type = new TypeToken<JsonArray>() {}.getType();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, Integer> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toString(parameter));
    }

    @Override
    public Map<String, Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : fromString(value);
    }

    @Override
    public Map<String, Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : fromString(value);
    }

    @Override
    public Map<String, Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : fromString(value);
    }

    String toString(Map<String, Integer> parameter) {
        return gson.toJson(parameter);
    }

    Map<String, Integer> fromString(String value) {
        JsonArray object;
        try {
            object = gson.fromJson(value, type);
        } catch (JsonSyntaxException | JsonIOException ignored) {
            return Collections.emptyMap();
        }

        return StreamSupport.stream(object.spliterator(), false)
                .map(element -> element.getAsJsonObject())
                .filter(element -> element.has("item")
                        && element.has("count")
                        && element.get("count").isJsonPrimitive())
                .collect(Collectors.toMap(
                    element -> element.get("item").getAsString(),
                    element -> element.get("count").getAsInt()));
    }

}
