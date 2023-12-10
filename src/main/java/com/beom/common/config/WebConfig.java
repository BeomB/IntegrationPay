package com.beom.common.config;

import com.beom.common.filter.CommonFilter;
import com.beom.common.interceptor.CommonInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.beom.aspect")
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        CommonInterceptor commonInterceptor = new CommonInterceptor();
        registry.addInterceptor(commonInterceptor).addPathPatterns("/**");
    }



    @Bean
    public FilterRegistrationBean<CommonFilter> myFilter() {
        FilterRegistrationBean<CommonFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CommonFilter());
        registrationBean.addUrlPatterns("/*"); // 필터를 어떤 URL 패턴에 적용할지 설정
        return registrationBean;
    }

}
