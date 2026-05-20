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
@ApiModel("Refund Response")
public class CsRefundResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Primary Key ID")
    private Long id;

    @ApiModelProperty("Refund Number")
    private String refundNo;

    @ApiModelProperty("Related Order Number")
    private String orderNo;

    @ApiModelProperty("User ID")
    private String userId;

    @ApiModelProperty("Refund Amount")
    private BigDecimal refundAmount;

    @ApiModelProperty("Status: 0=Pending 1=Approved 2=Rejected 3=Processing 4=Completed 5=Cancelled")
    private Integer status;

    @ApiModelProperty("Refund Reason")
    private String reason;

    @ApiModelProperty("Rejection Reason")
    private String rejectReason;

    @ApiModelProperty("Applied At")
    private LocalDateTime createdAt;

    @ApiModelProperty("Updated At")
    private LocalDateTime updatedAt;

    @ApiModelProperty("Approved At")
    private LocalDateTime approvedAt;

    @ApiModelProperty("Completed At")
    private LocalDateTime completedAt;
}