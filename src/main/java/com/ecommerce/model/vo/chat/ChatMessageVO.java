package com.ecommerce.model.vo.chat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 聊天消息 VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("聊天消息")
public class ChatMessageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色: user/agent")
    private String role;

    @ApiModelProperty("消息内容")
    private String content;
}