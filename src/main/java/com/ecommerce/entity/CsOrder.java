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

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 订单号 */
    private String orderNo;

    /** 用户ID */
    private String userId;

    /** 商品名称 */
    private String productName;

    /** 订单金额 */
    private BigDecimal amount;

    /** 订单状态：pending/paid/shipped/delivered/refunded/cancelled */
    private String status;

    /** 物流单号 */
    private String logisticsNo;

    /** 最新物流状态 */
    private String logisticsStatus;

    /** 下单时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /** 最后更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
