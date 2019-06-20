/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: RefereneTest
 * Author:   xutong
 * Date:     2019-01-03 14:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.reference;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

import static org.junit.Assert.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xutong
 * @create 2019-01-03
 * @since 1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@lombok.extern.slf4j.Slf4j
public class RefereneTest {
    @Test
    public void strongReference() {
        Object referent = new Object();

/**
 * 通过赋值创建 StrongReference 
 */
        Object strongReference = referent;

        assertSame(referent, strongReference);

        referent = null;
        System.gc();

/**
 * StrongReference 在 GC 后不会被回收
 */
        assertNotNull(strongReference);
    }
    @Test
    public void weakReference() {
        Object referent = new Object();
        WeakReference<Object> weakRerference = new WeakReference<Object>(referent);

        assertSame(referent, weakRerference.get());

        referent = null;
        System.gc();

/**
 * 一旦没有指向 referent 的强引用, weak reference 在 GC 后会被自动回收
 */
        assertNull(weakRerference.get());
    }
    @Test
    public void weakHashMap() throws InterruptedException {
        Map<Object, Object> weakHashMap = new WeakHashMap<Object, Object>();
        Object key = new Object();
        Object value = new Object();
        weakHashMap.put(key, value);

        assertTrue(weakHashMap.containsValue(value));

        key = null;
        System.gc();

/**
 * 等待无效 entries 进入 ReferenceQueue 以便下一次调用 getTable 时被清理
 */
        Thread.sleep(1000);

/**
 * 一旦没有指向 key 的强引用, WeakHashMap 在 GC 后将自动删除相关的 entry
 */
        assertFalse(weakHashMap.containsValue(value));
    }
    @Test

    public void softReference() {
        Object referent = new Object();
        SoftReference<Object> softRerference = new SoftReference<Object>(referent);

        assertNotNull(softRerference.get());

        referent = null;
        System.gc();

/**
 *  soft references 只有在 jvm OutOfMemory 之前才会被回收, 所以它非常适合缓存应用
 */
        assertNotNull(softRerference.get());
    }

}

class  VeryBig{
    private static final  int SIZE=10000;
    private long[] la=new long[SIZE];
    private String ident;

    @Override
    public String toString() {
        return "VeryBig{" +
                "ident='" + ident + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing"+ident);
    }
}