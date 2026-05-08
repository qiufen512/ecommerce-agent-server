package com.ecommerce.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecommerce.entity.CsTicket;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工单Mapper接口
 */
@Mapper
public interface CsTicketMapper extends BaseMapper<CsTicket> {
}