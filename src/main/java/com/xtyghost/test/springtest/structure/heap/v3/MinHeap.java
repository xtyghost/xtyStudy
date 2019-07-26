/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MinHeap
 * Author:   xutong
 * Date:     2019-07-23 11:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.structure.heap.v3;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutong
 * @create 2019-07-23
 * @since 1.0.0
 */
public class MinHeap<E extends Comparable<E>> extends AbstractHeap<E> {


    //向堆中添加元素
    public void add(E e) {
        data.add(e);
        shiftUp(data.size() - 1);
    }

    public void shiftUp(int k) {

        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            swap(k,parent(k));
            k=parent(k);
        }
    }

    private void swap(int k,int pk) {
        E e = data.get(k);
        data.set(k,data.get(pk));
        data.set(pk,e);
    }
    /**
     * 查询堆中最大元素
     *
     * @return
     */
    public E findMin() {
        if (isEmpty()) {
            throw new IllegalArgumentException("heap is empty");
        }
        return data.get(0);
    }

    /**
     * 取出堆中最大元素
     *
     * @return
     */
    public E extractMin() {
        E max = findMin();
        swap(0,data.size()-1);
//        siftDown(0);
        return max;
    }

}