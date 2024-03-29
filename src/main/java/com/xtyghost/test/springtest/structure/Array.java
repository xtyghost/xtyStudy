/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Array
 * Author:   xutong
 * Date:     2019-01-10 13:25
 * Description: 以自定义的Arraylist
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.structure;


import java.util.Arrays;

/**
 * 〈一句话功能简述〉<br>
 * 〈以自定义的Arraylist〉
 *
 * @author xutong
 * @create 2019-01-10
 * @since 1.0.0
 */
public class Array<E> {
    private final int leastdecCap = 40;
    private Integer capability = 10;
    private Integer size = 0;
    private E[] array = (E[]) new Object[capability];

    public Array() {
    }

    public Array(Integer capability) {
        this.capability = capability;
    }

    public void add(E e) {
        //判断是否超过容量
        //如果超过进行扩容
        if (size + 1 > capability) {
            incre(e);
        } else {
            array[size++] = e;
        }

    }

    public E remove(int index) {
        //remove元素
        E ret = array[index];
        System.arraycopy(array, index + 1, array, index, capability - index - 1);
        //判读是否需要减少容量
        if (capability > leastdecCap && size - 1 <= capability >> 2) {
            E[] oldarray = array;
            array = (E[]) new Object[capability >> 2];
            System.arraycopy(oldarray, 0, array, 0, capability >> 2);
            capability=array.length;
        }
        size--;
        return ret;
    }

    private void incre(E e) {
        E[] oldarray = array;
        array = (E[]) new Object[(capability >> 1) + capability];
        System.arraycopy(oldarray, 0, array, 0, size);
        array[size++] = e;
        capability = (capability >> 1) + capability;
    }

    /**
     * 根据对象取索引，如果有多个返回第一个值的索引
     *
     * @param e
     * @return
     */
    public int getIndex(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 交换索引的元素
     *
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
        if (i < 0 || j < 0 || i >= size || j >= size) {
            throw new IllegalArgumentException("index cannot be zore");

        }
        E e = array[i];
        array[i] = array[j];
        array[j] = e;


    }


    public int size() {
        return size;
    }

    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("索引不存在");
        }
        return array[index];
    }

    public boolean isempty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return "Array{" +
                "leastdecCap=" + leastdecCap +
                ", capability=" + capability +
                ", size=" + size +
                ", array=" + Arrays.toString(array) +
                '}';
    }
}