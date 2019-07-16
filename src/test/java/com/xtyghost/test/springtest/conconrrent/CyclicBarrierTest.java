/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: sdfs
 * Author:   xutong
 * Date:     2019-06-28 13:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.conconrrent;

import java.util.concurrent.CyclicBarrier;

/**
 * 〈一句话功能简述〉<br> 
 * 〈CyclicBarrier简单使用〉
 *
 * @author xutong
 * @create 2019-06-28
 * @since 1.0.0
 */
public class CyclicBarrierTest {

    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                c.reset();
                c.await();
            } catch (Exception e) {

            }
            System.out.println(1);
        }).start();

        try {
            System.out.println("main");
//            c.reset();
            c.await();
        } catch (Exception e) {

        }
        System.out.println(2);
    }
}
