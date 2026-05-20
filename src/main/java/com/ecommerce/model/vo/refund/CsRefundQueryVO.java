package com.ecommerce.model.vo.refund;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Refund Query Request VO
 */
@Data
@ApiModel("Refund Query Request")
public class CsRefundQueryVO {

    @ApiModelProperty(value = "Refund Number", example = "REF20240422001")
    private String refundNo;

    @ApiModelProperty(value = "Order Number", example = "TEST2025042201")
    private String orderNo;

    @ApiModelProperty(value = "User ID", example = "user123")
    private String userId;

    @ApiModelProperty(value = "Refund Status: 0=Pending 1=Approved 2=Rejected 3=Processing 4=Completed 5=Cancelled", example = "0")
    private Integer status;
}