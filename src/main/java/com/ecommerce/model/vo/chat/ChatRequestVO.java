package com.ecommerce.model.vo.chat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Chat Request VO
 */
@Data
@ApiModel("Chat Request")
public class ChatRequestVO {

    @ApiModelProperty(value = "Session ID", required = true, example = "session_001")
    @NotBlank(message = "Session ID must not be empty")
    private String sessionId;

    @ApiModelProperty(value = "User ID", required = true, example = "user123")
    @NotBlank(message = "User ID must not be empty")
    private String userId;

    @ApiModelProperty(value = "User Message", required = true, example = "Query order TEST2025042201")
    @NotBlank(message = "Message content must not be empty")
    private String message;

    @ApiModelProperty(value = "History Messages (optional)", notes = "Context for multi-turn conversations")
    @Valid
    private List<ChatMessageVO> history;
}