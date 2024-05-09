package com.zyt.config;

import com.zyt.properties.AliOssProperties;
import com.zyt.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j

public class OssConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){
        log.info("开始创建：{}",aliOssProperties);
        return new  AliOssUtil(aliOssProperties.getEndpoint(),aliOssProperties.getAccessKeyId()
        ,aliOssProperties.getAccessKeySecret(),aliOssProperties.getBucketName());
    };
}
