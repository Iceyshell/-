package com.yanagi;

import com.mysql.cj.log.Log;
import com.yanagi.utils.MD5Utils;
import com.yanagi.utils.MailUtils;
import com.yanagi.utils.SmsUtils;
import com.yanagi.vo.MailVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class JavaSportApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private SmsUtils smsUtils;

    @Test
    void contextLoads() {
        System.out.println(passwordEncoder.encode(MD5Utils.md5("123456")));

    }

    @Test
    void testMail() {
        MailVo mail = new MailVo();
        mail.setReceivers(new String[]{"2947140558@qq.com"});
        mail.setHtml(true);
        mail.setSubject("个人运动管理平台！");
        mail.setContent("<a href='https://www.baidu.com/' style='color: red'>邮件发送测试！</a>");
        System.out.println(mailUtils.sendMail(mail));
    }

    @Test
    void testSms() {
        //smsUtils.sendSms("15333008409",1234);
    }

}
