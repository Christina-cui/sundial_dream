package com.cuijing.sundial_dream.utils;

import org.springframework.aop.support.AopUtils;
import org.springframework.format.datetime.standard.DateTimeContext;
import org.springframework.format.datetime.standard.DateTimeContextHolder;

@SuppressWarnings("unused")
public interface Spring {
  /*
  查找 spring 中的 ContextHolder
  org.springframework.* ContextHolder
   */

    /** 获取一个对象的真实类, 因为可能该对象为代理后的对象, {@code getClass} 返回的是生成的类 */
    static Class<?> getClass(Object o) {
        //// Detect hibernate proxy
        //if (o instanceof HibernateProxy) {
        //  LazyInitializer lazyInitializer = ((HibernateProxy) o).getHibernateLazyInitializer();
        //  return lazyInitializer.getPersistentClass();
        //}
        return AopUtils.getTargetClass(o);
    }

    static DateTimeContext getDateTimeContext() {
        return DateTimeContextHolder.getDateTimeContext();
    }
}

