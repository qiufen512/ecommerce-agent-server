package com.ecommerce.model.vo.refund;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 发起退款申请请求 VO
 */
@Data
@ApiModel("发起退款申请请求")
public class CsRefundApplyVO {

    @ApiModelProperty(value = "订单号", required = true, example = "TEST2025042201")
    @NotBlank(message = "订单号不能为空")
    private String orderNo;

    @ApiModelProperty(value = "用户ID", required = true, example = "user123")
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    @ApiModelProperty(value = "退款原因", example = "不想要了")
    private String reason;
}