//package com.beom.aspect;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.MDC;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Aspect
//@Component
//public class TraceIdAspect {
//    @Around("controllerMethods()")
//    public Object addTraceId(ProceedingJoinPoint joinPoint) throws Throwable {
//        String traceId = generateTraceId(); // TraceId를 생성하거나 가져오는 로직 추가
//
//        try {
//
//
//            // 실제 메서드 호출
//            Object result = joinPoint.proceed();
//
//            // 응답값에 traceId 추가
//            if (result instanceof ResponseEntity<?>) {
//                ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
//                HttpHeaders headers = new HttpHeaders();
//                headers.addAll(responseEntity.getHeaders());
//                headers.add("traceId", traceId);
//                return new ResponseEntity<>(responseEntity.getBody(), headers, responseEntity.getStatusCode());
//            }
//
//            return result;
//        } finally {
//
//        }
//    }
//
//    private String generateTraceId() {
//        // TraceId 생성 로직 추가
//        // 예: UUID 사용
//        return UUID.randomUUID().toString();
//    }
//
//}
