package com.ecommerce.model.vo.ticket;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Ticket Query Request VO
 */
@Data
@ApiModel("Ticket Query Request")
public class CsTicketQueryVO {

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

    @ApiModelProperty(value = "Human Intervention Flag: 0=AI 1=Human", example = "0")
    private Integer humanFlag;
}