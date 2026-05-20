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
 * Ticket Management Controller
 */
@Api(tags = "Ticket Management")
@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
@Validated
public class CsTicketController {

    private final CsTicketService ticketService;

    /** Create Ticket */
    @ApiOperation("Create Ticket")
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
        return Response.fail("Failed to create ticket");
    }

    /** Query Ticket by Primary Key ID */
    @ApiOperation("Query Ticket by Primary Key ID")
    @GetMapping("/{id}")
    public Response getById(@ApiParam("Primary Key ID") @PathVariable Long id) {
        CsTicket ticket = ticketService.getById(id);
        if (ticket != null) {
            CsTicketResponseVO vo = new CsTicketResponseVO();
            BeanUtils.copyProperties(ticket, vo);
            return Response.success(vo);
        }
        return Response.notFound("Ticket not found: " + id);
    }

    /** Query Ticket by Business Ticket ID */
    @ApiOperation("Query Ticket by Business Ticket ID")
    @GetMapping("/ticketId/{ticketId}")
    public Response getByTicketId(@ApiParam("Business Ticket ID") @PathVariable Long ticketId) {
        CsTicket ticket = ticketService.getOne(
                new LambdaQueryWrapper<CsTicket>().eq(CsTicket::getTicketId, ticketId));
        if (ticket != null) {
            CsTicketResponseVO vo = new CsTicketResponseVO();
            BeanUtils.copyProperties(ticket, vo);
            return Response.success(vo);
        }
        return Response.notFound("Ticket not found: " + ticketId);
    }

    /** Query Ticket List by Session ID */
    @ApiOperation("Query Ticket List by Session ID")
    @GetMapping("/session/{sessionId}")
    public Response listBySessionId(@ApiParam("Session ID") @PathVariable String sessionId) {
        List<CsTicket> list = ticketService.list(
                new LambdaQueryWrapper<CsTicket>().eq(CsTicket::getSessionId, sessionId));
        List<CsTicketResponseVO> voList = list.stream().map(ticket -> {
            CsTicketResponseVO vo = new CsTicketResponseVO();
            BeanUtils.copyProperties(ticket, vo);
            return vo;
        }).collect(java.util.stream.Collectors.toList());
        return Response.success(voList);
    }

    /** Query Ticket List by Status */
    @ApiOperation("Query Ticket List by Status")
    @GetMapping("/status/{status}")
    public Response listByStatus(@ApiParam("Status: 0=Pending 1=In Progress 2=Completed 3=Closed") @PathVariable Integer status) {
        List<CsTicket> list = ticketService.list(
                new LambdaQueryWrapper<CsTicket>().eq(CsTicket::getStatus, status));
        List<CsTicketResponseVO> voList = list.stream().map(ticket -> {
            CsTicketResponseVO vo = new CsTicketResponseVO();
            BeanUtils.copyProperties(ticket, vo);
            return vo;
        }).collect(java.util.stream.Collectors.toList());
        return Response.success(voList);
    }

    /** Paged Query of Ticket List */
    @ApiOperation("Paged Query of Ticket List")
    @GetMapping("/page")
    public PageResponse page(@ApiParam("Current Page") @RequestParam(defaultValue = "1") Integer current,
                                      @ApiParam("Page Size") @RequestParam(defaultValue = "10") Integer size,
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

    /** Update Ticket */
    @ApiOperation("Update Ticket")
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
        return Response.fail("Failed to update ticket");
    }

    /** Delete Ticket */
    @ApiOperation("Delete Ticket")
    @DeleteMapping("/{id}")
    public Response delete(@ApiParam("Primary Key ID") @PathVariable Long id) {
        boolean success = ticketService.removeById(id);
        return success ? Response.success() : Response.fail("Failed to delete ticket");
    }

    /** Human Takeover Ticket */
    @ApiOperation("Human Takeover Ticket")
    @PostMapping("/takeover/{id}")
    public Response takeover(@ApiParam("Primary Key ID") @PathVariable Long id,
                             @ApiParam("Handler ID") @RequestParam String handlerId) {
        CsTicket ticket = ticketService.getById(id);
        if (ticket == null) {
            return Response.notFound("Ticket not found: " + id);
        }
        ticket.setHumanFlag(1);
        ticket.setStatus(1); // Set to In Progress
        boolean success = ticketService.updateById(ticket);
        return success ? Response.success() : Response.fail("Takeover failed");
    }

    /** Close Ticket */
    @ApiOperation("Close Ticket")
    @PostMapping("/close/{id}")
    public Response close(@ApiParam("Primary Key ID") @PathVariable Long id) {
        CsTicket ticket = ticketService.getById(id);
        if (ticket == null) {
            return Response.notFound("Ticket not found: " + id);
        }
        ticket.setStatus(3); // Set to Closed
        boolean success = ticketService.updateById(ticket);
        return success ? Response.success() : Response.fail("Close failed");
    }
}