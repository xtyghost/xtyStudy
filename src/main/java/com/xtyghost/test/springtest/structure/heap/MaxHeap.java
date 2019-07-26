/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MaxHeap
 * Author:   xutong
 * Date:     2019-07-22 15:59
 * Description: 最大堆
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.structure.heap;

import com.xtyghost.test.springtest.structure.Array;

/**
 * 〈一句话功能简述〉<br>
 * 〈最大堆〉
 *
 * @author xutong
 * @create 2019-07-22
 * @since 1.0.0
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {

        data = new Array<>(capacity);
    }

    public MaxHeap() {
        this.data = new Array<>();
    }

    /**
     * 添加元素
     *
     * @param e
     */
    public void add(E e) {
        data.add(e);
        siftUp(data.size() - 1);
    }

    /**
     * 上浮
     *
     * @param k
     */
    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            //重新定位k方便比较【
            k = parent(k);
        }
    }

    /**
     * 查询堆中最大元素
     *
     * @return
     */
    public E findMax() {
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
    public E extractMax() {
        E max = findMax();
        data.swap(0, data.size() - 1);
        data.remove(data.size()-1);
        siftDown(0);
        return max;
    }

    /**
     * 元素下沉，让二叉堆保持有序
     */
    private void siftDown(int k) {
        int l = leftChild(k);
        int r = rightChild(k);
        if (r < data.size()) {
            Integer max = null;
            if (data.get(l).compareTo(data.get(r)) > 0) {
                max = l;
            } else {
                max = r;
            }
            if (data.get(max).compareTo(data.get(k)) > 0) {
                data.swap(k, max);
                k = max;
                siftDown(k);
            }
        } else if (l < data.size()) {
            if (data.get(l).compareTo(data.get(k)) > 0) {
                data.swap(k, l);
                k = l;
                siftDown(k);
            }
        }
    }


    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isempty();
    }

    /**
     * 计算当前节点的父节点
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index cannot be zore");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return (index << 1 )+ 1;
    }

    private int rightChild(int index) {
        return (index << 1 ) +2;
    }

    @Override
    public String toString() {
        return "MaxHeap{" +
                "data=" + data.toString() +
                '}';
    }
}