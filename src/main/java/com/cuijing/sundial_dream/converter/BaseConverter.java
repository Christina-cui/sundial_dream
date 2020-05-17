package com.cuijing.sundial_dream.converter;


import com.cuijing.sundial_dream.utils.Lambdas;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.TargetType;

import java.util.*;

/**
 * <ul>
 *   <li>实现时间的映射 使用 unix timestamp, 秒级
 *   <li>实现 IdNameDTO 的基本转换
 *   <li>实现枚举的基本转换
 *   <li>数字与布尔值互转
 *   <li>集合转换为数字
 * </ul>
 */
public interface BaseConverter {
    static Integer integerFromBoolean(Boolean s) {
        return Lambdas.apply(s, v -> v ? 1 : 0);
    }

    static Boolean booleanFromInteger(Integer s) {
        return Lambdas.apply(s, v -> v != 0);
    }

    static Long dateUnixTimestamp(Date s) {
        return Lambdas.apply(s, v -> v.getTime() / 1000);
    }

    static Date dateFromUnixTimestamp(Long s) {
        return Lambdas.apply(s, v -> new Date(s * 1000));
    }

    static <T extends Enum<T>> Integer enumOrdinal(T s) {
        return Lambdas.apply(s, Enum::ordinal);
    }

    static <T extends Enum<T>> String mapEnumToString(T s) {
        return Lambdas.apply(s, Enum::name);
    }

    static <T extends Enum<T>> T mapStringToEnum(String s, @TargetType Class<T> type) {
        return Lambdas.apply(s, v -> Enum.valueOf(type, v));
    }

    static <T extends Collection> Integer mapCollectionToSize(T s) {
        return Lambdas.apply(s, Collection::size);
    }

    static <T extends Map> Integer integerFromMap(T s) {
        return Lambdas.apply(s, Map::size);
    }

    static List<String> stringFromList(String s) {
        return Lambdas.apply(s, v -> Arrays.asList(v.split(",")));
    }

    static String listFromString(List<String> s) {
        return Lambdas.apply(s, v -> StringUtils.join(v, ","));
    }
}

