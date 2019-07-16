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

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

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
        pipeline.addLast("HttpServerCodec",new HttpServerCodec());
    }
}