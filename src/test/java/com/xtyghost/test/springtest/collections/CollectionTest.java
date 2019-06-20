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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xutong
 * @create 2018-12-29
 * @since 1.0.0
 */
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

}