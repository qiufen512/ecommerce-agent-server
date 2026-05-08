package com.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客服会话实体
 */
@Data
@TableName("cs_session")
public class CsSession {

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 会话唯一ID（UUID） */
    private String sessionId;

    /** 用户ID */
    private String userId;

    /** 入口：web/app/mini */
    private String channel;

    /** 0=进行中 1=已结束 2=人工接管 */
    private Integer status;

    /** 接管的人工客服ID */
    private String agentId;

    /** 会话开始时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /** 最后更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    /** 会话关闭时间 */
    private LocalDateTime closedAt;
}
