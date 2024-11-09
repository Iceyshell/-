package com.yanagi.service;

import com.yanagi.entity.SysPermission;
import com.yanagi.utils.QueryInfo;
import com.yanagi.utils.Result;
import com.yanagi.vo.PermissionQueryVo;

/**
 * @author yanagi
 * @description 权限service
 * @date 2024-05-01 0:53
 */

public interface SysPermissionService {

    /**
     * 分页查询
     * @param queryInfo 页码、页数大小、查询内容
     * @return
         */
    Result findPage(PermissionQueryVo queryInfo);

    /**
     * 添加权限数据
     * @param permission
     * @return
     */
    Result insert(SysPermission permission);

    /**
     * 删除权限数据
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 修改权限数据
     * @param permission
     * @return
     */
    Result update(SysPermission permission);

    /**
     * 查询所有的权限数据
     * @return
     */
    Result findAll();
}
