package com.ecommerce.model.vo.order;

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
@ApiModel("订单响应")
public class CsOrderResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("订单金额")
    private BigDecimal amount;

    @ApiModelProperty("订单状态: pending/paid/shipped/delivered/refunded/cancelled")
    private String status;

    @ApiModelProperty("物流单号")
    private String logisticsNo;

    @ApiModelProperty("物流状态")
    private String logisticsStatus;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedAt;
}