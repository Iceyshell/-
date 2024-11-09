package com.yanagi.controller;

import com.yanagi.entity.SysUser;
import com.yanagi.service.SysUserService;
import com.yanagi.utils.QueryInfo;
import com.yanagi.utils.RedisUtils;
import com.yanagi.utils.Result;
import com.yanagi.vo.UserQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-05-06 14:59
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {
    @Autowired
    private SysUserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "分页查询")
    @PostMapping("/list")
    public Result findPage(@RequestBody UserQueryVo queryInfo) {
        return userService.findPage(queryInfo);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    public Result insert(@RequestBody SysUser user) {
        return userService.insert(user);
    }

    @ApiOperation(value = "修改用户")
    @PostMapping("/edit")
    public Result update(@RequestBody SysUser user) {
        return userService.update(user);
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("/delete")
    public Result delete(@RequestBody SysUser user) {
        return userService.delete(user.getId());
    }

    @ApiOperation(value = "用户数")
    @GetMapping("/count")
    public Result count() {
        return Result.success("查询所有部门成功", redisUtils.getValue("userCount"));
    }
}
