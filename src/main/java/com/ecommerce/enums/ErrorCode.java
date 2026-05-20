package com.ecommerce.enums;

import lombok.Getter;

/**
 * Business error code enum
 */
@Getter
public enum ErrorCode {

    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    CONFLICT(409, "Resource Already Exists"),
    INTERNAL_ERROR(500, "Internal Server Error"),
    PYTHON_SERVICE_UNAVAILABLE(503, "Python Service Unavailable"),
    PYTHON_SERVICE_TIMEOUT(504, "Python Service Timeout");

    private final Integer code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}