package com.cuijing.sundial_dream.web.support;

import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Consumer;

public interface RestControllerParameterChecker extends Consumer<JoinPoint> {

    /**
     * 用于创建 checker 的工厂类, 支持使用 {@link org.springframework.core.annotation.Order} 或 {@link
     * org.springframework.core.Ordered} 来指定顺序
     */
    interface Factory {
        Optional<RestControllerParameterChecker> create(Method method);
    }
}

