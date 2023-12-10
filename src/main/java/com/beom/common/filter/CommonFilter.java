package com.beom.common.filter;


import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

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
            // Trace ID 생성 (여기서는 UUID 사용)
            String traceId = UUID.randomUUID().toString();

            // MDC에 Trace ID 추가
            MDC.put("traceId", traceId);

            // 다음 필터 또는 요청 처리로 전달
            chain.doFilter(request, response);
        } finally {
            // 요청 처리 완료 후에는 MDC에서 Trace ID 제거
            MDC.clear();
        }
    }

}

