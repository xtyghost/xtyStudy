/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: DynamicTet
 * Author:   xutong
 * Date:     2019-06-29 10:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.dynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xutong
 * @create 2019-06-29
 * @since 1.0.0
 */
public class DynamicTest {
    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle test = lookup.findStatic(DynamicTest.class, "test", MethodType.methodType(void.class));
        test.invokeExact();
    }

    private static void test(){
        System.out.println("hello");
    }

}