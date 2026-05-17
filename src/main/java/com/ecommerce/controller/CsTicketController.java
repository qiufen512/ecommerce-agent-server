package com.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecommerce.common.response.Response;
import com.ecommerce.common.response.PageResponse;
import com.ecommerce.entity.CsTicket;
import com.ecommerce.model.vo.ticket.CsTicketCreateVO;
import com.ecommerce.model.vo.ticket.CsTicketQueryVO;
import com.ecommerce.model.vo.ticket.CsTicketResponseVO;
import com.ecommerce.model.vo.ticket.CsTicketUpdateVO;
import com.ecommerce.service.CsTicketService;
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
 * 工单管理控制器
 */
@Api(tags = "工单管理")
@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
@Validated
public class CsTicketController {

    private final CsTicketService ticketService;

    /** 创建工单 */
    @ApiOperation("创建工单")
    @PostMapping
    public Response create(@Valid @RequestBody CsTicketCreateVO vo) {
        CsTicket ticket = new CsTicket();
        BeanUtils.copyProperties(vo, ticket);
        boolean success = ticketService.save(ticket);
        if (success) {
            CsTicketResponseVO responseVO = new CsTicketResponseVO();
            BeanUtils.copyProperties(ticket, responseVO);
            return Response.success(responseVO);
        }
        return Response.fail("创建工单失败");
    }

    /** 按主键ID查询工单 */
    @ApiOperation("按主键ID查询工单")
    @GetMapping("/{id}")
    public Response getById(@ApiParam("主键ID") @PathVariable Long id) {
        CsTicket ticket = ticketService.getById(id);
        if (ticket != null) {
            CsTicketResponseVO vo = new CsTicketResponseVO();
            BeanUtils.copyProperties(ticket, vo);
            return Response.success(vo);
        }
        return Response.notFound("工单不存在: " + id);
    }

    /** 按工单业务ID查询工单 */
    @ApiOperation("按工单业务ID查询工单")
    @GetMapping("/ticketId/{ticketId}")
    public Response getByTicketId(@ApiParam("工单业务ID") @PathVariable Long ticketId) {
        CsTicket ticket = ticketService.getOne(
                new LambdaQueryWrapper<CsTicket>().eq(CsTicket::getTicketId, ticketId));
        if (ticket != null) {
            CsTicketResponseVO vo = new CsTicketResponseVO();
            BeanUtils.copyProperties(ticket, vo);
            return Response.success(vo);
        }
        return Response.notFound("工单不存在: " + ticketId);
    }

    /** 按会话ID查询工单列表 */
    @ApiOperation("按会话ID查询工单列表")
    @GetMapping("/session/{sessionId}")
    public Response listBySessionId(@ApiParam("会话ID") @PathVariable String sessionId) {
        List<CsTicket> list = ticketService.list(
                new LambdaQueryWrapper<CsTicket>().eq(CsTicket::getSessionId, sessionId));
        List<CsTicketResponseVO> voList = list.stream().map(ticket -> {
            CsTicketResponseVO vo = new CsTicketResponseVO();
            BeanUtils.copyProperties(ticket, vo);
            return vo;
        }).collect(java.util.stream.Collectors.toList());
        return Response.success(voList);
    }

    /** 按状态查询工单列表 */
    @ApiOperation("按状态查询工单列表")
    @GetMapping("/status/{status}")
    public Response listByStatus(@ApiParam("状态：0=待处理 1=处理中 2=已完成 3=已关闭") @PathVariable Integer status) {
        List<CsTicket> list = ticketService.list(
                new LambdaQueryWrapper<CsTicket>().eq(CsTicket::getStatus, status));
        List<CsTicketResponseVO> voList = list.stream().map(ticket -> {
            CsTicketResponseVO vo = new CsTicketResponseVO();
            BeanUtils.copyProperties(ticket, vo);
            return vo;
        }).collect(java.util.stream.Collectors.toList());
        return Response.success(voList);
    }

    /** 分页查询工单列表 */
    @ApiOperation("分页查询工单列表")
    @GetMapping("/page")
    public PageResponse page(@ApiParam("当前页") @RequestParam(defaultValue = "1") Integer current,
                                      @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer size,
                                      CsTicketQueryVO queryVO) {
        LambdaQueryWrapper<CsTicket> wrapper = new LambdaQueryWrapper<>();
        if (queryVO.getTicketId() != null) {
            wrapper.eq(CsTicket::getTicketId, queryVO.getTicketId());
        }
        if (queryVO.getSessionId() != null) {
            wrapper.eq(CsTicket::getSessionId, queryVO.getSessionId());
        }
        if (queryVO.getIntent() != null) {
            wrapper.eq(CsTicket::getIntent, queryVO.getIntent());
        }
        if (queryVO.getOrderNo() != null) {
            wrapper.eq(CsTicket::getOrderNo, queryVO.getOrderNo());
        }
        if (queryVO.getStatus() != null) {
            wrapper.eq(CsTicket::getStatus, queryVO.getStatus());
        }
        if (queryVO.getHumanFlag() != null) {
            wrapper.eq(CsTicket::getHumanFlag, queryVO.getHumanFlag());
        }
        Page<CsTicket> page = ticketService.page(new Page<>(current, size), wrapper);
        List<CsTicketResponseVO> voList = page.getRecords().stream().map(ticket -> {
            CsTicketResponseVO vo = new CsTicketResponseVO();
            BeanUtils.copyProperties(ticket, vo);
            return vo;
        }).collect(java.util.stream.Collectors.toList());
        return PageResponse.success(page, voList);
    }

    /** 更新工单 */
    @ApiOperation("更新工单")
    @PutMapping
    public Response update(@Valid @RequestBody CsTicketUpdateVO vo) {
        CsTicket ticket = new CsTicket();
        BeanUtils.copyProperties(vo, ticket);
        boolean success = ticketService.updateById(ticket);
        if (success) {
            CsTicket updated = ticketService.getById(ticket.getId());
            if (updated != null) {
                CsTicketResponseVO responseVO = new CsTicketResponseVO();
                BeanUtils.copyProperties(updated, responseVO);
                return Response.success(responseVO);
            }
        }
        return Response.fail("更新工单失败");
    }

    /** 删除工单 */
    @ApiOperation("删除工单")
    @DeleteMapping("/{id}")
    public Response delete(@ApiParam("主键ID") @PathVariable Long id) {
        boolean success = ticketService.removeById(id);
        return success ? Response.success() : Response.fail("删除工单失败");
    }

    /** 人工接管工单 */
    @ApiOperation("人工接管工单")
    @PostMapping("/takeover/{id}")
    public Response takeover(@ApiParam("主键ID") @PathVariable Long id,
                             @ApiParam("处理人ID") @RequestParam String handlerId) {
        CsTicket ticket = ticketService.getById(id);
        if (ticket == null) {
            return Response.notFound("工单不存在: " + id);
        }
        ticket.setHumanFlag(1);
        ticket.setStatus(1); // 设置为处理中
        boolean success = ticketService.updateById(ticket);
        return success ? Response.success() : Response.fail("接管失败");
    }

    /** 关闭工单 */
    @ApiOperation("关闭工单")
    @PostMapping("/close/{id}")
    public Response close(@ApiParam("主键ID") @PathVariable Long id) {
        CsTicket ticket = ticketService.getById(id);
        if (ticket == null) {
            return Response.notFound("工单不存在: " + id);
        }
        ticket.setStatus(3); // 设置为已关闭
        boolean success = ticketService.updateById(ticket);
        return success ? Response.success() : Response.fail("关闭失败");
    }
}