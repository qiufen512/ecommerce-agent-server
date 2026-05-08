package com.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客服工单实体
 */
@Data
@TableName("cs_ticket")
public class CsTicket {

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 工单业务ID */
    private Long ticketId;

    /** 关联会话ID */
    private String sessionId;

    /** 意图类型：refund/logistic/product/complaint */
    private String intent;

    /** 关联订单号 */
    private String orderNo;

    /** 状态：0=待处理 1=处理中 2=已完成 3=已关闭 */
    private Integer status;

    /** AI回复摘要 */
    private String agentReply;

    /** 人工介入标识：0=AI处理 1=人工介入 */
    private Integer humanFlag;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /** 最后更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}