package com.ecommerce.common.enums;

import lombok.Getter;

/**
 * 响应码枚举
 */
@Getter
public enum ResponseCodeEnum {

    // 成功响应
    SUCCESS("200", "成功"),

    // 客户端错误 (400-499)
    BAD_REQUEST("400", "请求参数错误"),
    UNAUTHORIZED("401", "未授权"),
    FORBIDDEN("403", "禁止访问"),
    NOT_FOUND("404", "资源不存在"),
    METHOD_NOT_ALLOWED("405", "方法不允许"),

    // 服务器错误 (500-599)
    SERVER_ERROR("500", "服务器内部错误"),
    SERVICE_UNAVAILABLE("503", "服务不可用"),

    // 业务错误 (1000-1999)
    BUSINESS_ERROR("1000", "业务错误"),
    VALIDATION_ERROR("1001", "参数验证失败"),
    DATA_NOT_FOUND("1002", "数据不存在"),
    DATA_EXISTS("1003", "数据已存在"),
    OPERATION_FAILED("1004", "操作失败"),

    // 第三方服务错误 (2000-2999)
    PYTHON_SERVICE_ERROR("2000", "Python服务调用失败"),
    DATABASE_ERROR("2001", "数据库操作失败"),
    EXTERNAL_API_ERROR("2002", "外部API调用失败");

    private final String code;
    private final String message;

    ResponseCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据code获取枚举
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