package com.ecommerce.common.response;

import com.ecommerce.common.enums.ResponseCodeEnum;
import com.ecommerce.enums.ErrorCode;
import com.ecommerce.exception.BusinessException;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应格式
 *
 * @param <T> 响应数据类型
 */
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 是否成功，默认为 true */
    private boolean success = true;

    /** 响应消息 */
    private String message;

    /** 错误码 */
    private String errorCode;

    /** 响应数据 */
    private T data;

    /**
     * 成功响应（无数据）
     */
    public static Response<?> success() {
        return new Response<>();
    }

    /**
     * 成功响应（有数据）
     */
    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setData(data);
        return response;
    }

    /**
     * 失败响应（无消息）
     */
    public static Response<?> fail() {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        return response;
    }

    /**
     * 失败响应（带消息）
     */
    public static Response<?> fail(String message) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }

    /**
     * 失败响应（带错误码和消息）
     */
    public static Response<?> fail(String errorCode, String message) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(errorCode);
        response.setMessage(message);
        return response;
    }

    /**
     * 从业务异常创建失败响应（Integer code 转 String）
     */
    public static Response<?> fail(BusinessException e) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(String.valueOf(e.getCode()));
        response.setMessage(e.getMessage());
        return response;
    }

    /**
     * 从错误码枚举创建失败响应
     */
    public static Response<?> fail(ErrorCode errorCode) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(String.valueOf(errorCode.getCode()));
        response.setMessage(errorCode.getMessage());
        return response;
    }

    /**
     * 从响应码枚举创建失败响应
     */
    public static Response<?> fail(ResponseCodeEnum codeEnum) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(codeEnum.getCode());
        response.setMessage(codeEnum.getMessage());
        return response;
    }

    /**
     * 快捷方法：参数验证失败
     */
    public static Response<?> badRequest(String message) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(String.valueOf(ErrorCode.BAD_REQUEST.getCode()));
        response.setMessage(message != null ? message : ErrorCode.BAD_REQUEST.getMessage());
        return response;
    }

    /**
     * 快捷方法：资源不存在
     */
    public static Response<?> notFound(String message) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(String.valueOf(ErrorCode.NOT_FOUND.getCode()));
        response.setMessage(message != null ? message : ErrorCode.NOT_FOUND.getMessage());
        return response;
    }

    /**
     * 快捷方法：服务器错误
     */
    public static Response<?> serverError(String message) {
        Response<?> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(String.valueOf(ErrorCode.INTERNAL_ERROR.getCode()));
        response.setMessage(message != null ? message : ErrorCode.INTERNAL_ERROR.getMessage());
        return response;
    }
}