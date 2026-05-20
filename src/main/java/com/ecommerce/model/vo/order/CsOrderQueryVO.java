package com.ecommerce.model.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Order Query Request VO
 */
@Data
@ApiModel("Order Query Request")
public class CsOrderQueryVO {

    @ApiModelProperty(value = "Order Number", example = "TEST2025042201")
    private String orderNo;

    @ApiModelProperty(value = "User ID", example = "user123")
    private String userId;

    @ApiModelProperty(value = "Order Status", example = "delivered")
    private String status;

    @ApiModelProperty(value = "Logistics Number", example = "SF1234567890")
    private String logisticsNo;
}