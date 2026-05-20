package com.ecommerce.model.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Update Order Request VO
 */
@Data
@ApiModel("Update Order Request")
public class CsOrderUpdateVO {

    @ApiModelProperty(value = "Primary Key ID", required = true, example = "1")
    @NotNull(message = "Primary key ID must not be empty")
    private Long id;

    @ApiModelProperty(value = "Order Number", example = "TEST2025042201")
    private String orderNo;

    @ApiModelProperty(value = "Product Name", example = "Smart Watch")
    private String productName;

    @ApiModelProperty(value = "Order Amount", example = "999.00")
    private BigDecimal amount;

    @ApiModelProperty(value = "Order Status", example = "delivered")
    private String status;

    @ApiModelProperty(value = "Logistics Number", example = "SF1234567890")
    private String logisticsNo;

    @ApiModelProperty(value = "Logistics Status", example = "In Transit")
    private String logisticsStatus;
}