package com.cuijing.sundial_dream.common;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Set;

@Component("customEntityConverter")
public class CustomEntityConverter implements ConditionalGenericConverter {
    private static final Logger log = LoggerFactory.getLogger(CustomEntityConverter.class);
    private final TypeDescriptor modelType = TypeDescriptor.valueOf(Model.class);
    private final TypeDescriptor longType = TypeDescriptor.valueOf(Long.class);
    @Autowired
    @Qualifier("defaultConversionService")
    private ConversionService conversionService;
    @Autowired
    @Lazy
    private SqlSessionTemplate sqlSessionTemplate;

    public CustomEntityConverter() {
    }

    @PostConstruct
    private void init() {
        log.debug("Init CustomEntityConverter with {} defines");
    }

    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return targetType.isAssignableTo(this.modelType) && this.conversionService.canConvert(sourceType, this.longType);
    }

    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Object.class, Object.class));
    }

    @Transactional(
            readOnly = true
    )
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        } else {
            Object id = this.conversionService.convert(source, sourceType, this.longType);
            Object entity = this.sqlSessionTemplate.selectOne(SqlHelper.table(targetType.getType()).getSqlStatement(SqlMethod.SELECT_BY_ID.getMethod()), id);
            if (entity == null) {
                log.info("Did not find an entity id {} of type {}", id, targetType.getType());
                return null;
            } else {
                return entity;
            }
        }
    }
}
