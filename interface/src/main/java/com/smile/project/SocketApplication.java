package com.smile.project;

import com.smile.project.application.services.SocketService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * @author: smile
 * @title: netty启动/停止器
 * @projectName: project
 * @description: netty启动/停止器
 * @date: 2023/9/18 10:35 上午
 */
@Slf4j
@Component
public class SocketApplication implements ApplicationRunner, ApplicationListener<ContextClosedEvent> {

    /**
     * socketService
     **/
    @Autowired
    private SocketService socketService;

    /**
     * 项目停止时触发
    **/
    @Override
    public void onApplicationEvent(@NotNull ContextClosedEvent event) {
        socketService.stop();
    }

    /**
     * 项目启动后触发
    **/
    @Override
    public void run(ApplicationArguments args) {
        socketService.start();
    }
}