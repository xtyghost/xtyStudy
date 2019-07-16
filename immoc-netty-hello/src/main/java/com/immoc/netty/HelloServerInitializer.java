/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: HelloServerInitializer
 * Author:   xutong
 * Date:     2019-07-16 13:23
 * Description: 初始化器，channel注册后，会执行里面相应的初始化方法
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.immoc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈初始化器，channel注册后，会执行里面相应的初始化方法〉
 *
 * @author xutong
 * @create 2019-07-16
 * @since 1.0.0
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //通过socketchannel去获得相应的管道
        ChannelPipeline pipeline = channel.pipeline();
        //添加拦截器
        //HttpServerCodec是由netty自己提供的助手类，可以理解为拦截器
        //HttpServerCodec一个由netty提供的编解码器
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());
        //自定义的处理器,返回hello netty
        //SimpleChannelInboundHandler对于请求来说，相当于【入站，出境】
        pipeline.addLast("customHandler", new SimpleChannelInboundHandler<HttpObject>() {


            @Override
            public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                System.out.println("channel---注册");
                super.channelRegistered(ctx);
            }

            @Override
            public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
                System.out.println("channel---取消注册");
                super.channelUnregistered(ctx);
            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                System.out.println("channel--活跃");
                super.channelActive(ctx);
            }

            @Override
            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                System.out.println("channel==不活跃");
                super.channelInactive(ctx);
            }

            @Override
            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                System.out.println("channel---读取完毕");
                super.channelReadComplete(ctx);
            }

            @Override
            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                System.out.println("channel---用户事件触发");
                super.userEventTriggered(ctx, evt);
            }

            @Override
            public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
                System.out.println("channel---写更改");
                super.channelWritabilityChanged(ctx);
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                System.out.println("channel===异常捕捉:"+cause);
                super.exceptionCaught(ctx, cause);
            }


            @Override
            public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

                System.out.println("助手类添加");
                super.handlerAdded(ctx);
            }

            @Override
            public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
                System.out.println("助手类移除");
                super.handlerRemoved(ctx);
            }

            @Override
            protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {


                if (msg instanceof  HttpRequest){
                    //获取channel
                    Channel channel1 = ctx.channel();
                    //打印远程地址
                    System.out.println(channel1.remoteAddress());
                    //发送消息
                    ByteBuf content = Unpooled.copiedBuffer("hello nettty!", CharsetUtil.UTF_8);
                    //构建响应 http response
                    DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
                    //设置返回头
                    //为响应添加数据类型和长度
                    response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
                    response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
                    //将响应数据写到缓存，并刷到客户端
                    ctx.writeAndFlush(response);
                }


            }
        });
    }
}