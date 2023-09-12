package com.smile.project;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author: smile
 * @title:
 * @projectName:
 * @description: TODO
 * @date: 2023/9/11 3:48 下午
 */
@SpringBootTest
public class RedisTest {
    @Resource
    private RedisTemplate<String,String> redisTemplate;


    @Test
    public void testRedis() {
        redisTemplate.boundListOps("smile").leftPush("admin");
    }
}