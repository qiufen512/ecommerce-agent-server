package com.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 客户订单实体（模拟，后续对接真实系统）
 */
@Data
@ApiModel(description = "客户订单信息")
@TableName("cs_order")
public class CsOrder {

    @ApiModelProperty(value = "主键ID", example = "1")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单号", required = true, example = "TEST2025042201")
    private String orderNo;

    @ApiModelProperty(value = "用户ID", required = true, example = "user123")
    private String userId;

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

    @ApiModelProperty(value = "下单时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
