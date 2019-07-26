/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TimeServer
 * Author:   xutong
 * Date:     2019-07-25 18:08
 * Description: nettty
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.immoc.netty.v1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 〈一句话功能简述〉<br>
 * 〈netty的时间服务器〉
 *
 * @author xutong
 * @create 2019-07-25
 * @since 1.0.0
 */
public class TimeServer {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {

            }
        }
        new TimeServer().bind(port);
    }

    public void bind(int port) throws Exception {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeServerHandler());
                }
            });
            //绑定端口，同步等待
            ChannelFuture sync = serverBootstrap.bind(port).sync();
            //等待服务端端口关闭
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            //优雅的关闭
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}

class TimeServerHandler extends SimpleChannelInboundHandler<ChannelHandlerContext> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChannelHandlerContext msg) throws Exception {

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

}