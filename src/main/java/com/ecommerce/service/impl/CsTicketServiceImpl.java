package com.ecommerce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.entity.CsTicket;
import com.ecommerce.mapper.CsTicketMapper;
import com.ecommerce.service.CsTicketService;
import org.springframework.stereotype.Service;

/**
 * Ticket Service Implementation
 */
@Service
public class CsTicketServiceImpl extends ServiceImpl<CsTicketMapper, CsTicket> implements CsTicketService {
}