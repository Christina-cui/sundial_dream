package com.cuijing.sundial_dream.web.support;

import com.cuijing.sundial_dream.common.Current;
import com.cuijing.sundial_dream.web.Currents;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

public class CurrentMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Current.class);
    }

    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        Current ann = parameter.getParameterAnnotation(Current.class);
        Assert.notNull(ann, "No @Current");
        return new NamedValueInfo(
                parameter.getParameterType().getCanonicalName(),
                ann.required(),
                ValueConstants.DEFAULT_NONE);
    }

    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) {
        return Currents.resolve(parameter.nestedIfOptional().getParameterType()).orElse(null);
    }

    @Override
    protected void handleMissingValue(String name, MethodParameter parameter) {
        // AuthenticationCredentialsNotFoundException
        throw new RuntimeException(
                String.format(
                        "Required current %s not found for %s.%s",
                        parameter.getParameterType().getSimpleName(),
                        parameter.getMethod().getDeclaringClass().getSimpleName(),
                        parameter.getMethod().getName()));
    }
}
