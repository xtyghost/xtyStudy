/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: HelloServer
 * Author:   xutong
 * Date:     2019-07-16 10:55
 * Description: 实现客户端发送一个请求，服务端会返回hello netty
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.immoc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 〈一句话功能简述〉<br>
 * 〈实现客户端发送一个请求，服务端会返回hello netty〉
 *
 * @author xutong
 * @create 2019-07-16
 * @since 1.0.0
 */
public class HelloServer {
    public static void main(String[] args) throws InterruptedException {
        //定义一对线程组，用于接受客户端端连接，但是不做处理，只做分发
        //主线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        工作线程组，用于接受boss分发端任务
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            //netty 服务器端创建，serverBootstrap是一个启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup).//设置主从线程池
                    channel(NioServerSocketChannel.class).//设置nio端双向通道
                    childHandler(new HelloServerInitializer());//子处理器，处理工作线程组
            //启动server 并且设置8088为启动端端口，启动方式是同步
            ChannelFuture sync = serverBootstrap.bind(8000).sync();
            //监听关闭端channel，设置为同步方式
            sync.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}