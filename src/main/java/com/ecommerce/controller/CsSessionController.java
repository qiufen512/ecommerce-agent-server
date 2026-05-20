package com.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecommerce.common.response.Response;
import com.ecommerce.common.response.PageResponse;
import com.ecommerce.entity.CsSession;
import com.ecommerce.model.vo.session.CsSessionCreateVO;
import com.ecommerce.model.vo.session.CsSessionQueryVO;
import com.ecommerce.model.vo.session.CsSessionResponseVO;
import com.ecommerce.model.vo.session.CsSessionUpdateVO;
import com.ecommerce.service.CsSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import javax.validation.Valid;

/**
 * Customer Service Session Controller
 */
@Api(tags = "Session Management")
@RestController
@RequestMapping("/api/session")
@RequiredArgsConstructor
@Validated
public class CsSessionController {

    private final CsSessionService sessionService;

    /** Create Session */
    @ApiOperation("Create Session")
    @PostMapping
    public Response create(@Valid @RequestBody CsSessionCreateVO vo) {
        CsSession session = new CsSession();
        BeanUtils.copyProperties(vo, session);
        boolean success = sessionService.save(session);
        if (success) {
            CsSessionResponseVO responseVO = new CsSessionResponseVO();
            BeanUtils.copyProperties(session, responseVO);
            return Response.success(responseVO);
        }
        return Response.fail("Failed to create session");
    }

    /** Query Session by Session ID */
    @ApiOperation("Query by Session ID")
    @GetMapping("/{sessionId}")
    public Response getBySessionId(@ApiParam("Session ID") @PathVariable String sessionId) {
        CsSession session = sessionService.getOne(
                new LambdaQueryWrapper<CsSession>().eq(CsSession::getSessionId, sessionId));
        if (session != null) {
            CsSessionResponseVO vo = new CsSessionResponseVO();
            BeanUtils.copyProperties(session, vo);
            return Response.success(vo);
        }
        return Response.notFound("Session not found: " + sessionId);
    }

    /** Query Session by Primary Key ID */
    @ApiOperation("Query Session by Primary Key ID")
    @GetMapping("/id/{id}")
    public Response getById(@ApiParam("Primary Key ID") @PathVariable Long id) {
        CsSession session = sessionService.getById(id);
        if (session != null) {
            CsSessionResponseVO vo = new CsSessionResponseVO();
            BeanUtils.copyProperties(session, vo);
            return Response.success(vo);
        }
        return Response.notFound("Session not found: " + id);
    }

    /** Paged Query of Session List */
    @ApiOperation("Paged Query of Session List")
    @GetMapping("/page")
    public PageResponse page(@ApiParam("Current Page") @RequestParam(defaultValue = "1") Integer current,
                                        @ApiParam("Page Size") @RequestParam(defaultValue = "10") Integer size,
                                        CsSessionQueryVO queryVO) {
        LambdaQueryWrapper<CsSession> wrapper = new LambdaQueryWrapper<>();
        if (queryVO.getSessionId() != null) {
            wrapper.like(CsSession::getSessionId, queryVO.getSessionId());
        }
        if (queryVO.getUserId() != null) {
            wrapper.eq(CsSession::getUserId, queryVO.getUserId());
        }
        if (queryVO.getChannel() != null) {
            wrapper.eq(CsSession::getChannel, queryVO.getChannel());
        }
        if (queryVO.getStatus() != null) {
            wrapper.eq(CsSession::getStatus, queryVO.getStatus());
        }
        if (queryVO.getAgentId() != null) {
            wrapper.eq(CsSession::getAgentId, queryVO.getAgentId());
        }
        Page<CsSession> page = sessionService.page(new Page<>(current, size), wrapper);
        List<CsSessionResponseVO> voList = page.getRecords().stream().map(session -> {
            CsSessionResponseVO vo = new CsSessionResponseVO();
            BeanUtils.copyProperties(session, vo);
            return vo;
        }).collect(java.util.stream.Collectors.toList());
        return PageResponse.success(page, voList);
    }

    /** Update Session */
    @ApiOperation("Update Session")
    @PutMapping
    public Response update(@Valid @RequestBody CsSessionUpdateVO vo) {
        CsSession session = new CsSession();
        BeanUtils.copyProperties(vo, session);
        boolean success = sessionService.updateById(session);
        if (success) {
            CsSession updated = sessionService.getById(session.getId());
            if (updated != null) {
                CsSessionResponseVO responseVO = new CsSessionResponseVO();
                BeanUtils.copyProperties(updated, responseVO);
                return Response.success(responseVO);
            }
        }
        return Response.fail("Failed to update session");
    }

    /** Delete Session */
    @ApiOperation("Delete Session")
    @DeleteMapping("/id/{id}")
    public Response delete(@ApiParam("Primary Key ID") @PathVariable Long id) {
        boolean success = sessionService.removeById(id);
        return success ? Response.success() : Response.fail("Failed to delete session");
    }
}