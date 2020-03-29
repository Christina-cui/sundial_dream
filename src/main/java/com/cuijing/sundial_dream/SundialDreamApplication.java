package com.cuijing.sundial_dream;



import com.cuijing.sundial_dream.common.CommonConfiguration;

import com.cuijing.sundial_dream.common.MybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ServletComponentScan

@SpringBootApplication(scanBasePackages = {"com.cuijing.sundial_dream"})
@EnableScheduling

@ComponentScan(basePackageClasses = {CommonConfiguration.class, SundialDreamApplication.class})
@Import({
        MybatisPlusConfig.class
})
@MapperScan("com.cuijing.sundial_dream.*.mapper")
public class SundialDreamApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SundialDreamApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SundialDreamApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ConversionService defaultConversionService() {
        return new DefaultConversionService();
    }

}
