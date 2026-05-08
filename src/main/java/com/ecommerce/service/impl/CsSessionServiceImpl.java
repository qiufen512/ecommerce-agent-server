package com.ecommerce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.entity.CsSession;
import com.ecommerce.mapper.CsSessionMapper;
import com.ecommerce.service.CsSessionService;
import org.springframework.stereotype.Service;

/**
 * 客服会话服务实现
 */
@Service
public class CsSessionServiceImpl extends ServiceImpl<CsSessionMapper, CsSession> implements CsSessionService {
}
