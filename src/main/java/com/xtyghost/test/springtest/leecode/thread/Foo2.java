/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Foo2
 * Author:   xutong
 * Date:     2019-08-01 11:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.leecode.thread;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutong
 * @create 2019-08-01
 * @since 1.0.0
 */
class Foo2 {


    private volatile int state = 0;

    public Foo2() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        synchronized (this) {
            printFirst.run();
            state = 1;
        }


    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        for (; ; ) {
            if (state == 1) {
                synchronized (this) {
                    printSecond.run();
                    state = 2;
                    break;
                }
            }
        }

    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        for (; ; ) {
            if (state == 2) {
                synchronized (this) {
                    printThird.run();
                    state = -1;
                }
                break;
            }
        }

    }
}