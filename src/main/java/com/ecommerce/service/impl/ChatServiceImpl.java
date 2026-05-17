package com.ecommerce.service.impl;

import com.ecommerce.enums.ErrorCode;
import com.ecommerce.exception.PythonServiceException;
import com.ecommerce.model.vo.chat.ChatRequestVO;
import com.ecommerce.model.vo.chat.ChatResponseVO;
import com.ecommerce.service.ChatService;
import com.ecommerce.service.PythonLangGraphClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 聊天服务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final PythonLangGraphClient pythonClient;

    @Override
    public ChatResponseVO processMessage(ChatRequestVO request) {
        try {
            return pythonClient.callLangGraph(request);
        } catch (PythonServiceException e) {
            log.error("Python服务调用失败，返回降级响应: {}", e.getMessage());
            return buildFallbackResponse(request);
        }
    }

    private ChatResponseVO buildFallbackResponse(ChatRequestVO request) {
        ChatResponseVO response = new ChatResponseVO();
        response.setReply("服务暂时繁忙，请稍后重试或联系人工客服");
        response.setIntent("other");
        response.setConfidence(0f);
        response.setSessionId(request.getSessionId());
        response.setHumanFlag(true);
        response.setComplianceCheck(null);
        return response;
    }
}