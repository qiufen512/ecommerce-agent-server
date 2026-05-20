package com.ecommerce.model.vo.session;

import com.ecommerce.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * Create Session Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("Create Session Request")
public class CsSessionCreateVO extends BaseVO {

    @ApiModelProperty(value = "Session Unique ID (UUID)", required = true, example = "session_001")
    @NotBlank(message = "Session ID must not be empty")
    private String sessionId;

    @ApiModelProperty(value = "User ID", required = true, example = "user123")
    @NotBlank(message = "User ID must not be empty")
    private String userId;

    @ApiModelProperty(value = "Channel: web/app/mini", example = "web")
    private String channel;
}