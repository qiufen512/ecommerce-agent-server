package com.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecommerce.common.Result;
import com.ecommerce.entity.CsTicket;
import com.ecommerce.service.CsTicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工单管理控制器
 */
@Api(tags = "工单管理")
@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class CsTicketController {

    private final CsTicketService ticketService;

    /** 创建工单 */
    @ApiOperation("创建工单")
    @PostMapping
    public Result<?> create(@RequestBody CsTicket ticket) {
        boolean success = ticketService.save(ticket);
        return success ? Result.success() : Result.fail("创建工单失败");
    }

    /** 按主键ID查询工单 */
    @ApiOperation("按主键ID查询工单")
    @GetMapping("/{id}")
    public Result<?> getById(@ApiParam("主键ID") @PathVariable Long id) {
        CsTicket ticket = ticketService.getById(id);
        if (ticket != null) {
            return Result.success(ticket);
        }
        return Result.notFound("工单不存在: " + id);
    }

    /** 按工单业务ID查询工单 */
    @ApiOperation("按工单业务ID查询工单")
    @GetMapping("/ticketId/{ticketId}")
    public Result<?> getByTicketId(@ApiParam("工单业务ID") @PathVariable Long ticketId) {
        CsTicket ticket = ticketService.getOne(
                new LambdaQueryWrapper<CsTicket>().eq(CsTicket::getTicketId, ticketId));
        if (ticket != null) {
            return Result.success(ticket);
        }
        return Result.notFound("工单不存在: " + ticketId);
    }

    /** 按会话ID查询工单列表 */
    @ApiOperation("按会话ID查询工单列表")
    @GetMapping("/session/{sessionId}")
    public Result<List<CsTicket>> listBySessionId(@ApiParam("会话ID") @PathVariable String sessionId) {
        List<CsTicket> list = ticketService.list(
                new LambdaQueryWrapper<CsTicket>().eq(CsTicket::getSessionId, sessionId));
        return Result.success(list);
    }

    /** 按状态查询工单列表 */
    @ApiOperation("按状态查询工单列表")
    @GetMapping("/status/{status}")
    public Result<List<CsTicket>> listByStatus(@ApiParam("状态：0=待处理 1=处理中 2=已完成 3=已关闭") @PathVariable Integer status) {
        List<CsTicket> list = ticketService.list(
                new LambdaQueryWrapper<CsTicket>().eq(CsTicket::getStatus, status));
        return Result.success(list);
    }

    /** 分页查询工单列表 */
    @ApiOperation("分页查询工单列表")
    @GetMapping("/page")
    public Result<Page<CsTicket>> page(@ApiParam("当前页") @RequestParam(defaultValue = "1") Integer current,
                                      @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer size) {
        Page<CsTicket> page = ticketService.page(new Page<>(current, size));
        return Result.success(page);
    }

    /** 更新工单 */
    @ApiOperation("更新工单")
    @PutMapping
    public Result<?> update(@RequestBody CsTicket ticket) {
        boolean success = ticketService.updateById(ticket);
        return success ? Result.success() : Result.fail("更新工单失败");
    }

    /** 删除工单 */
    @ApiOperation("删除工单")
    @DeleteMapping("/{id}")
    public Result<?> delete(@ApiParam("主键ID") @PathVariable Long id) {
        boolean success = ticketService.removeById(id);
        return success ? Result.success() : Result.fail("删除工单失败");
    }

    /** 人工接管工单 */
    @ApiOperation("人工接管工单")
    @PostMapping("/takeover/{id}")
    public Result<?> takeover(@ApiParam("主键ID") @PathVariable Long id,
                             @ApiParam("处理人ID") @RequestParam String handlerId) {
        CsTicket ticket = ticketService.getById(id);
        if (ticket == null) {
            return Result.notFound("工单不存在: " + id);
        }
        ticket.setHumanFlag(1);
        ticket.setStatus(1); // 设置为处理中
        boolean success = ticketService.updateById(ticket);
        return success ? Result.success() : Result.fail("接管失败");
    }

    /** 关闭工单 */
    @ApiOperation("关闭工单")
    @PostMapping("/close/{id}")
    public Result<?> close(@ApiParam("主键ID") @PathVariable Long id) {
        CsTicket ticket = ticketService.getById(id);
        if (ticket == null) {
            return Result.notFound("工单不存在: " + id);
        }
        ticket.setStatus(3); // 设置为已关闭
        boolean success = ticketService.updateById(ticket);
        return success ? Result.success() : Result.fail("关闭失败");
    }
}