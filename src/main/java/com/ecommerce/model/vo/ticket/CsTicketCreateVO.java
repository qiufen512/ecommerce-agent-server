package com.ecommerce.model.vo.ticket;

import com.ecommerce.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * Create Ticket Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("Create Ticket Request")
public class CsTicketCreateVO extends BaseVO {

    @ApiModelProperty(value = "Ticket Business ID", required = true, example = "1001")
    private Long ticketId;

    @ApiModelProperty(value = "Related Session ID", required = true, example = "session_001")
    @NotBlank(message = "Session ID must not be empty")
    private String sessionId;

    @ApiModelProperty(value = "Intent: refund/logistics/product/complaint", required = true, example = "refund")
    @NotBlank(message = "Intent must not be empty")
    private String intent;

    @ApiModelProperty(value = "Related Order Number", example = "TEST2025042201")
    private String orderNo;

    @ApiModelProperty(value = "AI Reply Summary", example = "User requested refund, eligibility confirmed")
    private String agentReply;
}