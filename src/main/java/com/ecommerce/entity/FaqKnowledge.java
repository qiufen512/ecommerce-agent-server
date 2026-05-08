package com.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * FAQ知识库实体（Milvus降级备用/后台管理用）
 */
@Data
@ApiModel(description = "FAQ知识库条目")
@TableName("faq_knowledge")
public class FaqKnowledge {

    @ApiModelProperty(value = "主键ID", example = "1")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标准问题", required = true, example = "如何申请退款？")
    private String question;

    @ApiModelProperty(value = "标准答案", required = true, example = "在订单详情页点击申请退款...")
    private String answer;

    @ApiModelProperty(value = "问题分类：refund/logistics/product/complaint/other", example = "refund")
    private String category;

    @ApiModelProperty(value = "优先级，数字越大越优先展示", example = "100")
    private Integer priority;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
