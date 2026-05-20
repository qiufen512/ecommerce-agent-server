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
@ApiModel("Order Response")
public class CsOrderResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Primary Key ID")
    private Long id;

    @ApiModelProperty("Order Number")
    private String orderNo;

    @ApiModelProperty("User ID")
    private String userId;

    @ApiModelProperty("Product Name")
    private String productName;

    @ApiModelProperty("Order Amount")
    private BigDecimal amount;

    @ApiModelProperty("Order Status: pending/paid/shipped/delivered/refunded/cancelled")
    private String status;

    @ApiModelProperty("Logistics Number")
    private String logisticsNo;

    @ApiModelProperty("Logistics Status")
    private String logisticsStatus;

    @ApiModelProperty("Created At")
    private LocalDateTime createdAt;

    @ApiModelProperty("Updated At")
    private LocalDateTime updatedAt;
}