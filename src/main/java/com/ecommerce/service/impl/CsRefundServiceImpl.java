package com.ecommerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.entity.CsOrder;
import com.ecommerce.entity.CsRefund;
import com.ecommerce.exception.BusinessException;
import com.ecommerce.mapper.CsRefundMapper;
import com.ecommerce.service.CsOrderService;
import com.ecommerce.service.CsRefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * Refund Service Implementation
 */
@Service
@RequiredArgsConstructor
public class CsRefundServiceImpl extends ServiceImpl<CsRefundMapper, CsRefund> implements CsRefundService {

    private final CsOrderService orderService;

    private static final long REFUND_DAYS_LIMIT = 7;

    @Override
    public boolean checkRefundEligibility(String orderNo, String userId) {
        CsOrder order = orderService.getByOrderNoOrThrow(orderNo);

        if (!order.getUserId().equals(userId)) {
            throw new BusinessException("No permission to operate on this order");
        }

        String orderStatus = order.getStatus();
        if ("pending".equals(orderStatus) || "cancelled".equals(orderStatus)) {
            throw new BusinessException("Order status does not support refund");
        }
        if ("refunded".equals(orderStatus) || "refunding".equals(orderStatus)) {
            throw new BusinessException("Order is already in refund process");
        }

        LocalDateTime createdTime = order.getCreatedAt();
        if (createdTime != null && ChronoUnit.DAYS.between(createdTime, LocalDateTime.now()) > REFUND_DAYS_LIMIT) {
            throw new BusinessException("Exceeded refund time limit (" + REFUND_DAYS_LIMIT + " days)");
        }

        LambdaQueryWrapper<CsRefund> existingRefund = new LambdaQueryWrapper<CsRefund>()
                .eq(CsRefund::getOrderNo, orderNo)
                .in(CsRefund::getStatus, 0, 1, 3);
        if (count(existingRefund) > 0) {
            throw new BusinessException("Order already has a pending refund application");
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CsRefund applyRefund(String orderNo, String userId, String reason) {
        CsOrder order = orderService.getByOrderNoOrThrow(orderNo);

        if (!order.getUserId().equals(userId)) {
            throw new BusinessException("No permission to operate on this order");
        }

        String orderStatus = order.getStatus();
        if ("pending".equals(orderStatus) || "cancelled".equals(orderStatus)) {
            throw new BusinessException("Order status does not support refund");
        }
        if ("refunded".equals(orderStatus) || "refunding".equals(orderStatus)) {
            throw new BusinessException("Order is already in refund process");
        }

        LocalDateTime createdTime = order.getCreatedAt();
        if (createdTime != null && ChronoUnit.DAYS.between(createdTime, LocalDateTime.now()) > REFUND_DAYS_LIMIT) {
            throw new BusinessException("Exceeded refund time limit (" + REFUND_DAYS_LIMIT + " days)");
        }

        LambdaQueryWrapper<CsRefund> existingRefund = new LambdaQueryWrapper<CsRefund>()
                .eq(CsRefund::getOrderNo, orderNo)
                .in(CsRefund::getStatus, 0, 1, 3);
        if (count(existingRefund) > 0) {
            throw new BusinessException("Order already has a pending refund application");
        }

        String refundNo = "REF" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        CsRefund refund = new CsRefund();
        refund.setRefundNo(refundNo);
        refund.setOrderNo(orderNo);
        refund.setUserId(userId);
        refund.setRefundAmount(order.getAmount());
        refund.setStatus(0);
        refund.setReason(reason);

        save(refund);

        order.setStatus("refunding");
        orderService.updateById(order);

        return refund;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CsRefund approveRefund(String refundNo, boolean approved, String rejectReason) {
        CsRefund refund = getByRefundNoOrThrow(refundNo);

        if (refund.getStatus() != 0) {
            throw new BusinessException("Refund status is incorrect, cannot approve");
        }

        if (approved) {
            refund.setStatus(1);
            refund.setApprovedAt(LocalDateTime.now());
        } else {
            refund.setStatus(2);
            refund.setRejectReason(rejectReason);
            CsOrder order = orderService.getByOrderNoOrThrow(refund.getOrderNo());
            if ("refunding".equals(order.getStatus())) {
                order.setStatus("delivered");
                orderService.updateById(order);
            }
        }
        updateById(refund);
        return refund;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CsRefund completeRefund(String refundNo) {
        CsRefund refund = getByRefundNoOrThrow(refundNo);

        if (refund.getStatus() != 1) {
            throw new BusinessException("Refund has not been approved, cannot complete refund");
        }

        refund.setStatus(4);
        refund.setCompletedAt(LocalDateTime.now());
        updateById(refund);

        CsOrder order = orderService.getByOrderNoOrThrow(refund.getOrderNo());
        order.setStatus("refunded");
        orderService.updateById(order);

        return refund;
    }

    private CsRefund getByRefundNoOrThrow(String refundNo) {
        CsRefund refund = getOne(
                new LambdaQueryWrapper<CsRefund>().eq(CsRefund::getRefundNo, refundNo));
        if (refund == null) {
            throw new BusinessException(404, "Refund not found: " + refundNo);
        }
        return refund;
    }
}
