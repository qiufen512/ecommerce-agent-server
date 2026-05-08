package com.ecommerce.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecommerce.entity.CsOrder;

/**
 * 客户订单服务接口
 */
public interface CsOrderService extends IService<CsOrder> {

    /** 按订单号查询（订单不存在抛异常） */
    CsOrder getByOrderNoOrThrow(String orderNo);
}
