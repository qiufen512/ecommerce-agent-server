package com.ecommerce.model.vo.session;

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
@ApiModel("Session Response")
public class CsSessionResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Primary Key ID")
    private Long id;

    @ApiModelProperty("Session Unique ID (UUID)")
    private String sessionId;

    @ApiModelProperty("User ID")
    private String userId;

    @ApiModelProperty("Channel: web/app/mini")
    private String channel;

    @ApiModelProperty("Status: 0=Active 1=Ended 2=Human Takeover")
    private Integer status;

    @ApiModelProperty("Assigned CS Agent ID")
    private String agentId;

    @ApiModelProperty("Session Start Time")
    private LocalDateTime createdAt;

    @ApiModelProperty("Last Updated At")
    private LocalDateTime updatedAt;

    @ApiModelProperty("Session Closed At")
    private LocalDateTime closedAt;
}