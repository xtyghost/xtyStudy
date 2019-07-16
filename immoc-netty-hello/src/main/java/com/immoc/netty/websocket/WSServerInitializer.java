/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: WSServerInitializer
 * Author:   xutong
 * Date:     2019-07-16 14:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.immoc.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutong
 * @create 2019-07-16
 * @since 1.0.0
 */
public class WSServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        //websocket基于http协议，所以要有HttpServerCodec编码解码器
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());
        //大数据流的支持
        pipeline.addLast("ChunkedWriteHandler", new ChunkedWriteHandler());
        //对httpmessage进行聚合，聚合层fullhttprequest或者fullhttpresponse
        pipeline.addLast(new HttpObjectAggregator(1024*64));
        //========================以上用于支持http协议================
        /**
         * websocket 服务器处理对协议，用于指定客户端连接访问路由：/ws
         * 本handler处理http升级为wobsocket对握手操作handshaking（colose，ping,pong）ping+pong=心跳
         * 对于websocket来讲，数据都是以frames进行传输，不同对数据类型对应不同的frames
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //自定义的handler
        pipeline.addLast(null);
    }
}