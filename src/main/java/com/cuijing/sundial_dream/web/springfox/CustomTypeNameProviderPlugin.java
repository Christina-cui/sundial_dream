package com.cuijing.sundial_dream.web.springfox;

import com.cuijing.sundial_dream.utils.Lambdas;
import com.google.common.base.Strings;
import io.swagger.annotations.ApiModel;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.TypeNameProviderPlugin;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.Optional;
import java.util.function.Function;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class CustomTypeNameProviderPlugin implements TypeNameProviderPlugin {

    private static String topSimpleName(Class<?> type, Function<String, String> proc) {
        StringBuilder builder = new StringBuilder();
        Class<?> t = type;
        builder.append(proc.apply(t.getSimpleName()));
        while (t.getEnclosingClass() != null) {
            t = t.getEnclosingClass();
            // reverse
            builder.insert(0, proc.apply(t.getSimpleName()));
        }
        return builder.toString();
    }

    @Override
    public String nameFor(Class<?> type) {
        return Lambdas.first(
                () ->
                        Optional.ofNullable(findAnnotation(type, ApiModel.class))
                                .flatMap(v -> Optional.ofNullable(Strings.emptyToNull(v.value()))),
                () -> Optional.of(topSimpleName(type, this::trim)))
                .orElseThrow(AssertionError::new);
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }

    private String trim(String v) {
        // 可以考虑作为可配置项
        return v.replaceAll("DTO$", "");
    }
}
