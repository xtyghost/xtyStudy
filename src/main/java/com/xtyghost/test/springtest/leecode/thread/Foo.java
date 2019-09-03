/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: sdf
 * Author:   xutong
 * Date:     2019-08-01 10:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.leecode.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutong
 * @create 2019-08-01
 * @since 1.0.0
 */
public class Foo {
    private   CountDownLatch count1 = new CountDownLatch(1);
    private   CountDownLatch count2 = new CountDownLatch(1);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        count1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        count1.await();
        printSecond.run();
        count2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        count2.await();
        printThird.run();
    }
}