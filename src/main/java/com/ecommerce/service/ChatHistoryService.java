package com.ecommerce.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecommerce.entity.ChatHistory;
import com.ecommerce.model.vo.chat.ChatMessageVO;

import java.util.List;

/**
 * 对话历史服务接口
 */
public interface ChatHistoryService extends IService<ChatHistory> {

    /**
     * 保存用户消息
     *
     * @param sessionId 会话ID
     * @param message   消息内容
     * @param intent    意图类型
     */
    void saveUserMessage(String sessionId, String message, String intent);

    /**
     * 保存Agent回复
     *
     * @param sessionId 会话ID
     * @param reply     回复内容
     */
    void saveAgentReply(String sessionId, String reply);

    /**
     * 查询会话历史记录
     *
     * @param sessionId 会话ID
     * @param limit     限制条数
     * @return 消息列表
     */
    List<ChatMessageVO> getChatHistory(String sessionId, int limit);
}