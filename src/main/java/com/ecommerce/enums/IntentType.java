package com.ecommerce.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 意图类型枚举
 */
public enum IntentType {

    REFUND("refund", "退款"),
    LOGISTICS("logistics", "物流"),
    PRODUCT("product", "商品"),
    COMPLAINT("complaint", "投诉"),
    OTHER("other", "其他");

    @EnumValue
    private final String code;

    @JsonValue
    private final String desc;

    IntentType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static IntentType fromCode(String code) {
        for (IntentType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return OTHER;
    }
}