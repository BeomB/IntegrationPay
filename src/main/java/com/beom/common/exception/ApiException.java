package com.beom.common.exception;

import com.beom.common.enumerations.ErrorEnumerate;

public class ApiException extends RuntimeException{
    private ErrorEnumerate errorEnumerate;

    public ApiException(ErrorEnumerate e)
    {
        super(e.getErrorMessage());
        this.errorEnumerate = e;
    }


}
