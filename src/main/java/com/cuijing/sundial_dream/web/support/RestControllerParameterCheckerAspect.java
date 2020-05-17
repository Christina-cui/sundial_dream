package com.cuijing.sundial_dream.web.support;

import com.cuijing.sundial_dream.utils.Lambdas;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.OrderUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

@Aspect
@Slf4j
public class RestControllerParameterCheckerAspect {
    /** 定义使用的包, 使得不直接硬编码在其他地方, 能够快速修改 */
    private static final String VENDOR_PACKAGE = "com.yikaiye";
    /** 指定放 {@link org.springframework.web.bind.annotation.RestController} 的路径 */
    private static final String REST_PACKAGE_POINTCUT =
            "within(" + VENDOR_PACKAGE + ".*.rest..*) || within(" + VENDOR_PACKAGE + ".*.*.rest..*)";

    @SuppressWarnings("SpringAutowiredFieldsWarningInspection")
    @Autowired(required = false)
    private List<RestControllerParameterChecker.Factory> checkers;

    private final LoadingCache<Method, Consumer<JoinPoint>> cache =
            Caffeine.newBuilder().build(this::buildChecker);

    @PostConstruct
    private void init() {
        // 对 Checker 进行排序, 以便于后面创建的顺序也按照这样的顺序处理,在这里进行排序只需要排序一次
        sort(checkers);
        log.info("RestControllerParameterCheckerAspect init with {} checkers", checkers.size());
    }

    private void sort(List<RestControllerParameterChecker.Factory> checkers) {
        checkers.sort(Comparator.comparingInt(this::getOrder));
    }

    private int getOrder(RestControllerParameterChecker.Factory v) {
        return OrderUtils.getOrder(v.getClass(), Ordered.LOWEST_PRECEDENCE);
        //        只考虑工厂上的优先级定义
        //        try {
        //            Class<?> checkerType = v.getClass().getDeclaredMethod("create",
        // Method.class).getReturnType();
        //            return OrderUtils.getOrder(checkerType, 0);
        //        } catch (NoSuchMethodException e) {
        //            return 0;
        //        }
    }

    private Consumer<JoinPoint> buildChecker(Method method) {
        List<RestControllerParameterChecker> list = Lists.newArrayList();
        checkers.forEach(v -> v.create(method).ifPresent(list::add));
        if (list.isEmpty()) {
            return Lambdas.emptyConsumer();
        }
        Consumer<JoinPoint> c = null;
        for (Consumer<JoinPoint> consumer : list) {
            if (c == null) {
                c = consumer;
            } else {
                c = c.andThen(consumer);
            }
        }
        return c;
    }

    @Pointcut(REST_PACKAGE_POINTCUT) // TODO 这个不一定正确
    public void expectedPackage() {}

    @Pointcut("expectedPackage()  && @within(org.springframework.web.bind.annotation.RestController)")
    public void restControllers() {}

    @Pointcut(
            "@annotation(org.springframework.web.bind.annotation.RequestMapping) "
                    + "|| @annotation(org.springframework.web.bind.annotation.GetMapping)"
                    + "|| @annotation(org.springframework.web.bind.annotation.PostMapping)"
                    + "|| @annotation(org.springframework.web.bind.annotation.PathVariable)"
                    + "|| @annotation(org.springframework.web.bind.annotation.PutMapping)"
                    + "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void mappingAnnotations() {}

    /** 这个也是可以的,但是性能上不清楚是否更好,但是更灵活 */
    @Pointcut("execution(@(@org.springframework.web.bind.annotation.RequestMapping *) * *(..))")
    public void requestMappingAnnotations() {}

    // 条件为: 在匹配包下, 有 RestController 注解, 并且有任意一个 rest 方法注解的方法
    @Before("restControllers() && mappingAnnotations()")
    public void onExecute(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        Consumer<JoinPoint> consumer = cache.get(method);
        consumer.accept(jp);
    }
}
