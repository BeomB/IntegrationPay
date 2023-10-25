package com.beom.common.config;

import com.beom.common.filter.CommonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig
{

    @Bean
    public FilterRegistrationBean<CommonFilter> loggingFilter() {
        FilterRegistrationBean<CommonFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CommonFilter());
        registrationBean.addUrlPatterns("/"); // 모든 URL에 필터 적용
        return registrationBean;
    }

}
