/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TimeServer
 * Author:   xutong
 * Date:     2019-07-24 18:32
 * Description: 时间服务器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.immoc.nio.v1;

/**
 * 〈一句话功能简述〉<br>
 * 〈时间服务器〉
 *
 * @author xutong
 * @create 2019-07-24
 * @since 1.0.0
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
      new Thread(timeServer,"NIO-MUltiplexerTimeServer-001").start();;
    }

}