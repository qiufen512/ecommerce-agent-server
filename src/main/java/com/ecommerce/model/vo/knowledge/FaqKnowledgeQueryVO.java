package com.ecommerce.model.vo.knowledge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * FAQ查询请求 VO
 */
@Data
@ApiModel("FAQ查询请求")
public class FaqKnowledgeQueryVO {

    @ApiModelProperty(value = "标准问题（支持模糊查询）", example = "退款")
    private String question;

    @ApiModelProperty(value = "问题分类：refund/logistics/product/complaint/other", example = "refund")
    private String category;

    @ApiModelProperty(value = "优先级（大于等于）", example = "50")
    private Integer minPriority;
}