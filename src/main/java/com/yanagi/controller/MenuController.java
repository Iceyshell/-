package com.yanagi.controller;

import com.yanagi.entity.SysMenu;
import com.yanagi.service.SysMenuService;
import com.yanagi.utils.QueryInfo;
import com.yanagi.utils.Result;
import com.yanagi.vo.MenuQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yanagi
 * @description 菜单controller
 * @date 2024-05-01 1:09
 */

@RestController
@Api(tags = "菜单数据")
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private SysMenuService menuService;

    @ApiOperation(value = "分页查询所有的父级菜单")
    @PostMapping("/parent/list")
    public Result findParent(@RequestBody MenuQueryVo queryInfo) {

        return menuService.findParent(queryInfo);
    }

    @ApiOperation(value = "菜单的分页查询")
    @PostMapping("/list")
    public Result findPage(@RequestBody MenuQueryVo queryInfo) {
        return menuService.findPage(queryInfo);
    }

    @ApiOperation(value = "菜单的孩子的列表查询")
    @GetMapping("/all")
    public Result findAll() {
        return menuService.findAll();
    }


    @ApiOperation(value = "添加菜单")
    @PostMapping("/add")
    public Result insert(@RequestBody SysMenu menu) {
        System.out.println(menu);
        return menuService.insert(menu);
    }


    @ApiOperation(value = "修改菜单")
    @PostMapping("/edit")
    public Result update(@RequestBody SysMenu menu) {
        return menuService.update(menu);
    }


    @ApiOperation(value = "删除菜单")
    @PostMapping("/delete")
    public Result delete(@RequestBody SysMenu menu) {
        return menuService.delete(menu.getId());
    }
}
