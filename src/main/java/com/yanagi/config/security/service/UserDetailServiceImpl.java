package com.yanagi.config.security.service;


import com.yanagi.entity.SysUser;
import com.yanagi.mapper.SysUserMapper;
import com.yanagi.service.SysUserService;
import com.yanagi.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


/**
 * 实现UserDetailsService接口，实现自定义登陆逻辑
 * 重写loadUserByUsername方法
 *
 * @author yanagi
 */
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserService userService;

    @Autowired
    private RedisUtils redisUtil;


    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser user;
        if (redisUtil.haskey("userInfo_" + username)) {
            //缓存中存在用户信息，直接从redis中取
            user = (SysUser)redisUtil.getValue("userInfo_" + username);
            redisUtil.expire("userInfo_" + username, 5);
            log.info("从redis中获取到了登录信息");
        }
        else {
            user = userMapper.findByUsername(username);
            if (null == user) {
                throw new RuntimeException("用户名或密码错误");
            }
            if (user.isAdmin()) {
                //非管理员需要查询角色信息
                user.setRoles(userMapper.findRoles(null));
//            //获取菜单
//            List<SysMenu> menus = userService.findMenus(null);
//            user.setMenus(menus);
                //获取权限列表
                user.setPermissions(userMapper.findPermissions(null));
            }
            else{
                // 非管理员需要查询
                user.setRoles(userMapper.findRoles(user.getId()));
//            //获取菜单
//            List<SysMenu> menus = userService.findMenus(user.getId());
//            user.setMenus(menus);
                //获取权限列表
                user.setPermissions(userMapper.findPermissions(user.getId()));
            }
            // redis中存入用户信息
            redisUtil.setValueTime("userInfo_" + username, user, 5);
        }
        return user;
    }
}
