package com.ecommerce.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Base VO
 * Common fields for all VOs
 */
@Data
@ApiModel("Base VO")
public class BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Created At */
    @ApiModelProperty("Created At")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /** Updated At */
    @ApiModelProperty("Updated At")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}