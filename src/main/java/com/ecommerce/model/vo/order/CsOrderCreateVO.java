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
 * Create Order Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("Create Order Request")
public class CsOrderCreateVO extends BaseVO {

    @ApiModelProperty(value = "Order Number", required = true, example = "TEST2025042201")
    @NotBlank(message = "Order number must not be empty")
    private String orderNo;

    @ApiModelProperty(value = "User ID", required = true, example = "user123")
    @NotBlank(message = "User ID must not be empty")
    private String userId;

    @ApiModelProperty(value = "Product Name", example = "Smart Watch")
    @NotBlank(message = "Product name must not be empty")
    private String productName;

    @ApiModelProperty(value = "Order Amount", required = true, example = "999.00")
    @NotNull(message = "Order amount must not be empty")
    @Positive(message = "Order amount must be greater than 0")
    private BigDecimal amount;

    @ApiModelProperty(value = "Order Status", example = "delivered")
    private String status;

    @ApiModelProperty(value = "Logistics Number", example = "SF1234567890")
    private String logisticsNo;

    @ApiModelProperty(value = "Logistics Status", example = "In Transit")
    private String logisticsStatus;
}