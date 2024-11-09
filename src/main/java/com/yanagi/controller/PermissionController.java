package com.yanagi.controller;

import com.yanagi.entity.SysPermission;
import com.yanagi.service.SysPermissionService;
import com.yanagi.utils.QueryInfo;
import com.yanagi.utils.Result;
import com.yanagi.vo.PermissionQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yanagi
 * @description 权限controller
 * @date 2024-05-02 20:08
 */
@RestController
@Api(tags = "权限数据")
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private SysPermissionService permissionService;

    @ApiOperation(value = "分页查询")
    @PostMapping("/list")
    public Result findPage(@RequestBody PermissionQueryVo queryInfo) {
        return permissionService.findPage(queryInfo);
    }

    @ApiOperation(value = "添加权限")
    @PostMapping("/add")
    public Result insert(@RequestBody SysPermission permission) {
        return permissionService.insert(permission);
    }

    @ApiOperation(value = "修改权限")
    @PostMapping("/edit")
    public Result update(@RequestBody SysPermission permission) {
        return permissionService.update(permission);
    }

    @ApiOperation(value = "删除权限")
    @PostMapping("/delete")
    public Result delete(@RequestBody SysPermission permission) {
        return permissionService.delete(permission.getId());
    }

    @ApiOperation(value = "查询所有的权限")
    @GetMapping("/all")
    public Result findAll() {
        return permissionService.findAll();
    }
}
