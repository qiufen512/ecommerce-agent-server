package com.ecommerce.service;

import com.ecommerce.exception.PythonServiceException;
import com.ecommerce.model.vo.chat.ChatRequestVO;
import com.ecommerce.model.vo.chat.ChatResponseVO;

/**
 * Python LangGraph Service Client
 */
public interface PythonLangGraphClient {

    /**
     * Call Python LangGraph service
     *
     * @param request Chat request
     * @return Chat response
     * @throws PythonServiceException Service unavailable or timeout
     */
    ChatResponseVO callLangGraph(ChatRequestVO request) throws PythonServiceException;
}