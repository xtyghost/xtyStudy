/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: sf
 * Author:   xutong
 * Date:     2019-07-23 10:48
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.structure.heap.v2;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutong
 * @create 2019-07-23
 * @since 1.0.0
 */

import java.util.List;

/**
 * Create by zxb on 2017/9/10
 */
public interface IHeap<T extends Comparable<T>> {

    void display();

    void initOriginList(List<T> orginList);

    void makeHeap(int first, int last);

    void popHeap(int first, int last);

    void pushHeap(int first, int last);

    List<T> getHeap();

}