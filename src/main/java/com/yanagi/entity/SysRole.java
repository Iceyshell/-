package com.yanagi.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author yanagi
 * @description 系统角色
 * @date 2024-04-20 23:44
 */
@Data
public class SysRole {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "角色标签")
    private String label;

    @ApiModelProperty(value = "角色值")
    private String code;

    @ApiModelProperty(value = "显示状态(0不显示、1显示)")
    private boolean status;

    @ApiModelProperty(value = "菜单列表")
    private List<SysMenu> menus;

    @ApiModelProperty(value = "菜单列表id")
    private List<Long> menuIds;

    @ApiModelProperty(value = "权限列表")
    private List<SysPermission> permissions;

    @ApiModelProperty(value = "权限列表id")
    private List<Long> permissionIds;
}
