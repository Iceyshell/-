package com.yanagi.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-04-20 23:53
 */
@Data
public class SysPermission {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "数据权限标签名称")
    private String label;

    @ApiModelProperty(value = "数据权限标签值")
    private String code;

    @ApiModelProperty(value = "显示状态(0不显示、1显示)")
    private boolean status;

}
