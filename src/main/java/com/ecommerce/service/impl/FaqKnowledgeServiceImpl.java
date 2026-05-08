package com.ecommerce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.entity.FaqKnowledge;
import com.ecommerce.mapper.FaqKnowledgeMapper;
import com.ecommerce.service.FaqKnowledgeService;
import org.springframework.stereotype.Service;

/**
 * FAQ知识库服务实现
 */
@Service
public class FaqKnowledgeServiceImpl extends ServiceImpl<FaqKnowledgeMapper, FaqKnowledge> implements FaqKnowledgeService {
}
