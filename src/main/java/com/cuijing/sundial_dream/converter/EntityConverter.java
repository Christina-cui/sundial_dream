package com.cuijing.sundial_dream.converter;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.mapstruct.Mapper;
import org.mapstruct.TargetType;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Mapper(componentModel = "spring")
public class EntityConverter {
    private static final Logger log = LoggerFactory.getLogger(EntityConverter.class);
    @Autowired
    @Lazy
    private SqlSessionTemplate sqlSessionTemplate;

    @Transactional(readOnly = true)
    public <T extends Model<T>> T resolve(Long id, @TargetType Class<T> type) {
        if (id == null) {
            return null;
        }
        T entity =
                sqlSessionTemplate.selectOne(
                        SqlHelper.table(type).getSqlStatement(SqlMethod.SELECT_BY_ID.getMethod()), id);
        if (entity == null) {
            log.warn("{} with id {} not found", type.getSimpleName(), id);
        }
        return entity;
    }
}
