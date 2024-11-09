package com.yanagi.controller;

import com.yanagi.entity.SysUser;
import com.yanagi.mapper.SysUserMapper;
import com.yanagi.service.SysUserService;
import com.yanagi.utils.*;
import com.yanagi.vo.ChangePwdVo;
import com.yanagi.vo.LoginVo;
import com.yanagi.vo.MailVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @author yanagi
 * @description 登录以及退出
 * 获取用户当前登录信息操作的接口
 * @date 2024-04-20 15:13
 */

@RestController
@Slf4j
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private SysUserService userService;

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {
        loginVo.setType("1");
        return userService.login(loginVo);
    }

    @ApiOperation(value = "短信登录")
    @PostMapping("/sms/login")
    public Result smsLogin(@RequestBody LoginVo loginVo) {
        loginVo.setType("2");
        System.out.println(loginVo.getPhoneNumber());
        return userService.login(loginVo);
    }


    @ApiOperation(value = "获取用户基本信息")
    @GetMapping("/getInfo")
    public Result getUserInfo() {
        return Result.success("获取用户信息成功", SecurityUtils.getUser());
    }

    @ApiOperation(value = "根据用户id获取当前用户的菜单列表（以前端路由格式）")
    @GetMapping("/menu/list")
    public Result getMenuList() {
        SysUser user = SecurityUtils.getUser();
        if (user.isAdmin()) {
            return Result.success("获取菜单列表成功", userService.findMenus(null));
        }
        else {
            return Result.success("获取菜单列表成功", userService.findMenus(user.getId()));
        }
    }

    @ApiOperation(value = "根据用户id获取当前用户的权限列表")
    @GetMapping("/auth/buttons")
    public Result getPermissionList() {
        SysUser user = SecurityUtils.getUser();
        if (user.isAdmin()) {
            return Result.success("获取菜单列表成功", userService.findPermissions(null));
        }
        else {
            return Result.success("获取菜单列表成功", userService.findPermissions(user.getId()));
        }
    }

    @ApiOperation(value = "用户退出登录")
    @PostMapping("/logout")
    public Result logout() {
        // 清除登录用户的缓存
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        log.info("退出登录");
        if (redisUtils.getValue("userCount") != null){
            int userCount = (Integer)redisUtils.getValue("userCount");
            if(userCount!=0) {
                redisUtils.setValue("userCount", userCount - 1);
            }
        }
        else {
            redisUtils.setValue("userCount", 0);
        }
        return Result.success("退出成功！");

    }

    @ApiOperation(value = "忘记密码，邮件找回")
    @PostMapping("/forget")
    public Result forget(@RequestBody MailVo mail) {
        mail.setSubject("个人运动管理平台");
        Random random = new Random();
        int password = 100000 + random.nextInt(1000000);
        userService.updatePwdByMail(mail.getReceivers()[0], passwordEncoder.encode(MD5Utils.md5(String.valueOf(password))));
        mail.setContent("您的新密码：" + password + " ，请妥善保管！");
        return Result.success(mailUtils.sendMail(mail));
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/pwd/change")
    public Result changePwd(@RequestBody ChangePwdVo changePwdVo) {
        return userService.changePwd(changePwdVo);
    }


}
