package com.ecommerce.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 聊天状态枚举
 */
public enum ChatStatus {

    SUCCESS("success", "处理成功"),
    PENDING("pending", "处理中"),
    FAILED("failed", "处理失败"),
    HUMAN_REQUIRED("human_required", "需要人工介入");

    @EnumValue
    private final String code;

    @JsonValue
    private final String desc;

    ChatStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ChatStatus fromCode(String code) {
        for (ChatStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return FAILED;
    }
}