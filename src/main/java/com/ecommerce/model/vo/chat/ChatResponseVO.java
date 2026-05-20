package com.ecommerce.model.vo.chat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Chat Response")
public class ChatResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("AI Reply Content")
    private String reply;

    @ApiModelProperty("Intent: refund/logistics/product/complaint/other")
    private String intent;

    @ApiModelProperty("Confidence (0.0~1.0)")
    private Float confidence;

    @ApiModelProperty("Session ID")
    private String sessionId;

    @ApiModelProperty("Whether human intervention is needed")
    private Boolean humanFlag;

    @ApiModelProperty("Compliance Check Result")
    private ComplianceCheckVO complianceCheck;
}