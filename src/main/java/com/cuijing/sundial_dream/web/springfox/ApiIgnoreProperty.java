package com.cuijing.sundial_dream.web.springfox;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Deprecated
@Documented
@Target({ElementType.FIELD})
@Retention(RUNTIME)
public @interface ApiIgnoreProperty {}