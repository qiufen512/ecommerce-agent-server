package com.ecommerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.entity.CsOrder;
import com.ecommerce.exception.BusinessException;
import com.ecommerce.mapper.CsOrderMapper;
import com.ecommerce.service.CsOrderService;
import org.springframework.stereotype.Service;

/**
 * 客户订单服务实现
 */
@Service
public class CsOrderServiceImpl extends ServiceImpl<CsOrderMapper, CsOrder> implements CsOrderService {

    @Override
    public CsOrder getByOrderNoOrThrow(String orderNo) {
        CsOrder order = getOne(
                new LambdaQueryWrapper<CsOrder>().eq(CsOrder::getOrderNo, orderNo));
        if (order == null) {
            throw new BusinessException(404, "订单不存在: " + orderNo);
        }
        return order;
    }
}
