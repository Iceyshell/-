package com.yanagi.mapper;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-04-20 15:25
 */

import com.github.pagehelper.Page;
import com.yanagi.entity.SysMenu;
import com.yanagi.entity.SysPermission;
import com.yanagi.entity.SysRole;
import com.yanagi.entity.SysUser;
import com.yanagi.vo.UserQueryVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SysUserMapper {
    // 根据id查询用户
    SysUser findByUsername(@Param("value") String username);

    SysUser findByEmail(@Param("email") String email);

    // 查询所有用户
    List<SysUser> findAll();

    List<SysRole> findRoles(@Param("userId") Long userId);

    List<SysMenu> findMenus(@Param("userId") Long userId);

    /**
     * 根据父级ID和用户ID查询子级菜单
     * @param id 父级ID
     * @param userId 用户ID
     * @return
     */
    List<SysMenu> findChildrenMenu(@Param("id") Long id, @Param("userId") Long userId);

    /**
     * 根据用户ID查询权限数据
     * @param userId
     * @return
     */
    List<SysPermission> findPermissions(@Param("userId") Long userId);

    // 用户增删改查部分
    //
    //
    /**
     * 分页查询用户信息
     * @param userQueryVo 用户查询信息
     * @return 返回分页数据
     */
    Page<SysUser> findPage(UserQueryVo userQueryVo);

    /**
     * 添加用户信息
     * @param user 用户信息
     */
    void insert(SysUser user);

    /**
     * 微信小程序进入添加信息
     * @param openid 微信小程序唯一标识
     */
    @Insert("insert into sys_user(open_id, status) values (#{openid}, 0)")
    void insertOpenid(@Param("openid") String openid);

    /**
     * 修改用户信息
     * @param user 用户信息
     */
    void update(SysUser user);

    /**
     * 删除用户
     * @param id 用户ID
     */
    void delete(Long id);

    /**
     * 根据用户信息中的角色列表，去添加用户的角色
     * @param userId
     * @param roleId
     */
    void insertUserRoles(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * 根据用户ID去删除角色列表
     * @param userId
     */
    void deleteRolesByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID查询用户的基本信息
     * @param id
     * @return
     */
    SysUser findById(Long id);

    /**
     * 根据邮件修改密码
     * @param email 邮件
     * @param password 新密码
     */
    @Update("update sys_user set `password` = #{password} where email = #{email}")
    void updatePwdByMail(@Param("email") String email, @Param("password") String password);

    /**
     * 根据openid更新用户信息
     * @param user
     */
    void updateByopenId(SysUser user);
}
