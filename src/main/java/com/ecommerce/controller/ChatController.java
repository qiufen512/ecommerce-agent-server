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
 * Chat Controller
 */
@Slf4j
@Api(tags = "AI Customer Service Chat")
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@Validated
public class ChatController {

    private final ChatService chatService;

    @ApiOperation("Send Message to Get AI Reply")
    @PostMapping
    public Response chat(@Valid @RequestBody ChatRequestVO request) {
        log.info("Received chat request - sessionId: {}, userId: {}, message: {}",
                request.getSessionId(), request.getUserId(), request.getMessage());

        ChatResponseVO response = chatService.processMessage(request);

        log.info("Chat processing completed - sessionId: {}, intent: {}, confidence: {}, humanFlag: {}",
                response.getSessionId(), response.getIntent(), response.getConfidence(), response.getHumanFlag());

        return Response.success(response);
    }
}