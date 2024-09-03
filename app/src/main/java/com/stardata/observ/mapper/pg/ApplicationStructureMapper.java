package com.stardata.observ.mapper.pg;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stardata.observ.vo.NameDisplayPair;
import com.stardata.observ.model.pg.ApplicationStructure;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Kyle Liu
 * @version 1.0
 * @email kyle.liu.code@gmail.com
 * @date 2024-01-18 18:01
 */
@Repository
@DS("config")
public interface ApplicationStructureMapper extends BaseMapper<ApplicationStructure> {
    List<NameDisplayPair> listPlatforms();

    List<NameDisplayPair> listAppClusters(String platform);

    void largeInsert(@Param("items") List<ApplicationStructure> items);
}
