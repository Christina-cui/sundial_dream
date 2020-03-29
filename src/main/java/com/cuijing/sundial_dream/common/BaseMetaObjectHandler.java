package com.cuijing.sundial_dream.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class BaseMetaObjectHandler implements MetaObjectHandler {
    private static final Logger log = LoggerFactory.getLogger(BaseMetaObjectHandler.class);
    private CurrentUser currentUser;

    public BaseMetaObjectHandler(CurrentUser currentUser) {
        this.currentUser = currentUser     ;
    }

    public void insertFill(MetaObject metaObject) {
        LocalDateTime dateTime = LocalDateTime.now();
        this.setFieldValByName("createTime", dateTime, metaObject);
        this.setFieldValByName("updateTime", dateTime, metaObject);
    }

    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}