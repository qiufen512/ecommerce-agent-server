package com.ecommerce.service;

import com.ecommerce.model.dto.ChatResponseDTO;
import com.ecommerce.model.vo.chat.ChatRequestVO;

/**
 * Python LangGraph 服务客户端
 */
public interface PythonLangGraphClient {

    /**
     * 调用 Python LangGraph 服务
     *
     * @param request 聊天请求
     * @return 聊天响应
     * @throws PythonServiceException 服务不可用或超时
     */
    ChatResponseDTO callLangGraph(ChatRequestVO request) throws PythonServiceException;
}