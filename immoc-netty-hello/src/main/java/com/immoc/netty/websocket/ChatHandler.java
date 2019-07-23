/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ChatHandler
 * Author:   xutong
 * Date:     2019-07-17 15:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.immoc.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutong
 * @create 2019-07-17
 * @since 1.0.0
 * TextWebSocketFrame 在netty中,是用于websocket专门处理文本的对象,frame是消息载体
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //用于记录管理所有客户的channel
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        //获取客户端传输的消息
        String context = msg.text();
        System.out.println("接受到的数据:" + context);
//        for (Channel client : clients) {
//            client.writeAndFlush(new TextWebSocketFrame("[服务器接受消息:  "+ LocalDateTime.now()));
//        }
        clients.writeAndFlush(new TextWebSocketFrame("[服务器接受消息:  "+ LocalDateTime.now()));

    }

    /**
     * 当客户端连接服务端后(打开连接)
     * 获取客户端的channel,并且放到channelGroup中去进行管理
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        System.out.println("asShortText::" + ctx.channel().id().asShortText());
        System.out.println("asLongText::" + ctx.channel().id().asLongText());
        clients.remove(ctx.channel());
    }
}