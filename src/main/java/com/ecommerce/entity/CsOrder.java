package com.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Customer order entity (simulated, to be connected to real system)
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
