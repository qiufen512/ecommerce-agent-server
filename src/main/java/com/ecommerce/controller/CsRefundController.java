package com.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ecommerce.common.response.Response;
import com.ecommerce.entity.CsRefund;
import com.ecommerce.model.vo.refund.CsRefundApplyVO;
import com.ecommerce.model.vo.refund.CsRefundApproveVO;
import com.ecommerce.model.vo.refund.CsRefundResponseVO;
import com.ecommerce.service.CsRefundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

/**
 * 退款控制器
 */
@Api(tags = "退款管理")
@RestController
@RequestMapping("/api/refund")
@RequiredArgsConstructor
@Validated
public class CsRefundController {

    private final CsRefundService refundService;

    /** 检查退款条件 */
    @ApiOperation("检查退款资格")
    @GetMapping("/check")
    public Response checkRefundEligibility(@ApiParam("订单号") @RequestParam String orderNo,
                                           @ApiParam("用户ID") @RequestParam String userId) {
        boolean eligible = refundService.checkRefundEligibility(orderNo, userId);
        return Response.success(eligible);
    }

    /** 发起退款 */
    @ApiOperation("发起退款申请")
    @PostMapping("/apply")
    public Response applyRefund(@Valid @RequestBody CsRefundApplyVO vo) {
        CsRefund refund = refundService.applyRefund(vo.getOrderNo(), vo.getUserId(), vo.getReason());
        CsRefundResponseVO responseVO = new CsRefundResponseVO();
        BeanUtils.copyProperties(refund, responseVO);
        return Response.success(responseVO);
    }

    /** 审核退款 */
    @ApiOperation("审核退款")
    @PostMapping("/approve")
    public Response approveRefund(@Valid @RequestBody CsRefundApproveVO vo) {
        CsRefund refund = refundService.approveRefund(vo.getRefundNo(), vo.getApproved(), vo.getRejectReason());
        CsRefundResponseVO responseVO = new CsRefundResponseVO();
        BeanUtils.copyProperties(refund, responseVO);
        return Response.success(responseVO);
    }

    /** 完成退款 */
    @ApiOperation("完成退款")
    @PostMapping("/complete")
    public Response completeRefund(@ApiParam("退款单号") @RequestParam String refundNo) {
        CsRefund refund = refundService.completeRefund(refundNo);
        CsRefundResponseVO responseVO = new CsRefundResponseVO();
        BeanUtils.copyProperties(refund, responseVO);
        return Response.success(responseVO);
    }

    /** 查询退款单 */
    @ApiOperation("按退款单号查询")
    @GetMapping("/{refundNo}")
    public Response getByRefundNo(@ApiParam("退款单号") @PathVariable String refundNo) {
        CsRefund refund = refundService.getOne(
                new LambdaQueryWrapper<CsRefund>().eq(CsRefund::getRefundNo, refundNo));
        if (refund != null) {
            CsRefundResponseVO responseVO = new CsRefundResponseVO();
            BeanUtils.copyProperties(refund, responseVO);
            return Response.success(responseVO);
        }
        return Response.notFound("退款单不存在: " + refundNo);
    }

    /** 按订单号查询退款列表 */
    @ApiOperation("按订单号查询退款列表")
    @GetMapping("/order/{orderNo}")
    public Response listByOrderNo(@ApiParam("订单号") @PathVariable String orderNo) {
        List<CsRefund> list = refundService.list(
                new LambdaQueryWrapper<CsRefund>()
                        .eq(CsRefund::getOrderNo, orderNo)
                        .orderByDesc(CsRefund::getCreatedAt));
        List<CsRefundResponseVO> voList = list.stream().map(refund -> {
            CsRefundResponseVO vo = new CsRefundResponseVO();
            BeanUtils.copyProperties(refund, vo);
            return vo;
        }).collect(java.util.stream.Collectors.toList());
        return Response.success(voList);
    }
}
