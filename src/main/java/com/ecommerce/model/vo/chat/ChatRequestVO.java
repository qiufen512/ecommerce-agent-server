package com.ecommerce.model.vo.chat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 聊天请求 VO
 */
@Data
@ApiModel("聊天请求")
public class ChatRequestVO {

    @ApiModelProperty(value = "会话ID", required = true, example = "session_001")
    @NotBlank(message = "会话ID不能为空")
    private String sessionId;

    @ApiModelProperty(value = "用户ID", required = true, example = "user123")
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    @ApiModelProperty(value = "用户消息", required = true, example = "查询订单TEST2025042201")
    @NotBlank(message = "消息内容不能为空")
    private String message;

    @ApiModelProperty(value = "历史消息（可选）", notes = "多轮对话时携带上下文")
    @Valid
    private List<ChatMessageVO> history;
}