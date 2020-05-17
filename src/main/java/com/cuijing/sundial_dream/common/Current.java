package com.cuijing.sundial_dream.common;

import springfox.documentation.annotations.ApiIgnore;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiIgnore
public @interface Current {

    /**
     * 如果该参数为必须的,但没找到则抛出授权失败的异常
     *
     * @return 该注入是否必须
     */
    boolean required() default true;
}

