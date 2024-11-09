package com.yanagi.service;

import com.yanagi.entity.SysMenu;
import com.yanagi.utils.QueryInfo;
import com.yanagi.utils.Result;
import com.yanagi.vo.MenuQueryVo;

/**
 * @author yanagi
 * @description 菜单service
 * @date 2024-05-01 0:52
 */

public interface SysMenuService {

    /**
     * 查询所有父级菜单
     * @return
     */
    Result findParent(MenuQueryVo queryInfo);

    /**
     * 分页查询
     * @param queryInfo 页码、页数大小、查询内容
     * @return
     */
    Result findPage(MenuQueryVo queryInfo);

    /**
     * 查询所有菜单
     * @return
     */
    Result findAll();

    /**
     * 添加菜单数据
     * @param menu
     * @return
     */
    Result insert(SysMenu menu);

    /**
     * 删除菜单数据
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 修改菜单数据
     * @param menu
     * @return
     */
    Result update(SysMenu menu);
}

