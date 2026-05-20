package com.ecommerce.exception;

import com.ecommerce.enums.ErrorCode;
import lombok.Getter;

/**
 * Python Service Unavailable Exception
 */
@Getter
public class PythonServiceException extends RuntimeException {

    private final Integer code;

    public PythonServiceException(String message) {
        super(message);
        this.code = ErrorCode.PYTHON_SERVICE_UNAVAILABLE.getCode();
    }

    public PythonServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}