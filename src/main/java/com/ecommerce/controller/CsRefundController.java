package com.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ecommerce.common.Result;
import com.ecommerce.entity.CsRefund;
import com.ecommerce.model.vo.refund.CsRefundApplyVO;
import com.ecommerce.model.vo.refund.CsRefundApproveVO;
import com.ecommerce.service.CsRefundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
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
    public Result<?> checkRefundEligibility(@ApiParam("订单号") @RequestParam String orderNo,
                                           @ApiParam("用户ID") @RequestParam String userId) {
        boolean eligible = refundService.checkRefundEligibility(orderNo, userId);
        return Result.success(eligible);
    }

    /** 发起退款 */
    @ApiOperation("发起退款申请")
    @PostMapping("/apply")
    public Result<CsRefund> applyRefund(@Valid @RequestBody CsRefundApplyVO vo) {
        CsRefund refund = refundService.applyRefund(vo.getOrderNo(), vo.getUserId(), vo.getReason());
        return Result.success(refund);
    }

    /** 审核退款 */
    @ApiOperation("审核退款")
    @PostMapping("/approve")
    public Result<CsRefund> approveRefund(@Valid @RequestBody CsRefundApproveVO vo) {
        CsRefund refund = refundService.approveRefund(vo.getRefundNo(), vo.getApproved(), vo.getRejectReason());
        return Result.success(refund);
    }

    /** 完成退款 */
    @ApiOperation("完成退款")
    @PostMapping("/complete")
    public Result<CsRefund> completeRefund(@ApiParam("退款单号") @RequestParam String refundNo) {
        CsRefund refund = refundService.completeRefund(refundNo);
        return Result.success(refund);
    }

    /** 查询退款单 */
    @ApiOperation("按退款单号查询")
    @GetMapping("/{refundNo}")
    public Result<?> getByRefundNo(@ApiParam("退款单号") @PathVariable String refundNo) {
        CsRefund refund = refundService.getOne(
                new LambdaQueryWrapper<CsRefund>().eq(CsRefund::getRefundNo, refundNo));
        if (refund != null) {
            return Result.success(refund);
        }
        return Result.notFound("退款单不存在: " + refundNo);
    }

    /** 按订单号查询退款列表 */
    @ApiOperation("按订单号查询退款列表")
    @GetMapping("/order/{orderNo}")
    public Result<?> listByOrderNo(@ApiParam("订单号") @PathVariable String orderNo) {
        List<CsRefund> list = refundService.list(
                new LambdaQueryWrapper<CsRefund>()
                        .eq(CsRefund::getOrderNo, orderNo)
                        .orderByDesc(CsRefund::getCreatedAt));
        return Result.success(list);
    }
}
