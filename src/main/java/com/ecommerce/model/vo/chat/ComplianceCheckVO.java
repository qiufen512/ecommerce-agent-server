package com.ecommerce.model.vo.chat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Compliance Check Result VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Compliance Check Result")
public class ComplianceCheckVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Whether compliance check passed")
    private Boolean pass;

    @ApiModelProperty("Risk item list")
    private List<String> risks;
}