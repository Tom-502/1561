package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class MymvcConfig implements WebMvcConfigurer  {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry .addViewController("/index.html").setViewName("index");
        registry .addViewController("/main.html").setViewName("teacher");
    }

   @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry .addInterceptor(new LoginHandlerInterceptor() )
                .addPathPatterns("/**")
                .excludePathPatterns("/","/homework/upload","/index.html","/admini/login","/admini/logon","/css/**","/img/**","/js/**");
    }
}
