package com.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ecommerce.common.Result;
import com.ecommerce.entity.CsRefund;
import com.ecommerce.service.CsRefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 退款控制器
 */
@RestController
@RequestMapping("/api/refund")
@RequiredArgsConstructor
public class CsRefundController {

    private final CsRefundService refundService;

    /** 检查退款条件 */
    @GetMapping("/check")
    public Result<?> checkRefundEligibility(@RequestParam String orderNo, @RequestParam String userId) {
        boolean eligible = refundService.checkRefundEligibility(orderNo, userId);
        return Result.success(eligible);
    }

    /** 发起退款 */
    @PostMapping("/apply")
    public Result<CsRefund> applyRefund(@RequestParam String orderNo,
                                       @RequestParam String userId,
                                       @RequestParam(required = false) String reason) {
        CsRefund refund = refundService.applyRefund(orderNo, userId, reason);
        return Result.success(refund);
    }

    /** 审核退款 */
    @PostMapping("/approve")
    public Result<CsRefund> approveRefund(@RequestParam String refundNo,
                                         @RequestParam boolean approved,
                                         @RequestParam(required = false) String rejectReason) {
        CsRefund refund = refundService.approveRefund(refundNo, approved, rejectReason);
        return Result.success(refund);
    }

    /** 完成退款 */
    @PostMapping("/complete")
    public Result<CsRefund> completeRefund(@RequestParam String refundNo) {
        CsRefund refund = refundService.completeRefund(refundNo);
        return Result.success(refund);
    }

    /** 查询退款单 */
    @GetMapping("/{refundNo}")
    public Result<?> getByRefundNo(@PathVariable String refundNo) {
        CsRefund refund = refundService.getOne(
                new LambdaQueryWrapper<CsRefund>().eq(CsRefund::getRefundNo, refundNo));
        if (refund != null) {
            return Result.success(refund);
        }
        return Result.notFound("退款单不存在: " + refundNo);
    }

    /** 按订单号查询退款列表 */
    @GetMapping("/order/{orderNo}")
    public Result<?> listByOrderNo(@PathVariable String orderNo) {
        List<CsRefund> list = refundService.list(
                new LambdaQueryWrapper<CsRefund>()
                        .eq(CsRefund::getOrderNo, orderNo)
                        .orderByDesc(CsRefund::getCreatedAt));
        return Result.success(list);
    }
}
