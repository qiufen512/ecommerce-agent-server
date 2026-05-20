package com.ecommerce.model.vo.ticket;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Update Ticket Request VO
 */
@Data
@ApiModel("Update Ticket Request")
public class CsTicketUpdateVO {

    @ApiModelProperty(value = "Primary Key ID", required = true, example = "1")
    @NotNull(message = "Primary key ID must not be empty")
    private Long id;

    @ApiModelProperty(value = "Ticket Business ID", example = "1001")
    private Long ticketId;

    @ApiModelProperty(value = "Related Session ID", example = "session_001")
    private String sessionId;

    @ApiModelProperty(value = "Intent: refund/logistics/product/complaint", example = "refund")
    private String intent;

    @ApiModelProperty(value = "Related Order Number", example = "TEST2025042201")
    private String orderNo;

    @ApiModelProperty(value = "Status: 0=Pending 1=Processing 2=Completed 3=Closed", example = "0")
    private Integer status;

    @ApiModelProperty(value = "AI Reply Summary", example = "User requested refund, eligibility confirmed")
    private String agentReply;

    @ApiModelProperty(value = "Human Intervention Flag: 0=AI 1=Human", example = "0")
    private Integer humanFlag;
}