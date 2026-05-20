package com.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * CS ticket entity
 */
@Data
@TableName("cs_ticket")
public class CsTicket {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long ticketId;

    private String sessionId;

    private String intent;

    private String orderNo;

    private Integer status;

    private String agentReply;

    private Integer humanFlag;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}