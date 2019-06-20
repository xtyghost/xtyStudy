/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CollectionTest
 * Author:   xutong
 * Date:     2018-12-29 10:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xutong
 * @create 2018-12-29
 * @since 1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@lombok.extern.slf4j.Slf4j
public class CollectionTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Object> objects = new CopyOnWriteArrayList<>();
        List<Integer> integers = Arrays.asList(1, 2, 2, 2);
        ArrayList<Integer> integers1 = new ArrayList<>(integers);
        integers1.removeIf(next -> next == 1);
        for (Integer integer : integers1) {
            System.out.println(integer);
        }

    }
    @Test
    public void test (){
        HashMap<String, String> m = new HashMap<>();
    }
    @Test
    public void bigSet(){
        BitSet bitSet = new BitSet(10);
        bitSet.and(new BitSet(11));
        System.out.println(bitSet);
        bitSet.set(10,false);
        System.out.println(bitSet.get(10));
//        Assert.assertEquals(21,bitSet.);
    }

}