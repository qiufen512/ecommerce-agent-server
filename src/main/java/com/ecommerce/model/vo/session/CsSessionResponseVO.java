package com.ecommerce.model.vo.session;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("会话响应")
public class CsSessionResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("会话唯一ID（UUID）")
    private String sessionId;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("入口: web/app/mini")
    private String channel;

    @ApiModelProperty("状态: 0=进行中 1=已结束 2=人工接管")
    private Integer status;

    @ApiModelProperty("接管的人工客服ID")
    private String agentId;

    @ApiModelProperty("会话开始时间")
    private LocalDateTime createdAt;

    @ApiModelProperty("最后更新时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty("会话关闭时间")
    private LocalDateTime closedAt;
}