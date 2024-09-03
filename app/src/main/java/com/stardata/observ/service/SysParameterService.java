package com.stardata.observ.service;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.stardata.observ.vo.NameDisplayListResponse;
import com.stardata.observ.common.ApplicationValidationException;
import com.stardata.observ.common.SysParameterCodeEnum;
import com.stardata.observ.domain.CommonalityKeyBlacklist;
import com.stardata.observ.mapper.pg.SysParameterMapper;
import com.stardata.observ.model.pg.DataTypeOperators;
import com.stardata.observ.model.pg.SysParameter;
import com.stardata.observ.vo.NameDisplayPair;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static com.stardata.observ.common.SysParameterCodeEnum.COMMONALITY_KEY_BLACKLIST;
import static com.stardata.observ.common.SysParameterCodeEnum.DATA_TYPE_OPERATORS;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-18 21:12
 */
@Service
@Slf4j
@AllArgsConstructor
public class SysParameterService {
    private static final Pattern ATTRIBUTE_KEY_PATTERN = Pattern.compile("^(Resource|Span|Log)Attributes\\['(?<key>.+?)'\\]$");

    private final Type type = new TypeToken<List<String>>() {}.getType();

    private final SysParameterMapper sysParameterMapper;
    private final QueryDictionaryService dictionaryService;
    private final Gson gson;

    private final LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(@Nonnull String key) {
                    return getStringParameterAsString(key);
                }
            });

    public String getStringParameterAsString(String code) {
        QueryWrapper<SysParameter> sysParameterQueryWrapper = new QueryWrapper<>();
        sysParameterQueryWrapper.eq("code", code);
        SysParameter result = sysParameterMapper.selectOne(sysParameterQueryWrapper);
        return result == null ? StringUtils.EMPTY : result.getValue();
    }

    public String getStringParameter(@Nonnull String code) {
        return cache.getUnchecked(code);
    }

    public Long getLongParameter(SysParameterCodeEnum code, Long defaultValue) {
        String valueStr = cache.getUnchecked(code.value());
        if (StringUtils.isEmpty(valueStr)) return defaultValue;
        try {
            return Long.valueOf(valueStr);
        } catch (NumberFormatException ignore) {
            return defaultValue;
        }
    }

    public List<String> getStringListParameter(@Nonnull String code) {
        String stringParameter = getStringParameter(code);
        if (StringUtils.isEmpty(stringParameter)) {
            return Collections.emptyList();
        }
        try {
            return gson.fromJson(stringParameter, type);
        } catch (JsonSyntaxException | JsonIOException ignored) {
            return Collections.emptyList();
        }
    }

    public CommonalityKeyBlacklist getCommonalityKeyBlacklist() {
        List<String> blacklist = getStringListParameter(COMMONALITY_KEY_BLACKLIST.value());
        CommonalityKeyBlacklist dto = new CommonalityKeyBlacklist();
        for (String key : blacklist) {
            Matcher matcher = ATTRIBUTE_KEY_PATTERN.matcher(key);
            if (!matcher.find()) {
                continue;
            }
            if (key.contains("Span")) {
                dto.addSpanBlacklist(matcher.group("key"));
            } else if (key.contains("Resource")) {
                dto.addResourceBlacklist(matcher.group("key"));
            }
        }
        return dto;
    }

    public NameDisplayListResponse getDataTypeOperator(String type) {
        String stringParameter = getStringParameter(DATA_TYPE_OPERATORS.value());
        if (StringUtils.isEmpty(stringParameter)) {
            log.warn("Missing data type operator config: {}.", DATA_TYPE_OPERATORS.value());
            return new NameDisplayListResponse();
        }

        List<String> operators;
        try {
            DataTypeOperators dataTypeOperators = gson.fromJson(stringParameter, DataTypeOperators.class);
            operators = "N".equals(type) ? dataTypeOperators.getNumber() : dataTypeOperators.getString();
        } catch (JsonSyntaxException | JsonIOException e) {
            throw new ApplicationValidationException("dataTypeOperators parse error.");
        }
        List<NameDisplayPair> data = operators.stream().map(dictionaryService::getDisplayPair).collect(Collectors.toList());
        return new NameDisplayListResponse().data(data);
    }
}
