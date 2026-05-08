package com.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 退款记录实体
 */
@Data
@ApiModel(description = "退款记录信息")
@TableName("cs_refund")
public class CsRefund {

    @ApiModelProperty(value = "主键ID", example = "1")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "退款单号", example = "REF20240422001")
    private String refundNo;

    @ApiModelProperty(value = "关联订单号", example = "TEST2025042201")
    private String orderNo;

    @ApiModelProperty(value = "用户ID", example = "user123")
    private String userId;

    @ApiModelProperty(value = "退款金额", example = "999.00")
    private BigDecimal refundAmount;

    @ApiModelProperty(value = "状态：0=待审核 1=已通过 2=已拒绝 3=退款中 4=已完成 5=已取消", example = "0")
    private Integer status;

    @ApiModelProperty(value = "退款原因", example = "不想要了")
    private String reason;

    @ApiModelProperty(value = "拒绝原因", example = "超过退货期限")
    private String rejectReason;

    @ApiModelProperty(value = "申请时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "审核通过时间")
    private LocalDateTime approvedAt;

    @ApiModelProperty(value = "退款完成时间")
    private LocalDateTime completedAt;
}
