package com.ecommerce.model.vo.chat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Chat Message VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Chat Message")
public class ChatMessageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Role: user/agent")
    private String role;

    @ApiModelProperty("Message Content")
    private String content;
}