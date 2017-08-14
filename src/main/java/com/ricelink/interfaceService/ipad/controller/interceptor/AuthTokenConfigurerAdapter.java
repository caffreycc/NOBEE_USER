package com.ricelink.interfaceService.ipad.controller.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置拦截器
 * Created by fangp on 2017/7/12.
 */
@Configuration
public class AuthTokenConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // registry.addInterceptor(new AuthTokenInterceptor()).addPathPatterns("/sec/**/*");
    }
}
