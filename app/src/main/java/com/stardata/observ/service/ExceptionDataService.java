package com.stardata.observ.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stardata.observ.mapper.pg.ExceptionCategoryMapper;
import com.stardata.observ.mapper.pg.ExceptionDefineMapper;
import com.stardata.observ.model.pg.ExceptionCategory;
import com.stardata.observ.model.pg.ExceptionDefine;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
public class ExceptionDataService {
    private final ExceptionCategoryMapper categoryMapper;
    private final ExceptionDefineMapper definitionMapper;

    private final ExceptionCategory otherCategory;
    private final ExceptionDefine otherDefinition;

    public ExceptionDataService(ExceptionCategoryMapper categoryMapper, ExceptionDefineMapper definitionMapper) {
        this.categoryMapper = categoryMapper;
        this.definitionMapper = definitionMapper;
        otherDefinition = new ExceptionDefine();
        otherDefinition.setId(0L);
        otherDefinition.setShortName("其他");

        otherCategory = new ExceptionCategory();
        otherCategory.setId(0L);
        otherCategory.setName("其他");
    }

    public ExceptionDefine getExceptionDefineById(Long id) {
        return definitionMapper.selectById(id);
    }

    public Map<String, ExceptionDefine> FindExceptionDefineByNames(Collection<String> names) {
        if (CollectionUtils.isEmpty(names)) {
            return Collections.emptyMap();
        }

        QueryWrapper<ExceptionDefine> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("short_name", names);
        List<ExceptionDefine> definitions = definitionMapper.selectList(queryWrapper);
        Map<String, ExceptionDefine> definitionMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(definitions)) {
            definitionMap = definitions.stream().collect(Collectors.toMap(ExceptionDefine::getShortName, Function.identity()));
        }
        return definitionMap;
    }

    public Map<Long, ExceptionCategory> getExceptionCategoriesByIds(Collection<Long> categoryIds) {
        if (CollectionUtils.isEmpty(categoryIds)) {
            return Collections.emptyMap();
        }

        List<ExceptionCategory> categories = categoryMapper.selectBatchIds(categoryIds);
        Map<Long, ExceptionCategory> categoryMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(categories)) {
            categoryMap = categories.stream().collect(Collectors.toMap(ExceptionCategory::getId, Function.identity()));
        }
        return categoryMap;
    }

    public ExceptionDefine getOtherDefinition() {
        return otherDefinition;
    }

    public ExceptionCategory getOtherCategory() {
        return otherCategory;
    }
}
