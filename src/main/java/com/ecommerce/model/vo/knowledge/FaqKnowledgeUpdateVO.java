package com.ecommerce.model.vo.knowledge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 更新FAQ请求 VO
 */
@Data
@ApiModel("更新FAQ请求")
public class FaqKnowledgeUpdateVO {

    @ApiModelProperty(value = "主键ID", required = true, example = "1")
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @ApiModelProperty(value = "标准问题", example = "如何申请退款？")
    private String question;

    @ApiModelProperty(value = "标准答案", example = "在订单详情页点击申请退款...")
    private String answer;

    @ApiModelProperty(value = "问题分类：refund/logistics/product/complaint/other", example = "refund")
    private String category;

    @ApiModelProperty(value = "优先级，数字越大越优先展示", example = "100")
    private Integer priority;
}