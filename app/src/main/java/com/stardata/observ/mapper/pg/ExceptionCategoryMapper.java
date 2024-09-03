package com.stardata.observ.mapper.pg;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stardata.observ.model.pg.ExceptionCategory;

import org.springframework.stereotype.Repository;

/**
 * @author Samson Shu
 * @version 1.0
 * @email shush@stardata.top
 * @date 2023/12/26 14:48
 */
@Repository
@DS("config")
public interface ExceptionCategoryMapper extends BaseMapper<ExceptionCategory> {
}
