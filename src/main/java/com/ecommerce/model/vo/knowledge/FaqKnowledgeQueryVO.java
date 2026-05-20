package com.ecommerce.model.vo.knowledge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * FAQ Query Request VO
 */
@Data
@ApiModel("FAQ Query Request")
public class FaqKnowledgeQueryVO {

    @ApiModelProperty(value = "Standard Question (fuzzy search supported)", example = "refund")
    private String question;

    @ApiModelProperty(value = "Category: refund/logistics/product/complaint/other", example = "refund")
    private String category;

    @ApiModelProperty(value = "Minimum Priority", example = "50")
    private Integer minPriority;
}