package com.beom.common.exception;

import com.beom.common.enumerations.ErrorEnumerate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiException extends RuntimeException {
    private ErrorEnumerate errorEnumerate;

}
