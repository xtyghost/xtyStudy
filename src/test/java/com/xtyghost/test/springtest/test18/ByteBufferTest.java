/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ByteBufferTest
 * Author:   xutong
 * Date:     2019-01-14 08:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.test18;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutong
 * @create 2019-01-14
 * @since 1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ByteBufferTest {
    private static class Endians {
        public static void main(String[] args) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[36]);
            wrap.asCharBuffer().put("abcdef");
            System.out.println(Arrays.toString(wrap.array()));
//            wrap.rewind();
            wrap.order(ByteOrder.BIG_ENDIAN);
            wrap.asCharBuffer().put("abcdef");
            System.out.println(Arrays.toString(wrap.array()));
//            wrap.rewind();
            wrap.order(ByteOrder.LITTLE_ENDIAN);
            wrap.asCharBuffer().put("abcdef");
            System.out.println(Arrays.toString(wrap.array()));
        }
    }

    /**
     * 内存映射文件
     */
    private static class LargeMappedFiles {
        static int length = 0x8ffffff; //128mb

        public static void main(String[] args) throws IOException {
            MappedByteBuffer out = new RandomAccessFile("test.dat", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
            for (int i = 0; i < length; i++)
                out.put((byte) 'x');
            System.out.println("finish, writing");
            for (int i = length / 2; i < length / 2 + 6; i++) {
                System.out.println((char) out.get(i));
            }

        }
    }

    /**
     * 对映射文件对部分加锁
     */
    private static class LockingMappedFiles {
        static final int LENGTH = 0x8ffffff; //128mb
        static FileChannel fc;

        public static void main(String[] args) throws IOException {
            fc = new RandomAccessFile(".gitignore", "rw").getChannel();
            MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
            for (int i = 0; i < LENGTH; i++) {
                out.put((byte) 'x');
            }
            new LockAndModify(out, 0, LENGTH / 3);
            new LockAndModify(out, 0, LENGTH / 2 + LENGTH / 4);


        }

        private static class LockAndModify extends Thread {
            private ByteBuffer buffer;
            private int start, end;

            LockAndModify(ByteBuffer mbb, int start, int end) {
                this.start = start;
                this.end = end;
                mbb.limit(end);
                mbb.position(start);
                buffer = mbb.slice();
                start();
            }

            @Override
            public void run() {
                try {
                    FileLock lock = fc.lock(start, end, false);
                    System.out.printf("Locked: %d to %d \n", start, end);
                    while (buffer.position() < buffer.limit() - 1)
                        buffer.position((byte) (buffer.get() + 1));
                    lock.release();
                    System.out.println("Released: %5d to %5d \n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 压缩类使用
     */
    private static class GzipCompress {
        public static void main(String[] args) throws IOException {
            BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("test.gz")));
            out.write("dfsfsfsdf".getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
            System.out.println("reading file");
            BufferedReader in = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("test.gz"))));
            String s;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
            }
        }
    }

}