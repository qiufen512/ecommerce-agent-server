package com.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客服工单实体
 */
@Data
@ApiModel(description = "客服工单信息")
@TableName("cs_ticket")
public class CsTicket {

    @ApiModelProperty(value = "主键ID", example = "1")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "工单业务ID", example = "1001")
    private Long ticketId;

    @ApiModelProperty(value = "关联会话ID", example = "session_001")
    private String sessionId;

    @ApiModelProperty(value = "意图类型：refund/logistic/product/complaint", example = "refund")
    private String intent;

    @ApiModelProperty(value = "关联订单号", example = "TEST2025042201")
    private String orderNo;

    @ApiModelProperty(value = "状态：0=待处理 1=处理中 2=已完成 3=已关闭", example = "0")
    private Integer status;

    @ApiModelProperty(value = "AI回复摘要", example = "用户要求退款，已确认资格")
    private String agentReply;

    @ApiModelProperty(value = "人工介入标识：0=AI处理 1=人工介入", example = "0")
    private Integer humanFlag;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}