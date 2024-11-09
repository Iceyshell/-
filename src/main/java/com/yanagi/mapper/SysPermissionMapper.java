package com.yanagi.mapper;

import com.github.pagehelper.Page;
import com.yanagi.entity.SysPermission;
import com.yanagi.vo.PermissionQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yanagi
 * @description 权限mapper
 * @date 2024-05-01 0:47
 */

public interface SysPermissionMapper {

    /**
     * 添加权限信息
     * @param permission 权限数据
     */
    void insert(SysPermission permission);

    /**
     * 修改权限信息
     * @param permission 权限数据
     */
    void update(SysPermission permission);

    /**
     * 删除权限数据
     * @param id
     */
    void delete(Long id);

    /**
     * 分页查询
     * @return
     */
    Page<SysPermission> findPage (PermissionQueryVo permissionQueryVo);

    /**
     * 根据角色ID查询该角色下的权限信息
     * @param roleId
     * @return
     */
    List<SysPermission> findByRoleId(@Param("roleId") Long roleId);

    /**
     * 查询所有的权限数据
     * @return
     */
    List<SysPermission> findAll();
}
