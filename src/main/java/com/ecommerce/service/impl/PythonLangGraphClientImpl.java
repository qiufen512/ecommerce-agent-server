package com.ecommerce.service.impl;

import com.ecommerce.enums.ErrorCode;
import com.ecommerce.exception.PythonServiceException;
import com.ecommerce.model.dto.PythonRequest;
import com.ecommerce.model.dto.PythonResponse;
import com.ecommerce.model.vo.chat.ChatRequestVO;
import com.ecommerce.model.vo.chat.ChatResponseVO;
import com.ecommerce.model.vo.chat.ComplianceCheckVO;
import com.ecommerce.service.PythonLangGraphClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Python LangGraph 服务客户端实现
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PythonLangGraphClientImpl implements PythonLangGraphClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${python.langgraph.url:http://localhost:8090}")
    private String pythonBaseUrl;

    @Override
    public ChatResponseVO callLangGraph(ChatRequestVO request) throws PythonServiceException {
        long startTime = System.currentTimeMillis();
        String url = pythonBaseUrl + "/run";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            PythonRequest pythonRequest = convertToPythonRequest(request);
            HttpEntity<PythonRequest> entity = new HttpEntity<>(pythonRequest, headers);

            log.info("调用Python服务 - URL: {}, sessionId: {}", url, request.getSessionId());

            ResponseEntity<PythonResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    PythonResponse.class
            );

            long elapsed = System.currentTimeMillis() - startTime;
            log.info("Python服务调用成功 - 耗时: {}ms, status: {}", elapsed, response.getStatusCode());

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return convertToChatResponseVO(response.getBody(), request.getSessionId());
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

    private PythonRequest convertToPythonRequest(ChatRequestVO request) {
        PythonRequest pythonRequest = new PythonRequest();
        pythonRequest.setSessionId(request.getSessionId());
        pythonRequest.setUserId(request.getUserId());
        pythonRequest.setUserInput(request.getMessage());

        if (request.getHistory() != null && !request.getHistory().isEmpty()) {
            List<Map<String, String>> historyItems = request.getHistory().stream()
                    .map(msg -> {
                        Map<String, String> item = new HashMap<>();
                        item.put("role", msg.getRole());
                        item.put("content", msg.getContent());
                        return item;
                    })
                    .collect(Collectors.toList());
            pythonRequest.setHistory(historyItems);
        }
        return pythonRequest;
    }

    private ChatResponseVO convertToChatResponseVO(PythonResponse pythonResponse, String sessionId) {
        ChatResponseVO vo = new ChatResponseVO();
        vo.setReply(pythonResponse.getReply());
        vo.setIntent(pythonResponse.getIntent());
        vo.setConfidence(pythonResponse.getConfidence() != null ? pythonResponse.getConfidence().floatValue() : 0f);
        vo.setSessionId(sessionId);
        vo.setHumanFlag(pythonResponse.getHumanFlag() != null ? pythonResponse.getHumanFlag() : false);

        if (pythonResponse.getComplianceCheck() != null) {
            ComplianceCheckVO check = new ComplianceCheckVO();
            check.setPass(pythonResponse.getComplianceCheck().getPass());
            check.setRisks(pythonResponse.getComplianceCheck().getRisks());
            vo.setComplianceCheck(check);
        }
        return vo;
    }
}