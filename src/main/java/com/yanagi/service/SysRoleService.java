package com.yanagi.service;

import com.yanagi.entity.SysRole;
import com.yanagi.utils.QueryInfo;
import com.yanagi.utils.Result;
import com.yanagi.vo.RoleQueryVo;

/**
 * @author yanagi
 * @description 角色service
 * @date 2024-05-01 0:52
 */

public interface SysRoleService {
    /**
     * 分页查询
     * @param queryInfo 页码、页数大小、查询内容
     * @return
     */
    Result findPage(RoleQueryVo queryInfo);

    /**
     * 添加角色数据
     * @param role
     * @return
     */
    Result insert(SysRole role);

    /**
     * 删除角色数据
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 修改角色数据
     * @param role
     * @return
     */
    Result update(SysRole role);

    Result findAll();

}
