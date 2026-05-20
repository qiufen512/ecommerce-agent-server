package com.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * FAQ knowledge base entity (Milvus fallback / admin management)
 */
@Data
@TableName("faq_knowledge")
public class FaqKnowledge {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String question;

    private String answer;

    private String category;

    private Integer priority;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
