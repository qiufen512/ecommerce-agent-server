package com.ecommerce.model.vo.knowledge;

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
@ApiModel("FAQ Response")
public class FaqKnowledgeResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Primary Key ID")
    private Long id;

    @ApiModelProperty("Standard Question")
    private String question;

    @ApiModelProperty("Standard Answer")
    private String answer;

    @ApiModelProperty("Category: refund/logistics/product/complaint/other")
    private String category;

    @ApiModelProperty("Priority (higher value = higher priority)")
    private Integer priority;

    @ApiModelProperty("Created At")
    private LocalDateTime createdAt;

    @ApiModelProperty("Updated At")
    private LocalDateTime updatedAt;
}