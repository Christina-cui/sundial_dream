package com.cuijing.sundial_dream.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.CaseFormat;

import java.util.List;

public interface QueryWrappers {
    static String likeVal(String s) {
        return "%" + s + "%";
    }

    static String lowerUnderscore(String s) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s);
    }

    static <T> QueryWrapper<T> wrapper() {
        return new QueryWrapper();
    }

    static <T> QueryWrapper<T> wrapper(T t) {
        return new QueryWrapper(t);
    }

    static <T> LambdaQueryWrapper<T> lambda() {
        return (LambdaQueryWrapper<T>) wrapper().lambda();
    }

    static <T> LambdaQueryWrapper<T> lambda(T t) {
        return wrapper(t).lambda();
    }

    static <T> QueryWrapper<T> eq(String column, Object val) {
        return (QueryWrapper)wrapper().eq(column, val);
    }

    static <T> QueryWrapper<T> in(String column, List<?> val) {
        return (QueryWrapper)wrapper().in(column, val);
    }
}
