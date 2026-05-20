package com.ecommerce.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecommerce.entity.CsRefund;

import java.math.BigDecimal;

/**
 * Refund Service Interface
 */
public interface CsRefundService extends IService<CsRefund> {

    /** Check if order meets refund conditions */
    boolean checkRefundEligibility(String orderNo, String userId);

    /** Initiate refund */
    CsRefund applyRefund(String orderNo, String userId, String reason);

    /** Approve refund */
    CsRefund approveRefund(String refundNo, boolean approved, String rejectReason);

    /** Complete refund */
    CsRefund completeRefund(String refundNo);
}
