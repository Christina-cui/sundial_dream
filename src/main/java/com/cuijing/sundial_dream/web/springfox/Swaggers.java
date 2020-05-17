package com.cuijing.sundial_dream.web.springfox;

import com.google.common.collect.ImmutableList;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.service.contexts.ParameterContext;

import java.util.List;

public interface Swaggers {

    static void ignore(ParameterContext parameterContext) {
        //            parameterContext.parameterBuilder().name("");
        parameterContext.parameterBuilder().hidden(true);
    }

    static List<Parameter> pageableParameters() {
        return Holder.pageableParams;
    }

    final class Holder {

        static final List<Parameter> pageableParams;

        static {
            ParameterBuilder builder = new ParameterBuilder();
            pageableParams =
                    ImmutableList.of(
                            builder
                                    .parameterType("query")
                                    .name("page")
                                    .modelRef(new ModelRef("int"))
                                    .description("获取获取的页 (0..N).")
                                    .build(),
                            builder
                                    .parameterType("query")
                                    .name("size")
                                    .modelRef(new ModelRef("int"))
                                    .description("每页显示的数量.")
                                    .build(),
                            builder
                                    .parameterType("query")
                                    .name("sort")
                                    .modelRef(new ModelRef("string"))
                                    .allowMultiple(true)
                                    .description("排序参数,格式为: 属性名[,asc|desc].默认排序为递增.可传递多个排序参数.")
                                    .build());
        }
    }
}


