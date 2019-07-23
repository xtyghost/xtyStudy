/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ReadWriteLock
 * Author:   xutong
 * Date:     2019-07-19 10:26
 * Description: 简单的读写锁
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.lock.v1;

/**
 * 〈一句话功能简述〉<br>
 * 〈简单的读写锁〉
 *
 * @author xutong
 * @create 2019-07-19
 * @since 1.0.0
 */
public class ReadWriteLock {
    private volatile int readers = 0;
    private volatile int writers = 0;
    private volatile int writeRequests = 0;

    public synchronized void lockRead() throws InterruptedException {
        while (writers >0||writeRequests>0){
            wait();
        }
        readers++;
    }

     public synchronized void unlockRead(){
        readers--;
        notifyAll();
     }
     public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        while (readers>0||writers>0){
            wait();
        }
        writeRequests--;
        writers++;
     }
     public synchronized void unlockWrite(){
        writers--;
        notifyAll();
     }
}