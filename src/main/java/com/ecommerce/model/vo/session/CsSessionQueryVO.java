package com.ecommerce.model.vo.session;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Session Query Request VO
 */
@Data
@ApiModel("Session Query Request")
public class CsSessionQueryVO {

    @ApiModelProperty(value = "Session Unique ID (UUID)", example = "session_001")
    private String sessionId;

    @ApiModelProperty(value = "User ID", example = "user123")
    private String userId;

    @ApiModelProperty(value = "Channel: web/app/mini", example = "web")
    private String channel;

    @ApiModelProperty(value = "Status: 0=Active 1=Ended 2=Human Takeover", example = "0")
    private Integer status;

    @ApiModelProperty(value = "Assigned CS Agent ID", example = "admin_001")
    private String agentId;
}