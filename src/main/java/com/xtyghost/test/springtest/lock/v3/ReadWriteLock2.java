/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ReadWriteLock2
 * Author:   xutong
 * Date:     2019-07-19 11:10
 * Description: 大佬版
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.lock.v3;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈大佬版〉
 *
 * @author xutong
 * @create 2019-07-19
 * @since 1.0.0
 */
public class ReadWriteLock2{
    private Map<Thread, Integer> readingThreads =
            new HashMap<Thread, Integer>();

    private int writeAccesses    = 0;
    private int writeRequests    = 0;
    private Thread writingThread = null;

    public synchronized void lockWrite()
            throws InterruptedException{
        writeRequests++;
        Thread callingThread = Thread.currentThread();
        while(!canGrantWriteAccess(callingThread)){
            wait();
        }
        writeRequests--;
        writeAccesses++;
        writingThread = callingThread;
    }

    public synchronized void unlockWrite()
            throws InterruptedException{
        writeAccesses--;
        if(writeAccesses == 0){
            writingThread = null;
        }
        notifyAll();
    }

    /**
     * TODO学习让代码语义更清晰
     * @param callingThread
     * @return
     */
    private boolean canGrantWriteAccess(Thread callingThread){
        if(hasReaders()) {
            return false;
        }
        if(writingThread == null) {
            return true;
        }
        return isWriter(callingThread);
    }

    private boolean hasReaders(){
        return readingThreads.size() > 0;
    }

    private boolean isWriter(Thread callingThread){
        return writingThread == callingThread;
    }
}