package com.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客服会话实体
 */
@Data
@ApiModel(description = "客服会话信息")
@TableName("cs_session")
public class CsSession {

    @ApiModelProperty(value = "主键ID", example = "1")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "会话唯一ID（UUID）", example = "session_001")
    private String sessionId;

    @ApiModelProperty(value = "用户ID", example = "user123")
    private String userId;

    @ApiModelProperty(value = "入口：web/app/mini", example = "web")
    private String channel;

    @ApiModelProperty(value = "状态：0=进行中 1=已结束 2=人工接管", example = "0")
    private Integer status;

    @ApiModelProperty(value = "接管的人工客服ID", example = "admin_001")
    private String agentId;

    @ApiModelProperty(value = "会话开始时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "会话关闭时间")
    private LocalDateTime closedAt;
}
