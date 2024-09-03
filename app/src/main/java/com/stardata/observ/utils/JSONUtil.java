package com.stardata.observ.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stardata.observ.common.TrendEnumDeserializer;
import com.stardata.observ.common.TrendEnumSerializer;
import com.stardata.observ.vo.StatusCondition;

public class JSONUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        // Register modules
        SimpleModule module = new SimpleModule();
        module.addSerializer(StatusCondition.TrendEnum.class, new TrendEnumSerializer());
        module.addDeserializer(StatusCondition.TrendEnum.class, new TrendEnumDeserializer());
        mapper.registerModule(module);
        // Configure other necessary settings, if any
    }


    private JSONUtil() {
    }

    /**
     * 获取单例ObjectMapper对象
     *
     * @return 单例ObjectMapper对象
     */
    public static ObjectMapper jsonMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }

    /**
     * 解析json字符串
     *
     * @param jsonStr json对象字符串
     * @return JsonNode对象，用于后续操作
     * @throws JsonProcessingException 可能会出现json字符串格式错误
     */
    public static JsonNode readTree(String jsonStr) throws JsonProcessingException {
        return jsonMapper().readTree(jsonStr);
    }

    /**
     * 创建JsonNode对象
     *
     * @return 创建的JsonNode对象
     */
    public static ObjectNode createObjectNode() {
        return jsonMapper().createObjectNode();
    }

    /**
     * 将对象转为json字符串
     *
     * @param object 待转换对象
     * @return 转换后的Json字符串
     */
    public static String toJSONString(Object object) {
        if (object == null) return null;
        try {
            return jsonMapper().writeValueAsString(object);
        } catch (JsonProcessingException ignored) {
            return null;
        }
    }

    /**
     * 解析json串到java对象
     *
     * @param jsonStr   待解析的Json串
     * @param valueType 转换后的对象类型
     * @param <T>       泛型，用于支持各种java类
     * @return 转换后的java对象
     * @throws JsonProcessingException 可能会出现json字符串格式错误
     */
    public static <T> T parseObject(String jsonStr, Class<T> valueType) throws JsonProcessingException {
        return jsonMapper().readValue(jsonStr, valueType);
    }

    /**
     * 解析json串到java List
     *
     * @param jsonStr   待解析的Json串
     * @param valueType 转换后的对象类型
     * @param <T>       泛型，用于支持各种java类
     * @return 转换后的java List对象，如果json字符串格式错误，则返回空列表
     */
    public static <T> List<T> parseList(String jsonStr, Class<T> valueType) {
        JavaType javaType = jsonMapper().getTypeFactory()
                .constructCollectionType(List.class, valueType);
        try {
            return jsonMapper().readValue(jsonStr, javaType);
        } catch (Exception ignored) {
            return Collections.emptyList();
        }
    }

    /**
     * 解析json字符串到HashMap
     *
     * @param jsonStr   待解析的Json串
     * @param keyType   转换后的HashMap key对象类型
     * @param valueType 转换后的HashMap value对象类型
     * @param <K>       泛型，用于支持各种java类
     * @param <V>       泛型，用于支持各种java类
     * @return 转换后的java List对象，如果json字符串格式错误，则返回空HashMap
     */
    public static <K, V> Map<K, V> parseMap(String jsonStr, Class<K> keyType, Class<V> valueType) {
        JavaType javaType = jsonMapper().getTypeFactory()
                .constructMapType(HashMap.class, keyType, valueType);
        try {
            return jsonMapper().readValue(jsonStr, javaType);
        } catch (Exception ignored) {
            return new HashMap<>();
        }
    }


    /**
     * 获取指定JsonNode节点下的long数值
     *
     * @param jsonNode  指定的JsonNode节点
     * @param fieldName json变量名
     * @return Long数值，如果json字符串格式错误，则返回null
     */
    public static Long getLong(JsonNode jsonNode, String fieldName) {
        JsonNode node = jsonNode.get(fieldName);
        if (node == null) return null;
        String str = node.asText();
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    /**
     * 将多个json格式的对象字符串，合并为一个Json对象字符串
     *
     * @param jsonStrList 多个json对象字符串
     * @return 合并为一个Json对象的字符串
     */
    public static String concatJsonStrings(List<String> jsonStrList) throws JsonProcessingException {
        JavaType javaType = jsonMapper().getTypeFactory()
                .constructMapType(HashMap.class, String.class, String.class);
        Map<String, String> aggregatedContentMap = new HashMap<>();
        for (String jsonStr : jsonStrList) {
            Map<String, String> contentMap = jsonMapper().readValue(jsonStr, javaType);
            aggregatedContentMap.putAll(contentMap);
        }
        return jsonMapper().writeValueAsString(aggregatedContentMap);
    }

}
