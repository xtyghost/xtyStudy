package com.immoc.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class TestTest {
    @Test
    public void Test1() {
        ByteBuf buf = Unpooled.copiedBuffer("sfas sfss sdfs", StandardCharsets.UTF_8);
        System.out.println((char) buf.readableBytes());
        int i = buf.readerIndex();
        System.out.println(i);
        int i1 = buf.writerIndex();
        System.out.println(buf.readableBytes());
        System.out.println(i1);
        System.out.println(buf.capacity());
        buf.writeByte((byte) '?');
        assert i == buf.readerIndex();
        assert i1 != buf.writerIndex();
    }
}