package com.jeecg.p3.system.config;

import com.jeecg.p3.baseApi.util.OSSBootUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssBootConfiguration {

    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucketName}")
    private String bucketName;
    @Value("${oss.imgDomain}")
    private String imgDomain;


    @Bean
    public void initStatic() {
        OSSBootUtil.setEndPoint(endpoint);
        OSSBootUtil.setAccessKeyId(accessKeyId);
        OSSBootUtil.setAccessKeySecret(accessKeySecret);
        OSSBootUtil.setBucketName(bucketName);
        OSSBootUtil.setImgDomain(imgDomain);
    }
}