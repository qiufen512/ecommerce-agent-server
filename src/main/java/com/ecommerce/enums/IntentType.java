package com.ecommerce.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Intent type enum
 */
public enum IntentType {

    REFUND("refund", "Refund"),
    LOGISTICS("logistics", "Logistics"),
    PRODUCT("product", "Product"),
    COMPLAINT("complaint", "Complaint"),
    OTHER("other", "Other");

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