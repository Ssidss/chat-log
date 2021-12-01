package com.charles.chat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${spring.resources.static-locations}")
    private String resourceLocations;

    @Value("${spring.mvc.static-path-pattern}")
    private String resourcePattern;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub

        registry.addResourceHandler(resourcePattern).addResourceLocations(resourceLocations);
    }

}
