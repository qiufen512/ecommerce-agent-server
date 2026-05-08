package com.ecommerce.model.vo.refund;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 审核退款请求 VO
 */
@Data
@ApiModel("审核退款请求")
public class CsRefundApproveVO {

    @ApiModelProperty(value = "退款单号", required = true, example = "REF20240422001")
    @NotBlank(message = "退款单号不能为空")
    private String refundNo;

    @ApiModelProperty(value = "是否通过", required = true, example = "true")
    private Boolean approved;

    @ApiModelProperty(value = "拒绝原因（approved=false时必填）", example = "超过退货期限")
    private String rejectReason;
}