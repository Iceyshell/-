package com.yanagi.controller;

import com.yanagi.service.SysUserService;
import com.yanagi.utils.*;
import com.yanagi.vo.LoginVo;
import com.yanagi.vo.MailVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Random;

/**
 * @author yanagi
 * @description 基本工具
 * @date 2024-05-07 19:53
 */

@RestController
@RequestMapping("/tool")
public class ToolController {

    @Autowired
    private QiniuUtils qiniuUtils;

    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "七牛云图片上传")
    @PostMapping("/upload")
    public Result uplaod(@RequestBody MultipartFile file) throws IOException {
        String url = qiniuUtils.upload(file.getBytes(), file.getOriginalFilename());
        return Result.success("图片上传成功！", url);
    }

    /**
     * 短信验证码发送
     * @param loginVo
     * @return
     */
    @PostMapping("/sms")
    public Result sms(@RequestBody LoginVo loginVo) {
        Random random = new Random();
        int code = 100000 + random.nextInt(899999);
        try {
            smsUtils.sendSms(loginVo.getPhoneNumber(), code);
            // 用用户电话匹配验证码
            redisUtils.setValueTime(loginVo.getPhoneNumber() + "sms", code, 5);
            return Result.success("验证码发送成功！");
        }
        catch (Exception e) {
            return Result.fail("验证码发送失败");
        }

    }

}
