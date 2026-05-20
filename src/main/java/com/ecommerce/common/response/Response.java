package com.ecommerce.common.response;

import com.ecommerce.common.enums.ResponseCodeEnum;
import com.ecommerce.enums.ErrorCode;
import com.ecommerce.exception.BusinessException;
import lombok.Data;

import java.io.Serializable;

/**
 * Unified Response Format
 *
 * @param <T> Response Data Type
 */
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Whether successful, defaults to true */
    private boolean success = true;

    /** Response Message */
    private String message;

    /** Error Code */
    private String errorCode;

    /** Response Data */
    private T data;

    /**
     * Success Response (No Data)
     */
    public static Response<?> success() {
        return new Response<>();
    }

    /**
     * Success Response (With Data)
     */
    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setData(data);
        return response;
    }

    /**
     * Failure Response (No Message)
     */
    public static Response<?> fail() {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        return response;
    }

    /**
     * Failure Response (With Message)
     */
    public static Response<?> fail(String message) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }

    /**
     * Failure Response (With Error Code and Message)
     */
    public static Response<?> fail(String errorCode, String message) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(errorCode);
        response.setMessage(message);
        return response;
    }

    /**
     * Create Failure Response from BusinessException (Integer code to String)
     */
    public static Response<?> fail(BusinessException e) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(String.valueOf(e.getCode()));
        response.setMessage(e.getMessage());
        return response;
    }

    /**
     * Create Failure Response from ErrorCode Enum
     */
    public static Response<?> fail(ErrorCode errorCode) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(String.valueOf(errorCode.getCode()));
        response.setMessage(errorCode.getMessage());
        return response;
    }

    /**
     * Create Failure Response from ResponseCodeEnum
     */
    public static Response<?> fail(ResponseCodeEnum codeEnum) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(codeEnum.getCode());
        response.setMessage(codeEnum.getMessage());
        return response;
    }

    /**
     * Convenience Method: Parameter Validation Failed
     */
    public static Response<?> badRequest(String message) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(String.valueOf(ErrorCode.BAD_REQUEST.getCode()));
        response.setMessage(message != null ? message : ErrorCode.BAD_REQUEST.getMessage());
        return response;
    }

    /**
     * Convenience Method: Resource Not Found
     */
    public static Response<?> notFound(String message) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(String.valueOf(ErrorCode.NOT_FOUND.getCode()));
        response.setMessage(message != null ? message : ErrorCode.NOT_FOUND.getMessage());
        return response;
    }

    /**
     * Convenience Method: Server Error
     */
    public static Response<?> serverError(String message) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(String.valueOf(ErrorCode.INTERNAL_ERROR.getCode()));
        response.setMessage(message != null ? message : ErrorCode.INTERNAL_ERROR.getMessage());
        return response;
    }
}