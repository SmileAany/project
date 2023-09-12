package com.smile.project.application.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: smile
 * @title: xxl-job属性配置
 * @projectName: project
 * @description: xxl-job属性配置
 * @date: 2023/8/10 11:14 上午
 */
@Data
@Component
@ConfigurationProperties(prefix = "xxl")
public class XxlProperty {
    private Job job;

    @Data
    public static class Job {
        private String accessToken;

        private Admin admin;

        private Executor executor;
    }

    @Data
    public static class Admin {
        private String addresses;
    }

    @Data
    public static class Executor {
        private String appname;

        private Integer port;

        private String address;

        private String ip;

        private String logpath;

        private Integer logretentiondays;
    }
}