package com.beom.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CommonFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            final HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("hello","hello");

            String requestTraceId = httpServletRequest.getHeader("traceId");
            String traceId ="";

            log.info("[{}] -----------TRACE - ID : {} --------------", ((HttpServletRequest) request).getRequestURI(),requestTraceId);

            if ("".equals(requestTraceId)||null == requestTraceId)
            {
                log.info(" null - traceId : {}",requestTraceId);
                traceId = UUID.randomUUID().toString();
            }else
            {
                log.info(" notnull - traceId : {}",requestTraceId);
                traceId = requestTraceId;
            }

            // 응답 헤더에 traceId를 추가합니다.

            // MDC에 Trace ID 추가
            MDC.put("traceId", traceId);
            log.info("put traceId");
            httpServletResponse.setHeader("traceId", traceId);

            // 다음 필터 또는 요청 처리로 전달
            chain.doFilter(httpServletRequest, httpServletResponse);
        } finally {
            // 요청 처리 완료 후에는 MDC에서 Trace ID 제거
            MDC.clear();
        }
    }
}

