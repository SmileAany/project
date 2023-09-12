package com.smile.project.application.config;

import com.smile.project.application.properties.XxlProperty;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @author: smile
 * @title:
 * @projectName:
 * @description: TODO
 * @date: 2023/8/10 11:13 上午
 */
public class XxlJobConfiguration {
    /***
     * xxl 自定义配置值
     **/
    @Autowired
    private XxlProperty xxlProperty;

    /***
     * xxlJobExecutor 注册成bean
     **/
    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();

        xxlJobSpringExecutor.setAdminAddresses(xxlProperty.getJob().getAdmin().getAddresses());
        xxlJobSpringExecutor.setAppname(xxlProperty.getJob().getExecutor().getAppname());
        xxlJobSpringExecutor.setAddress(xxlProperty.getJob().getExecutor().getAddress());
        xxlJobSpringExecutor.setIp(xxlProperty.getJob().getExecutor().getIp());
        xxlJobSpringExecutor.setPort(xxlProperty.getJob().getExecutor().getPort());
        xxlJobSpringExecutor.setAccessToken(xxlProperty.getJob().getAccessToken());
        xxlJobSpringExecutor.setLogPath(xxlProperty.getJob().getExecutor().getLogpath());
        xxlJobSpringExecutor.setLogRetentionDays(xxlProperty.getJob().getExecutor().getLogretentiondays());

        return xxlJobSpringExecutor;
    }
}