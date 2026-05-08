package com.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 客户订单实体（模拟，后续对接真实系统）
 */
@Data
@TableName("cs_order")
public class CsOrder {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String orderNo;

    private String userId;

    private String productName;

    private BigDecimal amount;

    private String status;

    private String logisticsNo;

    private String logisticsStatus;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
