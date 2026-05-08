package com.ecommerce.model.vo.ticket;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 工单查询请求 VO
 */
@Data
@ApiModel("工单查询请求")
public class CsTicketQueryVO {

    @ApiModelProperty(value = "工单业务ID", example = "1001")
    private Long ticketId;

    @ApiModelProperty(value = "关联会话ID", example = "session_001")
    private String sessionId;

    @ApiModelProperty(value = "意图类型：refund/logistic/product/complaint", example = "refund")
    private String intent;

    @ApiModelProperty(value = "关联订单号", example = "TEST2025042201")
    private String orderNo;

    @ApiModelProperty(value = "状态：0=待处理 1=处理中 2=已完成 3=已关闭", example = "0")
    private Integer status;

    @ApiModelProperty(value = "人工介入标识：0=AI处理 1=人工介入", example = "0")
    private Integer humanFlag;
}