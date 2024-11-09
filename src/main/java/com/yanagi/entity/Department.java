package com.yanagi.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yanagi
 * @description 部门
 * @date 2024-06-25 13:59
 */
@Data
public class Department {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "公司id")
    private Long company_id;

}
