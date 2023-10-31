package com.beom.common.config;

import com.beom.common.interceptor.CommonInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        CommonInterceptor commonInterceptor = new CommonInterceptor();
        registry.addInterceptor(commonInterceptor).addPathPatterns("/**");
    }

}
