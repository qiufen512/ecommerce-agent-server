package com.ecommerce.controller;

import com.ecommerce.common.response.Response;
import com.ecommerce.model.vo.chat.ChatRequestVO;
import com.ecommerce.model.vo.chat.ChatResponseVO;
import com.ecommerce.service.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 聊天控制器
 */
@Slf4j
@Api(tags = "智能客服聊天")
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@Validated
public class ChatController {

    private final ChatService chatService;

    @ApiOperation("发送消息获取AI回复")
    @PostMapping
    public Response chat(@Valid @RequestBody ChatRequestVO request) {
        log.info("收到聊天请求 - sessionId: {}, userId: {}, message: {}",
                request.getSessionId(), request.getUserId(), request.getMessage());

        ChatResponseVO response = chatService.processMessage(request);

        log.info("聊天处理完成 - sessionId: {}, intent: {}, confidence: {}, humanFlag: {}",
                response.getSessionId(), response.getIntent(), response.getConfidence(), response.getHumanFlag());

        return Response.success(response);
    }
}