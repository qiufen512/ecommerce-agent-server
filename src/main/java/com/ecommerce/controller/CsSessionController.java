package com.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecommerce.common.Result;
import com.ecommerce.entity.CsSession;
import com.ecommerce.service.CsSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 客服会话控制器
 */
@RestController
@RequestMapping("/api/session")
@RequiredArgsConstructor
public class CsSessionController {

    private final CsSessionService sessionService;

    /** 创建会话 */
    @PostMapping
    public Result<?> create(@RequestBody CsSession session) {
        boolean success = sessionService.save(session);
        return success ? Result.success() : Result.fail("创建会话失败");
    }

    /** 按sessionId查询会话 */
    @GetMapping("/{sessionId}")
    public Result<?> getBySessionId(@PathVariable String sessionId) {
        CsSession session = sessionService.getOne(
                new LambdaQueryWrapper<CsSession>().eq(CsSession::getSessionId, sessionId));
        if (session != null) {
            return Result.success(session);
        }
        return Result.notFound("会话不存在: " + sessionId);
    }

    /** 按主键ID查询会话 */
    @GetMapping("/id/{id}")
    public Result<?> getById(@PathVariable Long id) {
        CsSession session = sessionService.getById(id);
        if (session != null) {
            return Result.success(session);
        }
        return Result.notFound("会话不存在: " + id);
    }

    /** 分页查询会话列表 */
    @GetMapping("/page")
    public Result<Page<CsSession>> page(@RequestParam(defaultValue = "1") Integer current,
                                        @RequestParam(defaultValue = "10") Integer size) {
        Page<CsSession> page = sessionService.page(new Page<>(current, size));
        return Result.success(page);
    }

    /** 更新会话 */
    @PutMapping
    public Result<?> update(@RequestBody CsSession session) {
        boolean success = sessionService.updateById(session);
        return success ? Result.success() : Result.fail("更新会话失败");
    }

    /** 删除会话 */
    @DeleteMapping("/id/{id}")
    public Result<?> delete(@PathVariable Long id) {
        boolean success = sessionService.removeById(id);
        return success ? Result.success() : Result.fail("删除会话失败");
    }
}