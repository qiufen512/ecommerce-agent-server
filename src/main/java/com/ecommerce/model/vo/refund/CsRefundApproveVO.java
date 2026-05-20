package com.ecommerce.model.vo.refund;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Review Refund Request VO
 */
@Data
@ApiModel("Review Refund Request")
public class CsRefundApproveVO {

    @ApiModelProperty(value = "Refund Number", required = true, example = "REF20240422001")
    @NotBlank(message = "Refund number must not be empty")
    private String refundNo;

    @ApiModelProperty(value = "Is Approved", required = true, example = "true")
    private Boolean approved;

    @ApiModelProperty(value = "Rejection reason (required when approved=false)", example = "Exceeded return deadline")
    private String rejectReason;
}