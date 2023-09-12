package com.smile.project.application.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: smile
 * @title: redisson配置项
 * @projectName: project
 * @description: redisson配置项
 * @date: 2023/9/12 10:46 上午
 */
@Configuration
public class RedissonConfiguration {
    /**
     * redis前缀
     **/
    private static final String REDISSON_PREFIX = "redis://";

    /**
     * host
    **/
    @Value("${spring.redis.host}")
    private String host;

    /**
     * port
     **/
    @Value("${spring.redis.port}")
    private String port;

    /**
     * password
     **/
    @Value("${spring.redis.password}")
    private String password;

    /**
     * redis配置
     **/
    @Bean("redissonClient")
    public RedissonClient redisson() {
        //创建配置
        Config config = new Config();

        config.useSingleServer().setAddress(REDISSON_PREFIX + host + ":" + port);
        config.useSingleServer().setPassword(password);

        // 根据 Config 创建出 RedissonClient 实例
        return Redisson.create(config);
    }
}