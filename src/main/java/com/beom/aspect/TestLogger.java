package com.beom.aspect;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class TestLogger {

    @Before("execution(* com.beom.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info("Referer : {} " , request.getHeader("Referer"));
        log.info("Referer : {} " , request.getHeader("Referer"));
    }

    @After("execution(* com.beom.controller.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Request processing completed.");
    }

}
