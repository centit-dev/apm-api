package com.stardata.observ.config;

import java.util.Date;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.stardata.observ.common.ValidStatusEnum;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author Samson Shu
 * @email shush@stardata.top
 * @date 2021/5/24 15:40
 */

@Component
public class PgEntityCallbackHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("isValid", ValidStatusEnum.VALID.value(), metaObject);
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
