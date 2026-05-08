package com.ecommerce.model.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 更新订单请求 VO
 */
@Data
@ApiModel("更新订单请求")
public class CsOrderUpdateVO {

    @ApiModelProperty(value = "主键ID", required = true, example = "1")
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @ApiModelProperty(value = "订单号", example = "TEST2025042201")
    private String orderNo;

    @ApiModelProperty(value = "商品名称", example = "智能手表")
    private String productName;

    @ApiModelProperty(value = "订单金额", example = "999.00")
    private BigDecimal amount;

    @ApiModelProperty(value = "订单状态", example = "delivered")
    private String status;

    @ApiModelProperty(value = "物流单号", example = "SF1234567890")
    private String logisticsNo;

    @ApiModelProperty(value = "物流状态", example = "运输中")
    private String logisticsStatus;
}