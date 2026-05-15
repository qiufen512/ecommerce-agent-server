package com.ecommerce.model.dto;

import com.ecommerce.model.vo.chat.ComplianceCheckVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 聊天响应 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("聊天响应")
public class ChatResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("AI回复内容")
    private String reply;

    @ApiModelProperty("意图类型: refund/logistics/product/complaint/other")
    private String intent;

    @ApiModelProperty("置信度 (0.0~1.0)")
    private Float confidence;

    @ApiModelProperty("会话ID")
    private String sessionId;

    @ApiModelProperty("是否需要人工介入")
    private Boolean humanFlag;

    @ApiModelProperty("合规检查结果")
    private ComplianceCheckVO complianceCheck;
}