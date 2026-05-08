package com.ecommerce.model.vo.session;

import com.ecommerce.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 创建会话请求 VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("创建会话请求")
public class CsSessionCreateVO extends BaseVO {

    @ApiModelProperty(value = "会话唯一ID（UUID）", required = true, example = "session_001")
    @NotBlank(message = "会话ID不能为空")
    private String sessionId;

    @ApiModelProperty(value = "用户ID", required = true, example = "user123")
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    @ApiModelProperty(value = "入口：web/app/mini", example = "web")
    private String channel;
}