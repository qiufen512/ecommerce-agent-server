package com.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecommerce.common.response.Response;
import com.ecommerce.common.response.PageResponse;
import com.ecommerce.entity.CsOrder;
import com.ecommerce.model.vo.order.CsOrderCreateVO;
import com.ecommerce.model.vo.order.CsOrderQueryVO;
import com.ecommerce.model.vo.order.CsOrderResponseVO;
import com.ecommerce.model.vo.order.CsOrderUpdateVO;
import com.ecommerce.service.CsOrderService;
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
 * 客户订单控制器
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Validated
public class CsOrderController {

    private final CsOrderService orderService;

    /** 创建订单 */
    @ApiOperation("创建订单")
    @PostMapping
    public Response create(@Valid @RequestBody CsOrderCreateVO vo) {
        CsOrder order = new CsOrder();
        BeanUtils.copyProperties(vo, order);
        boolean success = orderService.save(order);
        return success ? Response.success() : Response.fail("创建订单失败");
    }

    /** 按订单号查询订单 */
    @ApiOperation("按订单号查询订单")
    @GetMapping("/{orderNo}")
    public Response getByOrderNo(@ApiParam("订单号") @PathVariable String orderNo) {
        CsOrder order = orderService.getByOrderNoOrThrow(orderNo);
        CsOrderResponseVO vo = new CsOrderResponseVO();
        BeanUtils.copyProperties(order, vo);
        return Response.success(vo);
    }

    /** 按主键ID查询订单 */
    @ApiOperation("按主键ID查询订单")
    @GetMapping("/id/{id}")
    public Response getById(@ApiParam("主键ID") @PathVariable Long id) {
        CsOrder order = orderService.getById(id);
        if (order != null) {
            CsOrderResponseVO vo = new CsOrderResponseVO();
            BeanUtils.copyProperties(order, vo);
            return Response.success(vo);
        }
        return Response.notFound("订单不存在: " + id);
    }

    /** 按用户ID查询订单列表 */
    @ApiOperation("按用户ID查询订单列表")
    @GetMapping("/user/{userId}")
    public Response listByUserId(@ApiParam("用户ID") @PathVariable String userId) {
        List<CsOrder> list = orderService.list(
                new LambdaQueryWrapper<CsOrder>().eq(CsOrder::getUserId, userId));
        List<CsOrderResponseVO> voList = list.stream().map(order -> {
            CsOrderResponseVO vo = new CsOrderResponseVO();
            BeanUtils.copyProperties(order, vo);
            return vo;
        }).collect(java.util.stream.Collectors.toList());
        return Response.success(voList);
    }

    /** 分页查询订单列表 */
    @ApiOperation("分页查询订单列表")
    @GetMapping("/page")
    public PageResponse page(@ApiParam("当前页") @RequestParam(defaultValue = "1") Integer current,
                                      @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer size,
                                      CsOrderQueryVO queryVO) {
        LambdaQueryWrapper<CsOrder> wrapper = new LambdaQueryWrapper<>();
        if (queryVO.getOrderNo() != null) {
            wrapper.like(CsOrder::getOrderNo, queryVO.getOrderNo());
        }
        if (queryVO.getUserId() != null) {
            wrapper.eq(CsOrder::getUserId, queryVO.getUserId());
        }
        if (queryVO.getStatus() != null) {
            wrapper.eq(CsOrder::getStatus, queryVO.getStatus());
        }
        if (queryVO.getLogisticsNo() != null) {
            wrapper.eq(CsOrder::getLogisticsNo, queryVO.getLogisticsNo());
        }
        Page<CsOrder> page = orderService.page(new Page<>(current, size), wrapper);
        List<CsOrderResponseVO> voList = page.getRecords().stream().map(order -> {
            CsOrderResponseVO vo = new CsOrderResponseVO();
            BeanUtils.copyProperties(order, vo);
            return vo;
        }).collect(java.util.stream.Collectors.toList());
        return PageResponse.success(page, voList);
    }

    /** 更新订单 */
    @ApiOperation("更新订单")
    @PutMapping
    public Response update(@Valid @RequestBody CsOrderUpdateVO vo) {
        CsOrder order = new CsOrder();
        BeanUtils.copyProperties(vo, order);
        boolean success = orderService.updateById(order);
        return success ? Response.success() : Response.fail("更新订单失败");
    }

    /** 删除订单 */
    @ApiOperation("删除订单")
    @DeleteMapping("/id/{id}")
    public Response delete(@ApiParam("主键ID") @PathVariable Long id) {
        boolean success = orderService.removeById(id);
        return success ? Response.success() : Response.fail("删除订单失败");
    }
}