/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: NestAccessEaxample
 * Author:   xutong
 * Date:     2019-06-29 11:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.jdk11;

import java.lang.reflect.Field;

/**
 * 〈一句话功能简述〉<br>
 * 〈测试jdk11新增的nest权限范围〉
 *
 * @author xutong
 * @create 2019-06-29
 * @since 1.0.0
 */
public class NestAccessEaxample {
    public static class X {
        void test() throws NoSuchFieldException, IllegalAccessException {
            Y y = new Y();
            y.y=1;
            Field y1 = Y.class.getDeclaredField("y");
            y1.set(y,3);
            System.out.println(y.y);
        }
    }

    public static class Y {
        private int y;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        new X().test();
    }
}