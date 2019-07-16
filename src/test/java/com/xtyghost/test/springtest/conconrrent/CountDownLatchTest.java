/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: dssf
 * Author:   xutong
 * Date:     2019-06-28 13:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.conconrrent;

import java.util.concurrent.CountDownLatch;

/**
 * 〈一句话功能简述〉<br> 
 * 〈CountDownLatch简单使用〉
 *
 * @author xutong
 * @create 2019-06-28
 * @since 1.0.0
 */
public class CountDownLatchTest {

    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();
                System.out.println(2);
//                c.countDown();
            }
        }).start();

        c.await();
        System.out.println("3");
    }

}
