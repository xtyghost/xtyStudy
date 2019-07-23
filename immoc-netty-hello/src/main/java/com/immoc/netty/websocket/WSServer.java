/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: WSServer
 * Author:   xutong
 * Date:     2019-07-16 14:33
 * Description: 聊天室服务端
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.immoc.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 〈一句话功能简述〉<br>
 * 〈聊天室服务端〉
 *
 * @author xutong
 * @create 2019-07-16
 * @since 1.0.0
 */
public class WSServer {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 采用netty的多线程模型
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new WSServerInitializer());
            ChannelFuture future = serverBootstrap.bind(8080).sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}