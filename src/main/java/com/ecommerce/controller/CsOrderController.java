package com.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecommerce.common.Result;
import com.ecommerce.entity.CsOrder;
import com.ecommerce.service.CsOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户订单控制器
 */
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class CsOrderController {

    private final CsOrderService orderService;

    /** 创建订单 */
    @PostMapping
    public Result<?> create(@RequestBody CsOrder order) {
        boolean success = orderService.save(order);
        return success ? Result.success() : Result.fail("创建订单失败");
    }

    /** 按订单号查询订单 */
    @GetMapping("/{orderNo}")
    public Result<CsOrder> getByOrderNo(@PathVariable String orderNo) {
        CsOrder order = orderService.getByOrderNoOrThrow(orderNo);
        return Result.success(order);
    }

    /** 按主键ID查询订单 */
    @GetMapping("/id/{id}")
    public Result<?> getById(@PathVariable Long id) {
        CsOrder order = orderService.getById(id);
        if (order != null) {
            return Result.success(order);
        }
        return Result.notFound("订单不存在: " + id);
    }

    /** 按用户ID查询订单列表 */
    @GetMapping("/user/{userId}")
    public Result<List<CsOrder>> listByUserId(@PathVariable String userId) {
        List<CsOrder> list = orderService.list(
                new LambdaQueryWrapper<CsOrder>().eq(CsOrder::getUserId, userId));
        return Result.success(list);
    }

    /** 分页查询订单列表 */
    @GetMapping("/page")
    public Result<Page<CsOrder>> page(@RequestParam(defaultValue = "1") Integer current,
                                      @RequestParam(defaultValue = "10") Integer size) {
        Page<CsOrder> page = orderService.page(new Page<>(current, size));
        return Result.success(page);
    }

    /** 更新订单 */
    @PutMapping
    public Result<?> update(@RequestBody CsOrder order) {
        boolean success = orderService.updateById(order);
        return success ? Result.success() : Result.fail("更新订单失败");
    }

    /** 删除订单 */
    @DeleteMapping("/id/{id}")
    public Result<?> delete(@PathVariable Long id) {
        boolean success = orderService.removeById(id);
        return success ? Result.success() : Result.fail("删除订单失败");
    }
}