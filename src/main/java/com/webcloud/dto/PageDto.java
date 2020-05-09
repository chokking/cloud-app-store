package com.webcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 */
@Data
public class PageDto {
    @ApiModelProperty(value = "页码", required = true)
    @NotNull(message = "页码不能为空")
    private Integer page;
    @ApiModelProperty(value = "条数", required = true)
    @NotNull(message = "条数不能为空")
    private Integer limit;
}
