package com.ecommerce.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一响应体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 状态码：200=成功，其他=失败 */
    private Integer code;

    /** 响应消息 */
    private String message;

    /** 响应数据 */
    private T data;

    /** 时间戳 */
    private Long timestamp;

    /** 成功响应（无数据） */
    public static Result<?> success() {
        return new Result<Void>(200, "success", null, System.currentTimeMillis());
    }

    /** 成功响应（有数据） */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data, System.currentTimeMillis());
    }

    /** 失败响应 */
    public static Result<?> fail(String message) {
        return new Result<Void>(500, message, null, System.currentTimeMillis());
    }

    /** 失败响应（自定义状态码） */
    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<T>(code, message, null, System.currentTimeMillis());
    }

    /** 参数校验失败 */
    public static Result<?> badRequest(String message) {
        return new Result<Void>(400, message, null, System.currentTimeMillis());
    }

    /** 资源不存在 */
    public static Result<?> notFound(String message) {
        return new Result<Void>(404, message, null, System.currentTimeMillis());
    }
}