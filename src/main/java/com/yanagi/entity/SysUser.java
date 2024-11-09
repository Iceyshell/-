package com.yanagi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yanagi.utils.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author yanagi
 * @description 用户实体
 * @date 2024-04-20 15:26
 */
@Data
public class SysUser implements UserDetails {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "部门id")
    private Long departmentId;

//    @ApiModelProperty(value = "公司id")
//    private Long companyId;
    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "前端展示的用户名")
    private String name;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "微信唯一ID")
    private String openId;
    @ApiModelProperty(value = "当前状态")
    private boolean status;

    @ApiModelProperty(value = "是否是管理员")
    private boolean admin;

    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

    @ApiModelProperty(value = "用户邮箱")
    private String email;
    @ApiModelProperty(value = "角色信息")
    private List<SysRole> roles;

    @ApiModelProperty(value = "角色id列表")
    private List<Long> roleIds;

    @ApiModelProperty(value = "用户对应的菜单列表")
    private List<SysMenu> menus;

    @ApiModelProperty(value = "用户对应的权限数据")
    private List<SysPermission> permissions;

    private boolean enabled;

    /**
     * 权限数据
     *
     * @return
     * @JsonIgnore 在返回的数据中，将该方法对应的属性数据给排除
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        roles.stream().filter(role -> StringUtils.isNotEmpty(role.getCode()))
                .forEach(role -> list.add(new SimpleGrantedAuthority("ROLE_" + role.getCode())));
        permissions.stream().filter(permission -> StringUtils.isNotEmpty(permission.getCode()))
                .forEach(permission -> list.add(new SimpleGrantedAuthority(permission.getCode())));
        return list;
    }

    /**
     * 用户名
     *
     * @return
     */
    @Override
    @JsonIgnore
    public String getUsername() {
        return userName;
    }

    /**
     * 账号是否过期
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * 账号是否被锁定
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 是否被禁用
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return status;
    }
}
