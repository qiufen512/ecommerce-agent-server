package com.ecommerce.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecommerce.entity.CsOrder;

/**
 * CS Order Service Interface
 */
public interface CsOrderService extends IService<CsOrder> {

    /** Query by order number (throws exception if order not found) */
    CsOrder getByOrderNoOrThrow(String orderNo);
}
