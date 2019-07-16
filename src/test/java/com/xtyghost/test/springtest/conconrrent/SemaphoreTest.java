/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: sfdf
 * Author:   xutong
 * Date:     2019-06-28 13:38
 * Description: Semphore简单实用
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.conconrrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Semphore简单实用〉
 *
 * @author xutong
 * @create 2019-06-28
 * @since 1.0.0
 */
public class SemaphoreTest {

    public SemaphoreTest() {

        super();
    }

    static Semaphore semaphore = new Semaphore(3);

    public  static void main(String[] args) {
        for ( int i=0; i<10; i++ ) {
            new Thread(() -> {
                try {
                    semaphore.acquire();

                    //这里执行任务代码
                    System.out.println(Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(3);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

