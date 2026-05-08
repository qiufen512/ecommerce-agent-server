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

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 退款单号 */
    private String refundNo;

    /** 关联订单号 */
    private String orderNo;

    /** 用户ID */
    private String userId;

    /** 退款金额 */
    private BigDecimal refundAmount;

    /** 0=待审核 1=已通过 2=已拒绝 3=退款中 4=已完成 5=已取消 */
    private Integer status;

    /** 退款原因 */
    private String reason;

    /** 拒绝原因 */
    private String rejectReason;

    /** 申请时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /** 最后更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    /** 审核通过时间 */
    private LocalDateTime approvedAt;

    /** 退款完成时间 */
    private LocalDateTime completedAt;
}
