package com.smile.project.application.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: smile
 * @title:
 * @projectName:
 * @description: TODO
 * @date: 2023/8/29 11:26 上午
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "socket")
public class SocketProperty {
    /**
     * 服务端口
     */
    private Integer webSocketPort;

    /**
     * boos 线程数
     */
    private Integer bossThreadCount;

    /**
     * work 线程数
     */
    private Integer workThreadCount;
}