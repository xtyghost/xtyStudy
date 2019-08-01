/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: PlainOioServer
 * Author:   xutong
 * Date:     2019-08-01 10:16
 * Description: jdk的网络编程
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.immoc.netty.v4;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 〈一句话功能简述〉<br>
 * 〈jdk的网络编程〉
 *
 * @author xutong
 * @create 2019-08-01
 * @since 1.0.0
 */
public class PlainOioServer {
    public void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        for (; ; ) {
            threadPoolExecutor.execute(() -> {
                OutputStream out;
                try (final Socket clientSocket = socket.accept()
                ) {
                    System.out.println("Accepted connection from:" + clientSocket);
                    out = clientSocket.getOutputStream();
                    out.write("hi!\r\n".getBytes(UTF_8));
                    out.flush();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }


}