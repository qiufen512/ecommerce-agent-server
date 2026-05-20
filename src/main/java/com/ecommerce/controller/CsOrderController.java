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
 * Customer Order Controller
 */
@Api(tags = "Order Management")
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Validated
public class CsOrderController {

    private final CsOrderService orderService;

    /** Create Order */
    @ApiOperation("Create Order")
    @PostMapping
    public Response create(@Valid @RequestBody CsOrderCreateVO vo) {
        CsOrder order = new CsOrder();
        BeanUtils.copyProperties(vo, order);
        boolean success = orderService.save(order);
        return success ? Response.success() : Response.fail("Failed to create order");
    }

    /** Query Order by Order Number */
    @ApiOperation("Query Order by Order Number")
    @GetMapping("/{orderNo}")
    public Response getByOrderNo(@ApiParam("Order Number") @PathVariable String orderNo) {
        CsOrder order = orderService.getByOrderNoOrThrow(orderNo);
        CsOrderResponseVO vo = new CsOrderResponseVO();
        BeanUtils.copyProperties(order, vo);
        return Response.success(vo);
    }

    /** Query Order by Primary Key ID */
    @ApiOperation("Query Order by Primary Key ID")
    @GetMapping("/id/{id}")
    public Response getById(@ApiParam("Primary Key ID") @PathVariable Long id) {
        CsOrder order = orderService.getById(id);
        if (order != null) {
            CsOrderResponseVO vo = new CsOrderResponseVO();
            BeanUtils.copyProperties(order, vo);
            return Response.success(vo);
        }
        return Response.notFound("Order not found: " + id);
    }

    /** Query Order List by User ID */
    @ApiOperation("Query Order List by User ID")
    @GetMapping("/user/{userId}")
    public Response listByUserId(@ApiParam("User ID") @PathVariable String userId) {
        List<CsOrder> list = orderService.list(
                new LambdaQueryWrapper<CsOrder>().eq(CsOrder::getUserId, userId));
        List<CsOrderResponseVO> voList = list.stream().map(order -> {
            CsOrderResponseVO vo = new CsOrderResponseVO();
            BeanUtils.copyProperties(order, vo);
            return vo;
        }).collect(java.util.stream.Collectors.toList());
        return Response.success(voList);
    }

    /** Paginated Query Order List */
    @ApiOperation("Paginated Query Order List")
    @GetMapping("/page")
    public PageResponse page(@ApiParam("Current Page") @RequestParam(defaultValue = "1") Integer current,
                                      @ApiParam("Page Size") @RequestParam(defaultValue = "10") Integer size,
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

    /** Update Order */
    @ApiOperation("Update Order")
    @PutMapping
    public Response update(@Valid @RequestBody CsOrderUpdateVO vo) {
        CsOrder order = new CsOrder();
        BeanUtils.copyProperties(vo, order);
        boolean success = orderService.updateById(order);
        return success ? Response.success() : Response.fail("Failed to update order");
    }

    /** Delete Order */
    @ApiOperation("Delete Order")
    @DeleteMapping("/id/{id}")
    public Response delete(@ApiParam("Primary Key ID") @PathVariable Long id) {
        boolean success = orderService.removeById(id);
        return success ? Response.success() : Response.fail("Failed to delete order");
    }
}