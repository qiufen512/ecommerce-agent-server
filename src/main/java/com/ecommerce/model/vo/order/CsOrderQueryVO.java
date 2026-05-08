package com.ecommerce.model.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单查询请求 VO
 */
@Data
@ApiModel("订单查询请求")
public class CsOrderQueryVO {

    @ApiModelProperty(value = "订单号", example = "TEST2025042201")
    private String orderNo;

    @ApiModelProperty(value = "用户ID", example = "user123")
    private String userId;

    @ApiModelProperty(value = "订单状态", example = "delivered")
    private String status;

    @ApiModelProperty(value = "物流单号", example = "SF1234567890")
    private String logisticsNo;
}