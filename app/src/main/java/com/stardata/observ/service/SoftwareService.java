package com.stardata.observ.service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stardata.observ.vo.Software;
import com.stardata.observ.vo.SoftwareListResponse;
import com.stardata.observ.mapper.pg.SoftwareMapper;
import com.stardata.observ.model.pg.SoftwareDefine;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class SoftwareService {
    private final Map<String, SoftwareDefine> softwareCache;

    private final SoftwareMapper softwareMapper;
    private final QueryDictionaryService queryDictionaryService;

    public SoftwareService(SoftwareMapper softwareMapper, QueryDictionaryService queryDictionaryService) {
        this.softwareMapper = softwareMapper;
        this.queryDictionaryService = queryDictionaryService;
        this.softwareCache = new ConcurrentHashMap<>();

        // build and refresh cache every 5 minutes
        Flux.interval(Duration.ofSeconds(1), Duration.ofMinutes(5))
                .doOnNext(v -> init())
                .subscribeOn(Schedulers.single())
                .subscribe();
    }

    public SoftwareListResponse listSoftware() {
        List<Software> softwares = softwareCache.values().stream()
                .map(this::convertToSoftWareVo)
                .collect(Collectors.toList());
        return new SoftwareListResponse().data(softwares);
    }

    private Software convertToSoftWareVo(SoftwareDefine softwareDefine) {
        Software software = new Software();
        software.setName(softwareDefine.getName());
        software.setDisplayName(queryDictionaryService.getDisplayNameByName(softwareDefine.getName()));
        software.setLogo(softwareDefine.getLogo());
        return software;
    }

    @Nullable
    public String getSoftwareLogo(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        SoftwareDefine softwareDefine = softwareCache.get(name);
        if (softwareDefine != null) {
            return softwareDefine.getLogo();
        }
        return null;
    }

    private void init() {
        QueryWrapper<SoftwareDefine> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "type", "logo");
        List<SoftwareDefine> softwares = softwareMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(softwares)) {
            for (SoftwareDefine software : softwares) {
                softwareCache.put(software.getName(), software);
            }
        }
    }
}
