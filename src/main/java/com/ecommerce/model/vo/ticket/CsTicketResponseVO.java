package com.ecommerce.model.vo.ticket;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("工单响应")
public class CsTicketResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("工单业务ID")
    private Long ticketId;

    @ApiModelProperty("关联会话")
    private String sessionId;

    @ApiModelProperty("意图: refund/logistic/product/complaint")
    private String intent;

    @ApiModelProperty("关联订单号")
    private String orderNo;

    @ApiModelProperty("状态: 0=待处理 1=处理中 2=已完成 3=已关闭")
    private Integer status;

    @ApiModelProperty("AI回复摘要")
    private String agentReply;

    @ApiModelProperty("是否人工介入: 0=AI处理 1=人工介入")
    private Integer humanFlag;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedAt;
}