package com.beom.common.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

public class ExceptionEntity {

    private String errorCode;
    private String errorMessage;

    @Builder
    public ExceptionEntity(HttpStatus httpStatus, String errorCode, String errorMessage)
    {
        this.errorCode =errorCode;
        this.errorMessage = errorMessage;
    }

}
