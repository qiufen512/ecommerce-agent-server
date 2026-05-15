package com.ecommerce.service;

import com.ecommerce.model.dto.ChatResponseDTO;
import com.ecommerce.model.vo.chat.ChatRequestVO;

/**
 * 聊天服务接口
 */
public interface ChatService {

    /**
     * 处理用户消息
     *
     * @param request 聊天请求
     * @return 聊天响应
     */
    ChatResponseDTO processMessage(ChatRequestVO request);
}