package com.ecommerce.model.vo.refund;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 退款查询请求 VO
 */
@Data
@ApiModel("退款查询请求")
public class CsRefundQueryVO {

    @ApiModelProperty(value = "退款单号", example = "REF20240422001")
    private String refundNo;

    @ApiModelProperty(value = "订单号", example = "TEST2025042201")
    private String orderNo;

    @ApiModelProperty(value = "用户ID", example = "user123")
    private String userId;

    @ApiModelProperty(value = "退款状态：0=待审核 1=已通过 2=已拒绝 3=退款中 4=已完成 5=已取消", example = "0")
    private Integer status;
}