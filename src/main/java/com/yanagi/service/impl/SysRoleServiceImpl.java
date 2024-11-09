package com.yanagi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanagi.entity.Record;
import com.yanagi.entity.SysMenu;
import com.yanagi.entity.SysPermission;
import com.yanagi.entity.SysRole;
import com.yanagi.mapper.RecordMapper;
import com.yanagi.mapper.SysMenuMapper;
import com.yanagi.mapper.SysPermissionMapper;
import com.yanagi.mapper.SysRoleMapper;
import com.yanagi.service.SysRoleService;
import com.yanagi.utils.*;
import com.yanagi.vo.RoleQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanagi
 * @description 角色的service
 * @date 2024-05-01 1:26
 */
@Service
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public Result findPage(RoleQueryVo queryInfo) {
        log.info("分页: 页码 -->{}， 页数大小--> {}", queryInfo.getPageNumber(), queryInfo.getPageSize());
        PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
        log.info("查询-->{}", queryInfo.getQueryString());
        Page<SysRole> page = roleMapper.findPage(queryInfo);
        long total = page.getTotal();
        log.info("分页总数据数: {}", total);
        List<SysRole> result = page.getResult();
        result.forEach(item -> {
            // 查询角色下菜单信息
            List<SysMenu> menus = menuMapper.findByRoleId(item.getId());
            menus.forEach(menu -> {
                List<SysMenu> children = menuMapper.findByRoleIdAndParentId(menu.getId(), item.getId());
                menu.setChildren(children);
            });
            item.setMenus(menus);
            // 查询该角色权限信息
            List<SysPermission> permissions = permissionMapper.findByRoleId(item.getId());
            item.setPermissions(permissions);
        });
        Record record = new Record();
        record.setType(4);
        recordMapper.insert(record);
        return PageResult.pageResult(queryInfo.getPageNumber(), queryInfo.getPageSize(), total, result);
    }

    @Transactional
    @Override
    public Result insert(SysRole role) {
        log.info("查询角色信息是否存在");
        SysRole role1 = roleMapper.findByLabel(role.getLabel());
        if (null != role1) {
            return Result.fail("该角色已经存在");
        }
        //插入角色信息
        roleMapper.insert(role);
        // 添加新的权限
        if(role.getPermissionIds().size() > 0) {
            role.getPermissionIds().forEach(item -> roleMapper.insertPermissions(role.getId(), item));}
        //添加新的菜单
        if(role.getMenuIds().size() > 0) {
            role.getMenuIds().forEach(item -> roleMapper.insertMenus(role.getId(), item));}
//        if (role.getPermissions().size() > 0) {
//            log.info("再添加对应的权限数据");
//            role.getPermissions().forEach(item -> roleMapper.insertPermissions(role.getId(), item.getId()));
//        }
//        if (role.getMenus().size() > 0) {
//            role.getMenus().forEach(item -> roleMapper.insertMenus(role.getId(), item.getId()));
//        }
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());

        Record record = new Record();
        record.setType(1);
        recordMapper.insert(record);
        return Result.success("添加角色信息成功！");
    }

    @Override
    public Result delete(Long id) {
        log.info("查询该角色信息下是否有菜单权限");
        List<SysMenu> menus = menuMapper.findByRoleId(id);
        List<SysMenu> childrens = new ArrayList<>();
        menus.forEach(item -> {
            childrens.addAll(menuMapper.findByRoleIdAndParentId(item.getId(), id));
        });
        if (menus.size() > 0 || childrens.size() > 0) {
            return Result.fail("删除失败，该角色下拥有菜单信息，请先删除对应的菜单信息！");
        }
        if (permissionMapper.findByRoleId(id).size() > 0) {
            return Result.fail("删除失败，该角色下拥有权限信息，请先删除对应的权限信息！");
        }
        roleMapper.delete(id);
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());

        Record record = new Record();
        record.setType(2);
        recordMapper.insert(record);
        return Result.success("删除成功！");
    }

    @Transactional
    @Override
    public Result update(SysRole role) {
        //log.info(role.toString());
        // 如果权限和菜单数据有变化
        if (role.getPermissionIds() != null || role.getMenuIds() != null) {
            // 删除权限数据
            roleMapper.deletePermissionById(role.getId());
            // 添加新的权限
            if(role.getPermissionIds().size() > 0) {
                role.getPermissionIds().forEach(item -> roleMapper.insertPermissions(role.getId(), item));
            }
            //删除菜单数据
            roleMapper.deleteMenuById(role.getId());
            //添加新的菜单
            if(role.getMenuIds().size() > 0) {
                role.getMenuIds().forEach(item -> roleMapper.insertMenus(role.getId(), item));
            }
        }
        roleMapper.update(role);
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());

        Record record = new Record();
        record.setType(3);
        recordMapper.insert(record);
        return Result.success("修改角色信息成功！");
    }

    @Override
    public Result findAll() {
        return Result.success("查询所有角色成功", roleMapper.findAll());
    }
}
