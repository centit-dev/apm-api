package com.stardata.observ.mapper.pg;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stardata.observ.model.pg.SysParameter;

import org.springframework.stereotype.Repository;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2023-12-18 21:12
 */

@Repository
@DS("config")
public interface SysParameterMapper extends BaseMapper<SysParameter> {

}
