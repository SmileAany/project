package com.smile.project.infra.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author: smile
 * @title:
 * @projectName:
 * @description: TODO
 * @date: 2023/8/7 3:46 下午
 */
@Component
public class AuthoriseMetaObjectHandlerConfiguration implements MetaObjectHandler {
    /**
     * 新增数据时插入
     **/
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class,LocalDateTime.now());
    }

    /**
     * 更新数据时插入
     **/
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class,LocalDateTime.now());
    }
}