package com.ecommerce.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Chat status enum
 */
public enum ChatStatus {

    SUCCESS("success", "Success"),
    PENDING("pending", "Pending"),
    FAILED("failed", "Failed"),
    HUMAN_REQUIRED("human_required", "Human Intervention Required");

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