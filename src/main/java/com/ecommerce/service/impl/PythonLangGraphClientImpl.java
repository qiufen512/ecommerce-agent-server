package com.ecommerce.service.impl;

import com.ecommerce.enums.ErrorCode;
import com.ecommerce.exception.PythonServiceException;
import com.ecommerce.model.dto.ChatResponseDTO;
import com.ecommerce.model.vo.chat.ChatRequestVO;
import com.ecommerce.service.PythonLangGraphClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Python LangGraph 服务客户端实现
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PythonLangGraphClientImpl implements PythonLangGraphClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${python.langgraph.url:http://localhost:8000}")
    private String pythonBaseUrl;

    @Override
    public ChatResponseDTO callLangGraph(ChatRequestVO request) throws PythonServiceException {
        long startTime = System.currentTimeMillis();
        String url = pythonBaseUrl + "/run";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<ChatRequestVO> entity = new HttpEntity<>(request, headers);

            log.info("调用Python服务 - URL: {}, sessionId: {}", url, request.getSessionId());

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            long elapsed = System.currentTimeMillis() - startTime;
            log.info("Python服务调用成功 - 耗时: {}ms, status: {}", elapsed, response.getStatusCode());

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return objectMapper.readValue(response.getBody(), ChatResponseDTO.class);
            } else {
                throw new PythonServiceException(ErrorCode.PYTHON_SERVICE_UNAVAILABLE.getCode(),
                        "Python服务返回异常状态: " + response.getStatusCode());
            }

        } catch (PythonServiceException e) {
            throw e;
        } catch (Exception e) {
            long elapsed = System.currentTimeMillis() - startTime;
            log.error("Python服务调用失败 - 耗时: {}ms, error: {}", elapsed, e.getMessage());
            throw new PythonServiceException(ErrorCode.PYTHON_SERVICE_UNAVAILABLE.getCode(),
                    "Python服务不可用: " + e.getMessage());
        }
    }
}