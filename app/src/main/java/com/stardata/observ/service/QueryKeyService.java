package com.stardata.observ.service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stardata.observ.vo.ConditionKey;
import com.stardata.observ.common.ApplicationValidationException;
import com.stardata.observ.mapper.pg.QueryKeyMapper;
import com.stardata.observ.mapper.pg.QueryValueMapper;
import com.stardata.observ.model.pg.QueryKey;
import com.stardata.observ.model.pg.QueryValue;
import com.stardata.observ.vo.ConditionKeyListResponse;
import com.stardata.observ.vo.StringListResponse;

import lombok.AllArgsConstructor;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-16 16:12
 */
@Service
@AllArgsConstructor
public class QueryKeyService {
    static final Pattern RESOURCE_ATTRIBUTE_BLOCK_PATTERN = Pattern.compile("^ResourceAttributes\\[[\"'](?<key>.+?)[\\\"']\\]$");
    private static final Pattern SPAN_ATTRIBUTE_BLOCK_PATTERN = Pattern.compile("^SpanAttributes\\[[\"'](?<key>.+?)[\\\"']\\]$");

    private final QueryKeyMapper queryKeyMapper;
    private final QueryValueMapper queryValueMapper;
    private final QueryDictionaryService dictionaryService;

    public ConditionKeyListResponse listConditionKeys(Integer type) {
        QueryWrapper<QueryKey> wrapper = new QueryWrapper<>();
        wrapper.ge("valid_date", Instant.now());
        switch (type) {
            case 1:
                wrapper.eq("spans_valid", true);
                break;
            case 2:
                wrapper.eq("logs_valid", true);
                break;
            case 3:
                wrapper.eq("metrics_valid", true);
                break;
            default:
                throw new ApplicationValidationException("type is invalid. it must be one of [1, 2, 3]");
        }
        List<QueryKey> keys = queryKeyMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(keys)) {
            return new ConditionKeyListResponse();
        }

        List<ConditionKey> data = keys.stream()
                .map(key -> new ConditionKey()
                        .id(key.getId())
                        .name(key.getName())
                        .displayName(dictionaryService.getDisplayNameByName(key.getName()))
                        .dataType(ConditionKey.DataTypeEnum.fromValue(key.getType())))
                .collect(Collectors.toList());
        return new ConditionKeyListResponse().data(data);
    }

    public StringListResponse listConditionValues(Long keyId) {
        QueryWrapper<QueryValue> wrapper = new QueryWrapper<>();
        wrapper.eq("key_id", keyId);
        wrapper.ge("valid_date", Instant.now());
        List<QueryValue> queryValues = queryValueMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(queryValues)) {
            return new StringListResponse();
        }
        List<String> data = queryValues.stream().map(QueryValue::getValue).collect(Collectors.toList());
        return new StringListResponse().data(data);
    }

    public Map<String, Boolean> listKeys() {
        QueryWrapper<QueryKey> wrapper = new QueryWrapper<>();
        wrapper.eq("spans_valid", true);
        wrapper.ge("valid_date", Instant.now());
        List<QueryKey> keys = queryKeyMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptyMap();
        }
        return keys.stream().map(QueryKey::getName)
                .map(name -> {
                    Matcher matcher = RESOURCE_ATTRIBUTE_BLOCK_PATTERN.matcher(name);
                    if (matcher.find()) {
                        return Pair.of(matcher.group("key"), true);
                    }

                    matcher = SPAN_ATTRIBUTE_BLOCK_PATTERN.matcher(name);
                    if (matcher.find()) {
                        return Pair.of(matcher.group("key"), false);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (prev, next) -> prev || next));
    }
}
