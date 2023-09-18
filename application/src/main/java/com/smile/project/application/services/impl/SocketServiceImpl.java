package com.smile.project.application.services.impl;

import com.smile.project.application.handler.WebsocketInitHandler;
import com.smile.project.application.properties.SocketProperty;
import com.smile.project.application.services.SocketService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author: smile
 * @title:
 * @projectName:
 * @description: TODO
 * @date: 2023/9/18 12:07 下午
 */
@Slf4j
@Service
public class SocketServiceImpl implements SocketService {
    /**
     * 启动状态
     **/
    private boolean active;

    /**
     * boss
     **/
    private EventLoopGroup bossGroup;

    /**
     * work
     **/
    private EventLoopGroup workerGroup;

    /**
     * socket属性
     **/
    @Autowired
    private SocketProperty socketProperty;

    /**
     * server
     **/
    private ServerBootstrap serverBootstrap;

    /**
     * webSocket初始化handler
     **/
    @Autowired
    private WebsocketInitHandler websocketInitHandler;

    /**
     * init
    **/
    @PostConstruct
    private void init() {
        bossGroup =  new NioEventLoopGroup(socketProperty.getBossThreadCount());

        workerGroup = new NioEventLoopGroup(socketProperty.getWorkThreadCount());

        serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(websocketInitHandler);
    }

    /**
     * netty启动
    **/
    @Override
    public void start() {
        synchronized(this) {
            try {
                if (active) {
                    log.info("netty server has started");

                    return;
                }

                ChannelFuture bind = serverBootstrap.bind(socketProperty.getWebSocketPort());

                bind.addListener(future -> {
                    if (future.isSuccess()) {
                        log.info("netty server start success port: {}", socketProperty.getWebSocketPort());

                        active = true;
                    } else {
                        log.error("netty server start error", future.cause());
                    }
                }).sync();

                bind.channel().closeFuture().addListener((future -> {
                    log.info("netty server close success");
                })).sync();

            } catch (Exception exception) {
                log.error("netty server start error", exception);
            }
        }
    }

    /**
     * netty关闭
    **/
    @Override
    public void stop() {
        try {
            bossGroup.shutdownGracefully().addListener(future -> {
                if (future.isSuccess()) {
                    log.info("netty boss server shutdown success");
                }
                else {
                    log.error("netty boss server shutdown error", future.cause());
                }
            }).sync();

            workerGroup.shutdownGracefully().addListener(future -> {
                if (future.isSuccess()) {
                    log.info("netty worker server shutdown success");
                }
                else {
                    log.error("netty worker server shutdown error", future.cause());
                }
            }).sync();

        }catch (Exception exception) {
            log.error("netty server shutdown error", exception);
        }
    }
}