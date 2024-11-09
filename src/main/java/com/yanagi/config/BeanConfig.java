package com.yanagi.config;

import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yanagi
 * @description 配置spring的bean
 * @date 2024-05-07 19:48
 */

@Configuration
public class BeanConfig {

    @Bean
    public UploadManager uploadManager() {
        com.qiniu.storage.Configuration cfg = new com.qiniu.storage.Configuration(Region.region1());
        return new UploadManager(cfg);
    }

}
