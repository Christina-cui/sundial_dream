package com.cuijing.sundial_dream.web.support;

import com.cuijing.sundial_dream.common.error.Errors;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Shorts;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Persistable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Optional;

@Slf4j
public class PathVariableNotNullChecker implements RestControllerParameterChecker {

    private final short[] pos;

    PathVariableNotNullChecker(short[] pos) {
        this.pos = pos;
    }

    @Override
    public void accept(JoinPoint joinPoint) {
        for (short i : pos) {
            if (joinPoint.getArgs()[i] == null) {
                handlerNullArgs(i, joinPoint);
            }
        }
    }

    private void handlerNullArgs(short i, JoinPoint jp) {
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        Parameter parameter = method.getParameters()[i];
        if (parameter.isAnnotationPresent(PathVariable.class)) {
            log.warn(
                    "PathVariable({}) {} {} should not null in {}",
                    parameter.getAnnotation(PathVariable.class).value(),
                    parameter.getType().getSimpleName(),
                    parameter.getName(),
                    method);
        } else if (parameter.isAnnotationPresent(RequestParam.class)) {
            log.warn(
                    "RequestParam({}) {} {} should not null in {}",
                    parameter.getAnnotation(RequestParam.class).value(),
                    parameter.getType().getSimpleName(),
                    parameter.getName(),
                    method);
        } else {
            log.warn(
                    "Something {} {} should not null in {}",
                    parameter.getType().getSimpleName(),
                    parameter.getName(),
                    method);
        }

        throw Errors.notFound().asException();
    }

    @Order(Ordered.HIGHEST_PRECEDENCE) // 判断 null 需要最先处理
    public static class Factory implements RestControllerParameterChecker.Factory {

        public Optional<RestControllerParameterChecker> create(Method method) {
            // 生成需要检测的位置
            ImmutableList.Builder<Short> builder = ImmutableList.builder();
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                if (Persistable.class.isAssignableFrom(parameter.getType())) {
                    if (parameter.isAnnotationPresent(PathVariable.class)) {
                        builder.add((short) i);
                    } else if (
                        // 如果 RequestParam 为必选, 则也要检查, 否则可能会出现一些不必要的 NPE
                            Optional.ofNullable(parameter.getAnnotation(RequestParam.class))
                                    .map(RequestParam::required)
                                    .orElse(false)) {
                        builder.add((short) i);
                    }
                }
            }
            short[] array = Shorts.toArray(builder.build());
            if (array.length == 0) {
                return Optional.empty();
            }
            return Optional.of(new PathVariableNotNullChecker(array));
        }
    }
}

