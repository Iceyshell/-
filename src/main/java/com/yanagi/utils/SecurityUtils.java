package com.yanagi.utils;

import com.yanagi.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * @author yanagi
 * @description 获取当前登录用户的基本信息
 * @date 2024-04-25 16:36
 */

public class SecurityUtils {

    /**
     * 从Security主体信息中获取用户信息
     * @return
     */
    public static SysUser getUser() {
        // 获取当前认证对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 获取当前认证对象的权限集合
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //System.out.println(authorities);

        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setPassword(null);
        user.setName(user.getUsername());

        return user;
    }

    /**
     * 在security中获取用户名
     * @return
     */
    public static String getUsername() {
        return getUser().getUsername();
    }

    /**
     * 在security中获取用户ID
     * @return
     */
    public static Long getUserId() {
        return getUser().getId();
    }

    /**
     * 在security中获取用户小程序ID
     * @return
     */
    public static String getOpenId() {
        return getUser().getOpenId();
    }
}
