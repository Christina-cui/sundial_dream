package com.cuijing.sundial_dream.web.springfox;


import com.google.common.base.Optional;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

/**
 * 添加缺少的 {@link org.springframework.web.bind.annotation.PathVariable} 参数,
 * 因为可以不再参数中使用路径参数,此时无法生成该路径参数
 */
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
@Component
class MissingPathVariableParameterBuilder implements OperationBuilderPlugin {

    private static final Pattern REG_PATH_VAR = Pattern.compile("\\{([-_a-zA-Z]+)}");

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    @Override
    public void apply(OperationContext operationContext) {
        List<Parameter> parameters =
                org.joor.Reflect.on(operationContext.operationBuilder()).get("parameters");
        String pattern = operationContext.requestMappingPattern();
        Matcher matcher = REG_PATH_VAR.matcher(pattern);
        while (matcher.find()) {
            String var = matcher.group(1);
            boolean found = false;
            for (Parameter parameter : parameters) {
                if (parameter.getName().equals(var)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                Parameter parameter =
                        new Parameter(
                                var,
                                var,
                                null,
                                true,
                                false,
                                ApiBuilder.buildModelRef(String.class),
                                Optional.absent(),
                                null,
                                "path",
                                "",
                                false,
                                null,
                                null,
                                Collections.emptyList());
                parameters.add(parameter);
            }
        }
    }
}

