package com.ecommerce.model.vo.knowledge;

import com.ecommerce.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * Create FAQ Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("Create FAQ Request")
public class FaqKnowledgeCreateVO extends BaseVO {

    @ApiModelProperty(value = "Standard Question", required = true, example = "How to apply for a refund?")
    @NotBlank(message = "Question must not be empty")
    private String question;

    @ApiModelProperty(value = "Standard Answer", required = true, example = "Click 'Apply Refund' on the order details page...")
    @NotBlank(message = "Answer must not be empty")
    private String answer;

    @ApiModelProperty(value = "Category: refund/logistics/product/complaint/other", example = "refund")
    private String category;

    @ApiModelProperty(value = "Priority (higher value = higher display priority)", example = "100")
    private Integer priority;
}