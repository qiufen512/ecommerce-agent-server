package com.ecommerce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.entity.ChatHistory;
import com.ecommerce.mapper.ChatHistoryMapper;
import com.ecommerce.model.vo.chat.ChatMessageVO;
import com.ecommerce.service.ChatHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 对话历史服务实现
 */
@Slf4j
@Service
public class ChatHistoryServiceImpl extends ServiceImpl<ChatHistoryMapper, ChatHistory> implements ChatHistoryService {

    @Override
    public void saveUserMessage(String sessionId, String message, String intent) {
        ChatHistory history = new ChatHistory();
        history.setSessionId(sessionId);
        history.setRole("user");
        history.setContent(message);
        history.setIntent(intent);
        save(history);
    }

    @Override
    public void saveAgentReply(String sessionId, String reply) {
        ChatHistory history = new ChatHistory();
        history.setSessionId(sessionId);
        history.setRole("agent");
        history.setContent(reply);
        history.setIntent(null);
        save(history);
    }

    @Override
    public List<ChatMessageVO> getChatHistory(String sessionId, int limit) {
        List<ChatHistory> histories = baseMapper.listBySessionId(sessionId, limit);
        return histories.stream()
                .map(h -> {
                    ChatMessageVO vo = new ChatMessageVO();
                    vo.setRole(h.getRole());
                    vo.setContent(h.getContent());
                    return vo;
                })
                .collect(Collectors.toList());
    }
}