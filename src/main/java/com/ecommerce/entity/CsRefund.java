package com.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 退款记录实体
 */
@Data
@TableName("cs_refund")
public class CsRefund {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String refundNo;

    private String orderNo;

    private String userId;

    private BigDecimal refundAmount;

    private Integer status;

    private String reason;

    private String rejectReason;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    private LocalDateTime approvedAt;

    private LocalDateTime completedAt;
}
