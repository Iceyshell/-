package com.yanagi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author yanagi
 * @description 菜单
 * @date 2024-04-20 23:47
 */
@Data
public class SysMenu {

//    private long id;
//    private String path;
//    private String name;
//    private String redirect;
//    private String component;
//    private String icon;
//    private String title;
//    private String activeMenu;
//    private String isLink;
//    private boolean isHide;
//    private boolean isFull;
//    private boolean isAffix;
//    private boolean isKeepAlive;
//    private long parentId;
//
//    private List<SysMenu> children;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "前端路由")
    private String path;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "重定向")
    private String redirect;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "菜单标题")
    private String title;

    @ApiModelProperty(value = "前端组件")
    private String component;

    @ApiModelProperty(value = "显示状态(0不显示、1显示)")
    private boolean status;

    /**
     *  - @JsonIgnore 不被序列化 前端就取不到
     */
    @ApiModelProperty(value = "父级菜单")
    private Long parentId;

    @ApiModelProperty(value = "父级标题")
    private String parentTitle;

    @ApiModelProperty(value = "meta")
    private Meta meta;

    @ApiModelProperty(value = "子菜单")
    private List<SysMenu> children;
}
