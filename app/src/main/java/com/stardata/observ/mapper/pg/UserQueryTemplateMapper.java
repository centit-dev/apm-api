package com.stardata.observ.mapper.pg;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stardata.observ.model.pg.UserQueryTemplate;

import org.springframework.stereotype.Repository;

@Repository
@DS("config")
public interface UserQueryTemplateMapper extends BaseMapper<UserQueryTemplate> {
}
