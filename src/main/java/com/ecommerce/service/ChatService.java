package com.ecommerce.service;

import com.ecommerce.model.vo.chat.ChatRequestVO;
import com.ecommerce.model.vo.chat.ChatResponseVO;

/**
 * Chat Service Interface
 */
public interface ChatService {

    /**
     * Process user message
     *
     * @param request Chat request
     * @return Chat response
     */
    ChatResponseVO processMessage(ChatRequestVO request);
}