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
@ApiModel("Ticket Response")
public class CsTicketResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Primary Key ID")
    private Long id;

    @ApiModelProperty("Ticket Business ID")
    private Long ticketId;

    @ApiModelProperty("Related Session")
    private String sessionId;

    @ApiModelProperty("Intent: refund/logistics/product/complaint")
    private String intent;

    @ApiModelProperty("Related Order Number")
    private String orderNo;

    @ApiModelProperty("Status: 0=Pending 1=Processing 2=Completed 3=Closed")
    private Integer status;

    @ApiModelProperty("AI Reply Summary")
    private String agentReply;

    @ApiModelProperty("Human Intervention: 0=AI 1=Human")
    private Integer humanFlag;

    @ApiModelProperty("Created At")
    private LocalDateTime createdAt;

    @ApiModelProperty("Updated At")
    private LocalDateTime updatedAt;
}