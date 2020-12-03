package com.gdiot.ssm.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SessionConfiguration implements WebMvcConfigurer{
	
	@Autowired
    private UserAuthInterceptor userAuthInterceptor;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userAuthInterceptor).addPathPatterns("/**");
//        .excludePathPatterns("/index","/","/user/login","/asserts/**","/webjars/**");
    }
	
}
