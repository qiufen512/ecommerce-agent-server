package com.ecommerce.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecommerce.entity.CsRefund;

import java.math.BigDecimal;

/**
 * 退款服务接口
 */
public interface CsRefundService extends IService<CsRefund> {

    /** 检查订单是否满足退款条件 */
    boolean checkRefundEligibility(String orderNo, String userId);

    /** 发起退款 */
    CsRefund applyRefund(String orderNo, String userId, String reason);

    /** 审核退款 */
    CsRefund approveRefund(String refundNo, boolean approved, String rejectReason);

    /** 完成退款 */
    CsRefund completeRefund(String refundNo);
}
