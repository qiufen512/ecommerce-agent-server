package com.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * CS session entity
 */
@Data
@TableName("cs_session")
public class CsSession {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String sessionId;

    private String userId;

    private String channel;

    private Integer status;

    private String agentId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    private LocalDateTime closedAt;
}
