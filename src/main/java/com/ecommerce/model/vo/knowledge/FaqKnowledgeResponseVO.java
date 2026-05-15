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
@ApiModel("FAQ响应")
public class FaqKnowledgeResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("标准问题")
    private String question;

    @ApiModelProperty("标准答案")
    private String answer;

    @ApiModelProperty("分类: refund/logistics/product/complaint/other")
    private String category;

    @ApiModelProperty("优先级，数字越大越优先")
    private Integer priority;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedAt;
}