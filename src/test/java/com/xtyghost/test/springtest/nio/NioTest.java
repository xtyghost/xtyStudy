/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: NioTest
 * Author:   xutong
 * Date:     2019-01-08 10:40
 * Description: 用来演示nio的类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用来演示nio的类〉
 *
 * @author xutong
 * @create 2019-01-08
 * @since 1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class NioTest {
    private static final int BSIZE=1024;
    @Test
    public void test1() throws IOException {
        FileChannel in = new FileInputStream("/Users/xutong/IdeaProjects/javaWeb-project/springtest/src/test/java/com/xtyghost/test/springtest/nio/orignalfile").getChannel();
        FileChannel out = new FileOutputStream("/Users/xutong/IdeaProjects/javaWeb-project/springtest/src/test/java/com/xtyghost/test/springtest/nio/copyfile").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        while (in.read(buffer)!=-1){
            buffer.flip(); //prepare  for writing
            out.write(buffer);
            buffer.clear();  //prepare for reading
        }

    }
    @Test
    public void test2() throws IOException {
        FileChannel in = new FileInputStream("/Users/xutong/IdeaProjects/javaWeb-project/springtest/src/test/java/com/xtyghost/test/springtest/nio/orignalfile").getChannel();
        FileChannel out = new FileOutputStream("/Users/xutong/IdeaProjects/javaWeb-project/springtest/src/test/java/com/xtyghost/test/springtest/nio/copyfile2").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        in.transferTo(0,in.size(),out);

    }
    @Test
    public void test3() throws IOException {
        FileChannel in = new FileInputStream("orignalfile").getChannel();
        FileChannel out = new FileOutputStream("copyfile3").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        CharBuffer charBuffer = buffer.asCharBuffer();
        in.transferTo(0,in.size(),out);

    }

}