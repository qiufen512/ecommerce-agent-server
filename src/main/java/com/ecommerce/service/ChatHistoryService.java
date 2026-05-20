package com.ecommerce.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecommerce.entity.ChatHistory;
import com.ecommerce.model.vo.chat.ChatMessageVO;

import java.util.List;

/**
 * Chat History Service Interface
 */
public interface ChatHistoryService extends IService<ChatHistory> {

    /**
     * Save user message
     *
     * @param sessionId Session ID
     * @param message   Message content
     * @param intent    Intent type
     */
    void saveUserMessage(String sessionId, String message, String intent);

    /**
     * Save agent reply
     *
     * @param sessionId Session ID
     * @param reply     Reply content
     */
    void saveAgentReply(String sessionId, String reply);

    /**
     * Query session chat history
     *
     * @param sessionId Session ID
     * @param limit     Limit
     * @return Message list
     */
    List<ChatMessageVO> getChatHistory(String sessionId, int limit);
}