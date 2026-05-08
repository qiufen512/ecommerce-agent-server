package com.ecommerce.model.vo.session;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 会话查询请求 VO
 */
@Data
@ApiModel("会话查询请求")
public class CsSessionQueryVO {

    @ApiModelProperty(value = "会话唯一ID（UUID）", example = "session_001")
    private String sessionId;

    @ApiModelProperty(value = "用户ID", example = "user123")
    private String userId;

    @ApiModelProperty(value = "入口：web/app/mini", example = "web")
    private String channel;

    @ApiModelProperty(value = "状态：0=进行中 1=已结束 2=人工接管", example = "0")
    private Integer status;

    @ApiModelProperty(value = "接管的人工客服ID", example = "admin_001")
    private String agentId;
}