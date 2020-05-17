package com.cuijing.sundial_dream.web.springfox;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RUNTIME)
@Inherited
public @interface ApiQueryParam {}
