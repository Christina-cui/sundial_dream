package com.cuijing.sundial_dream.common;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.cuijing.sundial_dream.converter.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

@Configuration
@ComponentScan(
        basePackageClasses = {CustomEntityConverter.class, EntityConverter.class}
)
public class MybatisPlusConfig implements WebMvcConfigurer {
    @Autowired
    private CustomEntityConverter customEntityConverter;

    public MybatisPlusConfig() {
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public LogicSqlInjector logicSqlInjector() {
        return new LogicSqlInjector();
    }

    @Bean
    @ConditionalOnMissingBean({CurrentUser.class})
    public CurrentUser defaultCurrentUser() {
        return Optional::empty;
    }

    @Bean
    public MetaObjectHandler metaObjectHandler(CurrentUser currentUser) {
        return new BaseMetaObjectHandler(currentUser);
    }

    public void addFormatters(FormatterRegistry registry) {
        if (registry instanceof FormattingConversionService) {
            FormattingConversionService conversionService = (FormattingConversionService)registry;
            conversionService.addConverter(this.customEntityConverter);
        }

    }
}
