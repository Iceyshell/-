package com.yanagi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yanagi
 * @description 描述
 * @date 2024-04-21 0:12
 */
@Data
@ApiModel(value = "登录参数")
public class LoginVo {
    @ApiModelProperty(value = "用户名", dataType = "String")
    private String username;

    @ApiModelProperty(value = "密码", dataType = "String")
    private String password;

    @ApiModelProperty(value = "手机号码", dataType = "String")
    private String phoneNumber;

    @ApiModelProperty(value = "验证码", dataType = "String")
    private String code;

    @ApiModelProperty(value = "1、账号密码登录，2、手机验证码登录")
    private String type;
}
