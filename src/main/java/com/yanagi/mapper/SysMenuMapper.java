package com.yanagi.mapper;

import com.github.pagehelper.Page;
import com.yanagi.entity.SysMenu;
import com.yanagi.vo.MenuQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yanagi
 * @description 菜单mapper
 * @date 2024-05-01 0:46
 */

public interface SysMenuMapper {

    /**
     * 添加菜单信息
     * @param menu 菜单数据
     */
    void insert(SysMenu menu);

    /**
     * 修改菜单信息
     * @param menu 菜单数据
     */
    void update(SysMenu menu);

    /**
     * 删除菜单数据
     * @param id
     */
    void delete(Long id);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<SysMenu> findPage(MenuQueryVo queryString);

    /**
     * 查询所有父级菜单
     * @return
     */
    Page<SysMenu> findParent(MenuQueryVo queryString);

    /**
     * 查询所有菜单
     * @return
     */
    List<SysMenu> findAll();

    /**
     * 根据角色ID查询菜单信息
     * @param roleId
     * @return
     */
    List<SysMenu> findByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色ID和父级ID查询所有的子级菜单
     * @param parentId
     * @param roleId
     * @return
     */
    List<SysMenu> findByRoleIdAndParentId(@Param("parentId") Long parentId, @Param("roleId") Long roleId);
}

