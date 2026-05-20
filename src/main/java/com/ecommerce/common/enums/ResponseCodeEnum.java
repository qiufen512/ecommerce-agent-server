package com.ecommerce.common.enums;

import lombok.Getter;

/**
 * Response Code Enum
 */
@Getter
public enum ResponseCodeEnum {

    // Success
    SUCCESS("200", "Success"),

    // Client Error (400-499)
    BAD_REQUEST("400", "Bad Request"),
    UNAUTHORIZED("401", "Unauthorized"),
    FORBIDDEN("403", "Forbidden"),
    NOT_FOUND("404", "Not Found"),
    METHOD_NOT_ALLOWED("405", "Method Not Allowed"),

    // Server Error (500-599)
    SERVER_ERROR("500", "Internal Server Error"),
    SERVICE_UNAVAILABLE("503", "Service Unavailable"),

    // Business Error (1000-1999)
    BUSINESS_ERROR("1000", "Business Error"),
    VALIDATION_ERROR("1001", "Parameter Validation Failed"),
    DATA_NOT_FOUND("1002", "Data Not Found"),
    DATA_EXISTS("1003", "Data Already Exists"),
    OPERATION_FAILED("1004", "Operation Failed"),

    // Third-party Service Error (2000-2999)
    PYTHON_SERVICE_ERROR("2000", "Python Service Call Failed"),
    DATABASE_ERROR("2001", "Database Operation Failed"),
    EXTERNAL_API_ERROR("2002", "External API Call Failed");

    private final String code;
    private final String message;

    ResponseCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Get enum by code
     */
    public static ResponseCodeEnum getByCode(String code) {
        for (ResponseCodeEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return SERVER_ERROR;
    }
}