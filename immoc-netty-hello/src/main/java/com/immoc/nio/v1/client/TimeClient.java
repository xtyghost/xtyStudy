/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TimeClient
 * Author:   xutong
 * Date:     2019-07-25 14:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.immoc.nio.v1.client;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xutong
 * @create 2019-07-25
 * @since 1.0.0
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args!=null&&args.length>0){
            try {
                port=Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
            }
        }
        new Thread(new TimeClientHandle("127.0.0.1",port),"TiemClient-001").start();
    }

}