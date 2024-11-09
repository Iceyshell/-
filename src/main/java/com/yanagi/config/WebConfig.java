package com.yanagi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yanagi
 * @description 跨域处理
 * @date 2024-04-19 13:23
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8848")
                .allowedMethods("GET","POST","DELETE","PUT","OPTION")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
