package com.yanagi.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yanagi
 * @description 阿里云发送短信的工具类
 * @date 2024-05-08 11:18
 */

@Slf4j
@Component
public class SmsUtils {

    @Value("${aliyun.accessKey}")
    private String accessKey;

    @Value("${aliyun.secretKey}")
    private String secretKey;

    @Value("${aliyun.signName}")
    private String signName;

    @Value("${aliyun.templateCode}")
    private String templateCode;

    /**
     * 构建发送短信的连接
     * @return 短信的连接
     */
    public Client createClient() throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessKey)
                .setAccessKeySecret(secretKey);
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }

    /**
     * 传入电话号码， 发送短信
     * @param phoneNumbers
     * @return
     */
    public void sendSms(String phoneNumbers, int code) throws Exception {

        Client client = this.createClient();
        SendSmsRequest request = new SendSmsRequest()
                .setPhoneNumbers(phoneNumbers)
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setTemplateParam("{ code: " + code + " }");
        SendSmsResponse response = client.sendSms(request);
        log.info("短信发送结果--> {}", response.getBody().code + "----------" + response.getBody().message);
    }


}

