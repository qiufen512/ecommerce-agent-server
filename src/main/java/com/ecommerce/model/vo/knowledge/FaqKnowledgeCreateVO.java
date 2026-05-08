package com.ecommerce.model.vo.knowledge;

import com.ecommerce.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 创建FAQ请求 VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("创建FAQ请求")
public class FaqKnowledgeCreateVO extends BaseVO {

    @ApiModelProperty(value = "标准问题", required = true, example = "如何申请退款？")
    @NotBlank(message = "问题不能为空")
    private String question;

    @ApiModelProperty(value = "标准答案", required = true, example = "在订单详情页点击申请退款...")
    @NotBlank(message = "答案不能为空")
    private String answer;

    @ApiModelProperty(value = "问题分类：refund/logistics/product/complaint/other", example = "refund")
    private String category;

    @ApiModelProperty(value = "优先级，数字越大越优先展示", example = "100")
    private Integer priority;
}