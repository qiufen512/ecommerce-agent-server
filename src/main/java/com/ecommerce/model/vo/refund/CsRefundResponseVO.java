package com.ecommerce.model.vo.refund;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("退款响应")
public class CsRefundResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("退款单号")
    private String refundNo;

    @ApiModelProperty("关联订单号")
    private String orderNo;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("状态: 0=待审核 1=已通过 2=已拒绝 3=退款中 4=已完成 5=已取消")
    private Integer status;

    @ApiModelProperty("退款原因")
    private String reason;

    @ApiModelProperty("拒绝原因")
    private String rejectReason;

    @ApiModelProperty("申请时间")
    private LocalDateTime createdAt;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty("审核通过时间")
    private LocalDateTime approvedAt;

    @ApiModelProperty("退款完成时间")
    private LocalDateTime completedAt;
}