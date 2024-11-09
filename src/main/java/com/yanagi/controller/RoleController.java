package com.yanagi.controller;

import com.yanagi.entity.SysRole;
import com.yanagi.service.SysRoleService;
import com.yanagi.utils.QueryInfo;
import com.yanagi.utils.Result;
import com.yanagi.vo.RoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yanagi
 * @description 角色的controller
 * @date 2024-05-05 8:25
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色管理")
public class RoleController {

    @Autowired
    private SysRoleService roleService;

    @GetMapping("/all")
    @ApiOperation(value = "添加用户时，返回角色信息列表")
    public Result findAll() {
        return roleService.findAll();
    }

    @ApiOperation(value = "分页查询")
    @PostMapping("/list")
    public Result findPage(@RequestBody RoleQueryVo queryInfo) {
        return roleService.findPage(queryInfo);
    }

    @ApiOperation(value = "删除角色信息")
    @PostMapping("/delete")
    public Result delete(@RequestBody SysRole sysRole) {
        return roleService.delete(sysRole.getId());
    }

    @ApiOperation(value = "添加角色信息")
    @PostMapping("/add")
    public Result insert(@RequestBody SysRole role) {
        return roleService.insert(role);
    }

    @ApiOperation(value = "修改角色信息")
    @PostMapping("/edit")
    public Result update(@RequestBody SysRole role) {
        return roleService.update(role);
    }
}
