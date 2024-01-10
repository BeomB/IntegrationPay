package com.beom.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<ErrorResponseEntity> handlerCustomException(ApiException e)
    {
        return ErrorResponseEntity.toResponseEntity(e.getErrorEnumerate());
    }
}
