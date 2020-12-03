package com.gdiot.ssm.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer{
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//	}
	
	public CorsConfiguration() {
		System.out.println("===============================CorsConfiguration=========================");
	}
	
	//关键代码
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/**").allowCredentials(false).allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE").allowedOrigins("*");
		
	}
	
}
