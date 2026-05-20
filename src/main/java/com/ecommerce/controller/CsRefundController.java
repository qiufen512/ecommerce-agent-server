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
 * Refund Controller
 */
@Api(tags = "Refund Management")
@RestController
@RequestMapping("/api/refund")
@RequiredArgsConstructor
@Validated
public class CsRefundController {

    private final CsRefundService refundService;

    /** Check Refund Eligibility */
    @ApiOperation("Check Refund Eligibility")
    @GetMapping("/check")
    public Response checkRefundEligibility(@ApiParam("Order Number") @RequestParam String orderNo,
                                           @ApiParam("User ID") @RequestParam String userId) {
        boolean eligible = refundService.checkRefundEligibility(orderNo, userId);
        return Response.success(eligible);
    }

    /** Initiate Refund */
    @ApiOperation("Apply for Refund")
    @PostMapping("/apply")
    public Response applyRefund(@Valid @RequestBody CsRefundApplyVO vo) {
        CsRefund refund = refundService.applyRefund(vo.getOrderNo(), vo.getUserId(), vo.getReason());
        CsRefundResponseVO responseVO = new CsRefundResponseVO();
        BeanUtils.copyProperties(refund, responseVO);
        return Response.success(responseVO);
    }

    /** Approve Refund */
    @ApiOperation("Approve Refund")
    @PostMapping("/approve")
    public Response approveRefund(@Valid @RequestBody CsRefundApproveVO vo) {
        CsRefund refund = refundService.approveRefund(vo.getRefundNo(), vo.getApproved(), vo.getRejectReason());
        CsRefundResponseVO responseVO = new CsRefundResponseVO();
        BeanUtils.copyProperties(refund, responseVO);
        return Response.success(responseVO);
    }

    /** Complete Refund */
    @ApiOperation("Complete Refund")
    @PostMapping("/complete")
    public Response completeRefund(@ApiParam("Refund Number") @RequestParam String refundNo) {
        CsRefund refund = refundService.completeRefund(refundNo);
        CsRefundResponseVO responseVO = new CsRefundResponseVO();
        BeanUtils.copyProperties(refund, responseVO);
        return Response.success(responseVO);
    }

    /** Query Refund */
    @ApiOperation("Query by Refund Number")
    @GetMapping("/{refundNo}")
    public Response getByRefundNo(@ApiParam("Refund Number") @PathVariable String refundNo) {
        CsRefund refund = refundService.getOne(
                new LambdaQueryWrapper<CsRefund>().eq(CsRefund::getRefundNo, refundNo));
        if (refund != null) {
            CsRefundResponseVO responseVO = new CsRefundResponseVO();
            BeanUtils.copyProperties(refund, responseVO);
            return Response.success(responseVO);
        }
        return Response.notFound("Refund not found: " + refundNo);
    }

    /** Query Refund List by Order Number */
    @ApiOperation("Query Refund List by Order Number")
    @GetMapping("/order/{orderNo}")
    public Response listByOrderNo(@ApiParam("Order Number") @PathVariable String orderNo) {
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
