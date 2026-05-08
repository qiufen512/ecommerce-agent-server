package com.ecommerce.model.vo.order;

import com.ecommerce.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * 创建订单请求 VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("创建订单请求")
public class CsOrderCreateVO extends BaseVO {

    @ApiModelProperty(value = "订单号", required = true, example = "TEST2025042201")
    @NotBlank(message = "订单号不能为空")
    private String orderNo;

    @ApiModelProperty(value = "用户ID", required = true, example = "user123")
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    @ApiModelProperty(value = "商品名称", example = "智能手表")
    @NotBlank(message = "商品名称不能为空")
    private String productName;

    @ApiModelProperty(value = "订单金额", required = true, example = "999.00")
    @NotNull(message = "订单金额不能为空")
    @Positive(message = "订单金额必须大于0")
    private BigDecimal amount;

    @ApiModelProperty(value = "订单状态", example = "delivered")
    private String status;

    @ApiModelProperty(value = "物流单号", example = "SF1234567890")
    private String logisticsNo;

    @ApiModelProperty(value = "物流状态", example = "运输中")
    private String logisticsStatus;
}