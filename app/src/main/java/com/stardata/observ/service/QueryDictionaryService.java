package com.stardata.observ.service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stardata.observ.mapper.pg.QueryDictionaryMapper;
import com.stardata.observ.model.pg.QueryDictionary;
import com.stardata.observ.vo.NameDisplayPair;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-16 17:12
 */
@Service
public class QueryDictionaryService {
    static final Pattern REQUEST_BODY_PATTERN = Pattern.compile("SpanAttributes\\['http\\.request\\.body\\.(?<body>.*)']");
    static final Pattern RESPONSE_BODY_PATTERN = Pattern.compile("SpanAttributes\\['http\\.response\\.body\\.(?<body>.*)']");
    private static final String REQUEST_BODY_I18N_PREFIX = "入参";
    private static final String RESPONSE_BODY_I18N_PREFIX = "出参";

    private final QueryDictionaryMapper queryDictionaryMapper;
    private final Map<String, QueryDictionary> cache;

    public QueryDictionaryService(QueryDictionaryMapper queryDictionaryMapper) {
        this.queryDictionaryMapper = queryDictionaryMapper;
        cache = new ConcurrentHashMap<>();
        // build and refresh cache every 5 minutes
        Flux.interval(Duration.ofSeconds(1), Duration.ofMinutes(5))
                .doOnNext(v -> init())
                .subscribeOn(Schedulers.single())
                .subscribe();
    }

    public void init() {
        Wrapper<QueryDictionary> wrapper = new QueryWrapper<>();
        List<QueryDictionary> queryDictionaries = queryDictionaryMapper.selectList(wrapper);
        for (QueryDictionary queryDictionary : queryDictionaries) {
            if (StringUtils.isBlank(queryDictionary.getName())) {
                continue;
            }
            cache.put(queryDictionary.getName(), queryDictionary);
        }
    }

    public String getDisplayNameByName(String name) {
        if (StringUtils.isBlank(name)) {
            return name;
        }
        QueryDictionary entry = cache.get(name);
        if (entry != null) {
            return entry.getDisplayName();
        }
        Matcher matcher = REQUEST_BODY_PATTERN.matcher(name);
        if (matcher.find()) {
            return REQUEST_BODY_I18N_PREFIX + matcher.group("body");
        }

        matcher = RESPONSE_BODY_PATTERN.matcher(name);
        if (matcher.find()) {
            return RESPONSE_BODY_I18N_PREFIX + matcher.group("body");
        }
        return name;
    }

    @Nonnull
    public NameDisplayPair getDisplayPair(String name) {
        NameDisplayPair pair = new NameDisplayPair();
        if (StringUtils.isBlank(name)) {
            return pair;
        }
        QueryDictionary entry = cache.get(name);
        pair.setName(name);
        if (entry == null) {
            pair.setDisplayName(name);
            return pair;
        }
        pair.setDisplayName(entry.getDisplayName());
        return pair;
    }

    public List<NameDisplayPair> getDisplayPairs(List<String> names) {
        return names.stream().map(this::getDisplayPair).collect(Collectors.toList());
    }
}
