package com.ecommerce.model.vo.chat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 合规检查结果 VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("合规检查结果")
public class ComplianceCheckVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("是否通过合规检查")
    private Boolean pass;

    @ApiModelProperty("风险项列表")
    private List<String> risks;
}