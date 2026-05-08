package com.ecommerce.model.vo.ticket;

import com.ecommerce.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 创建工单请求 VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("创建工单请求")
public class CsTicketCreateVO extends BaseVO {

    @ApiModelProperty(value = "工单业务ID", required = true, example = "1001")
    private Long ticketId;

    @ApiModelProperty(value = "关联会话ID", required = true, example = "session_001")
    @NotBlank(message = "会话ID不能为空")
    private String sessionId;

    @ApiModelProperty(value = "意图类型：refund/logistic/product/complaint", required = true, example = "refund")
    @NotBlank(message = "意图类型不能为空")
    private String intent;

    @ApiModelProperty(value = "关联订单号", example = "TEST2025042201")
    private String orderNo;

    @ApiModelProperty(value = "AI回复摘要", example = "用户要求退款，已确认资格")
    private String agentReply;
}