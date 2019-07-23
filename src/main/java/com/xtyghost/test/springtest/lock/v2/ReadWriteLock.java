/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ReadWriteLock
 * Author:   xutong
 * Date:     2019-07-19 10:34
 * Description: 简单的读写锁
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.lock.v2;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 〈一句话功能简述〉<br>
 * 〈简单的读写锁〉
 *
 * @author xutong
 * @create 2019-07-19
 * @since 1.0.0
 */
public class ReadWriteLock {
    private final ConcurrentHashMap<Thread, Integer> readerMap = new ConcurrentHashMap<>();
    private volatile int writer;
    /**
     * 用来提高写的优先级
     */
    private volatile int writerRequest;

    /**
     * TODO没有解决饥饿问题
     * @throws InterruptedException
     */
    public synchronized void lockWriter() throws InterruptedException {
        writerRequest++;
        while (writer > 0 || readerMap.size() > 0) {
            wait();
        }
        writerRequest--;
        writer++;
    }

    public synchronized void unlockWriter() throws InterruptedException {
        writerRequest++;
        while (writer > 0 || readerMap.size() > 0) {
            wait();
        }
        writerRequest--;
        writer--;
        notifyAll();
    }

    public synchronized void lockReader() throws InterruptedException {
        while (writer > 0 || writerRequest > 0) {
            wait();
        }
        Integer readCount = readerMap.get(Thread.currentThread());
        readerMap.put(Thread.currentThread(), readCount == null ? 1 : readCount++);
    }

    public synchronized void unlockReader() throws InterruptedException {
        while (writer > 0 || writerRequest > 0) {
            wait();
        }
        Integer readCount = readerMap.get(Thread.currentThread());
        if (readCount == 1 || readCount == null) {
            readerMap.remove(Thread.currentThread());
        } else {
            readerMap.put(Thread.currentThread(), readCount--);
        }
        notifyAll();

    }

}