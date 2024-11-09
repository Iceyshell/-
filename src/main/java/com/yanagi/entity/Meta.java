package com.yanagi.entity;

import lombok.Data;

/**
 * @author yanagi
 * @description 菜单里的meta，在查找菜单时需要重新赋值
 * @date 2024-04-20 23:59
 */
@Data
public class Meta {
    private String icon;
    private String title;
    private String isLink;
    private boolean isHide;
    private boolean isFull;
    private boolean isAffix;
    private boolean isKeepAlive;

    public Meta() {
    }

    public Meta(String icon, String title) {
        this.icon = icon;
        this.title = title;
        this.isLink = "";
        this.isHide = false;
        this.isFull = false;
        this.isAffix = false;
        this.isKeepAlive = true;
    }
}
