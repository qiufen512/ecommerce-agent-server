package com.ecommerce.model.vo.refund;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Submit Refund Application Request VO
 */
@Data
@ApiModel("Submit Refund Application Request")
public class CsRefundApplyVO {

    @ApiModelProperty(value = "Order Number", required = true, example = "TEST2025042201")
    @NotBlank(message = "Order number must not be empty")
    private String orderNo;

    @ApiModelProperty(value = "User ID", required = true, example = "user123")
    @NotBlank(message = "User ID must not be empty")
    private String userId;

    @ApiModelProperty(value = "Refund Reason", example = "No longer needed")
    private String reason;
}