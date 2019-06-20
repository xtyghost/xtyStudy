/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Test
 * Author:   xutong
 * Date:     2019-01-03 16:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.reference;

import org.springframework.util.Assert;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

public class Test {
    public static boolean isRun = true;

    public static void main(String[] args) throws Exception {
        String abc = new String("abc");
        System.out.println(abc.getClass() + "@" + abc.hashCode());
        System.out.println(abc);
        final ReferenceQueue referenceQueue = new ReferenceQueue<String>();
        new Thread(() -> {
            while (isRun) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Object o = referenceQueue.poll();
//                System.out.println(o);
                if (o != null) {
                    try {
                        Field rereferent = Reference.class.getDeclaredField("referent");
                        rereferent.setAccessible(true);
                        Object result = rereferent.get(o);
                        System.out.println("gc will collect:" + result.getClass() + "@" + result.hashCode());
                        System.out.println(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        PhantomReference<String> abcWeakRef = new PhantomReference<String>(abc, referenceQueue);
        abc = null;
        Thread.sleep(400);
        System.gc();
        Assert.isNull(abc,"abc不是空");
        Thread.sleep(300);
        isRun = false;
    }
}
