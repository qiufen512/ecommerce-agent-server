package com.ecommerce.model.vo.knowledge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Update FAQ Request VO
 */
@Data
@ApiModel("Update FAQ Request")
public class FaqKnowledgeUpdateVO {

    @ApiModelProperty(value = "Primary Key ID", required = true, example = "1")
    @NotNull(message = "Primary key ID must not be empty")
    private Long id;

    @ApiModelProperty(value = "Standard Question", example = "How to apply for a refund?")
    private String question;

    @ApiModelProperty(value = "Standard Answer", example = "Click 'Apply Refund' on the order details page...")
    private String answer;

    @ApiModelProperty(value = "Category: refund/logistics/product/complaint/other", example = "refund")
    private String category;

    @ApiModelProperty(value = "Priority (higher value = higher display priority)", example = "100")
    private Integer priority;
}