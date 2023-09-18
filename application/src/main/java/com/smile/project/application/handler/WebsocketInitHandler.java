package com.smile.project.application.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;

/**
 * @author: smile
 * @title:
 * @projectName:
 * @description: TODO
 * @date: 2023/9/18 3:32 下午
 */
@Component
@ChannelHandler.Sharable
public class WebsocketInitHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        //http编解码器，对http协议的支持
        pipeline.addLast("http-codec",new HttpServerCodec());

        // 以块的方式来写的处理器，对大数据流的支持
        pipeline.addLast("http-chunked", new ChunkedWriteHandler());

        // post请求分三部分. request line / request header / message body，将多个消息转换为单一的request或者response对象
        pipeline.addLast("aggregator", new HttpObjectAggregator(65535));

        //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
        pipeline.addLast("websocket",  new WebSocketServerProtocolHandler("/wechat"));
    }
}