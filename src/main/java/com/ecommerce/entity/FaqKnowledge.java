package com.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * FAQ知识库实体（Milvus降级备用/后台管理用）
 */
@Data
@TableName("faq_knowledge")
public class FaqKnowledge {

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 标准问题 */
    private String question;

    /** 标准答案 */
    private String answer;

    /** 问题分类：refund/logistics/product/complaint/other */
    private String category;

    /** 优先级，数字越大越优先展示 */
    private Integer priority;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
