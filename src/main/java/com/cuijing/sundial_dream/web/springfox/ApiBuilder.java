package com.cuijing.sundial_dream.web.springfox;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.ApiModelProperty;
import org.joor.Reflect;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.service.Parameter;
import springfox.documentation.swagger.schema.ApiModelProperties;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

class ApiBuilder {

    static LoadingCache<Class<?>, CharSequence> enumDescCache =
            Caffeine.newBuilder()
                    .maximumSize(100)
                    .expireAfterWrite(1, TimeUnit.MINUTES)
                    .build(ApiBuilder::doBuildEnumDescription);
    private static ImmutableMap<Class, ModelRef> TYPE_TO_MODELREF;

    static {
        ImmutableMap.Builder<Class, ModelRef> builder = ImmutableMap.builder();

        builder
                .put(Integer.class, new ModelRef("int"))
                .put(int.class, new ModelRef("int"))
                .put(Long.class, new ModelRef("long"))
                .put(long.class, new ModelRef("long"))
                .put(Double.class, new ModelRef("double"))
                .put(Float.class, new ModelRef("float"))
                .put(double.class, new ModelRef("double"))
                .put(float.class, new ModelRef("float"));
        builder.put(String.class, new ModelRef("string"));
        ApiBuilder.TYPE_TO_MODELREF = builder.build();
    }

    static ModelReference buildModelRef(Class<?> type) {
        return Optional.ofNullable(TYPE_TO_MODELREF.get(type)).orElseGet(() -> new ModelRef("string"));
    }

    static String emptyNull(String v) {
        return v != null && v.length() != 0 ? v : null;
    }

    static Optional<Parameter> buildParameter(Field f, ParameterBuilder builder) {
        if (isIgnored(f)) {
            return Optional.empty();
        }

        Class<?> type = f.getType();
        builder
                .name(f.getName())
                .parameterType("query")
                .description(f.getName())
                .modelRef(buildModelRef(type));

        Optional.ofNullable(f.getAnnotation(ApiModelProperty.class))
                .ifPresent(
                        v -> {
                            builder
                                    .description(emptyNull(v.value()))
                                    .name(emptyNull(v.name()))
                                    .parameterType(emptyNull(v.dataType()))
                                    .allowableValues(ApiModelProperties.allowableValueFromString(v.allowableValues()))
                                    .parameterAccess(emptyNull(v.access()))
                                    .required(v.required());
                        });

        buildDescription(f)
                .ifPresent(
                        v -> builder.description(Reflect.on(builder).get("description") + " " + v.toString()));
        return Optional.of(builder.build());
    }

    static Optional<StringBuilder> buildDescription(Field f) {
        Class<?> type = f.getType();
        StringBuilder sb = new StringBuilder();

        // 为枚举类型的参数生成额外的说明
        ApiEnumConstant annotation = f.getAnnotation(ApiEnumConstant.class);
        if (annotation != null) {
            sb.append(buildEnumDescription(annotation.value()));
        } else if (type.isEnum()) {
            sb.append(buildEnumDescription(type));
        }
        return sb.length() > 0 ? Optional.of(sb) : Optional.empty();
    }

    private static CharSequence buildEnumDescription(Class<?> type) {
        return enumDescCache.get(type);
    }

    private static StringBuilder doBuildEnumDescription(Class<?> type) {
        StringBuilder sb = new StringBuilder();
        Optional.ofNullable(type.getAnnotation(Describe.class)).ifPresent(v -> sb.append(v.value()));
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("");
        //noinspection unchecked
        for (Enum o : ((Class<? extends Enum>) type).getEnumConstants()) {
            helper.add(o.toString(), o.ordinal()); // By default use ordinal
        }
        sb.append(helper.toString());
        return sb;
    }

    private static boolean isIgnored(Field f) {
        return f.isAnnotationPresent(ApiIgnoreProperty.class);
    }
}

