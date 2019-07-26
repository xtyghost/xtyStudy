/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: sdf
 * Author:   xutong
 * Date:     2019-07-25 15:51
 * Description: a
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.immoc.nio.v2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author xutong
 * @create 2019-07-25
 * @since 1.0.0
 */
public class BasicNio {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data.nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        System.out.println(bytesRead);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}