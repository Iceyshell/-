package com.yanagi.config.security.contents;

/**
 * 白名单
 * @author yanagi
 */
public class SecurityContents {
    public static final String[] WHITE_LIST = {
            //后端登录接口
            "/user/login",

            //swagger相关
            "/favicon.ico",
            "/swagger-ui.html",
            "/doc.html",
            "/webjars/**",
            "/swagger-resources/**",
            "/v2/**",
            "/configuration/ui",
            "/configuration/security",
            //忘记密码接口
            "/user/forget",
            //验证码登录
            "/tool/sms",
            "/user/sms/login",
            "/goods/batchExport",

            // 小程序相关
            "/mini/login",
    };
}
