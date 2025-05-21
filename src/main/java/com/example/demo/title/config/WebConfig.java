package com.example.demo.title.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	String path = "file:/C:\\uploadfile\\";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/img/**").addResourceLocations(path);
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

}
