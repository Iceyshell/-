package com.yanagi.service;

import com.yanagi.entity.SysMenu;
import com.yanagi.entity.SysUser;
import com.yanagi.utils.Result;
import com.yanagi.vo.ChangePwdVo;
import com.yanagi.vo.LoginVo;
import com.yanagi.vo.UserQueryVo;

import java.util.List;
import java.util.Map;

/**
 * @author yanagi
 * @description 用户操作逻辑接口
 * @date 2024-04-20 15:35
 */

public interface SysUserService {

    Result findAll();

    /**
     * 登录接口
     * @param loginVo
     * @return
     */
    Result login(LoginVo loginVo);

    SysUser findUserByUsername(String username);

    /**
     * 前端加载左侧菜单时调用，根据用户Id获取菜单列表
     * @param userId
     * @return
     */
    List<SysMenu> findMenus(Long userId);

    /**
     * 根据用户Id和菜单Id获取子菜单列表
     * @param userId
     * @return
     */
    List<SysMenu> findChildrenMenu(Long id, Long userId);

    /**
     * 根据用户Id获取权限列表
     * @param userId
     * @return
     */
    Map<String, List<String>> findPermissions(Long userId);


    /**
     * 分页查询
     * @param queryInfo
     * @return
     */
    Result findPage(UserQueryVo queryInfo);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    Result insert(SysUser user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    Result update(SysUser user);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 根据邮箱修改密码
     * @param email
     * @param password
     */
    void updatePwdByMail(String email, String password);

    /**
     * 根据openid更新用户信息
     * @param user
     * @return
     */
    Result updateByopenId(SysUser user);


    /**
     * 修改密码
     * @param changePwdVo
     * @return
     */
    Result changePwd(ChangePwdVo changePwdVo);

}
