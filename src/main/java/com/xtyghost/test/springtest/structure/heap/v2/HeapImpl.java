/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: dfs
 * Author:   xutong
 * Date:     2019-07-23 10:49
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapImpl<T extends Comparable<T>> implements IHeap<T> {

    private List<T> heap;

    private List<T> orginList;

    @Override
    public void initOriginList(List<T> orginList) {
        this.orginList = orginList;
    }

    @Override
    public List<T> getHeap() {
        return heap;
    }

    public HeapImpl() {
        this.heap = new ArrayList<>();
    }

    /**
     * 插入对应上浮
     *
     * @param start
     */
    protected void adjustUp(int start) {
        int currentIndex = start;
        int parentIndex = (currentIndex - 1) / 2;
        T tmp = heap.get(currentIndex);
        while (currentIndex > 0) {
            int cmp = heap.get(parentIndex).compareTo(tmp);
            if (cmp >= 0) {
                break;
            } else {
                heap.set(currentIndex, heap.get(parentIndex));
                currentIndex = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            }
        }
        heap.set(currentIndex, tmp);
    }

    public void insert(T data) {
        int size = heap.size();
        heap.add(data);    // 将"数组"插在表尾
        adjustUp(size);        // 向上调整堆
    }

    public void remove(int index) {
        int size = heap.size();
        heap.set(index, heap.get(size - 1));
        heap.remove(size - 1);
        adjustDown(index);
    }

    /**
     * 删除对应下沉
     *
     * @param index
     */
    private void adjustDown(int index) {
        int currentIndex = index;
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;
        T tmp = heap.get(currentIndex);
        int size = heap.size();
        while (leftChildIndex < size) {
            T left = null;
            T right = null;
            if (leftChildIndex < size) {
                left = heap.get(leftChildIndex);
            }
            if (rightChildIndex < size) {
                right = heap.get(rightChildIndex);
            }
            int maxIndex = right == null ? leftChildIndex : (left.compareTo(right) >= 0 ? leftChildIndex : rightChildIndex);
            T max = heap.get(maxIndex);
            if (tmp.compareTo(max) >= 0) {
                break;
            } else {
                heap.set(currentIndex, max);
                heap.set(maxIndex, tmp);
                leftChildIndex = maxIndex * 2 + 1;
                rightChildIndex = maxIndex + 1;
            }
        }
    }

    @Override
    public void makeHeap(int first, int last) {
        for (int i = first; i < last; i++) {
            insert(orginList.get(i));
        }
    }

    @Override
    public void popHeap(int first, int last) {
        remove(first);
    }

    @Override
    public void pushHeap(int first, int last) {
        adjustUp(last - 1);
    }

    @Override
    public void display() {
        System.out.println(heap);
    }

    public static void main(String[] args) {
        IHeap<Integer> heap = new HeapImpl<>();
        heap.initOriginList(Arrays.asList(10, 20, 30, 5, 15));
        System.out.println("初始构建堆：");
        heap.makeHeap(0, 5);
        heap.display();
        System.out.println("弹出堆顶：");
        heap.popHeap(0, 5);
        heap.display();
        System.out.println("弹出堆顶：");
        heap.popHeap(0, 4);
        heap.display();
        System.out.println("插入堆尾：");
        heap.getHeap().add(90);
        heap.display();
        System.out.println("重新排列：");
        heap.pushHeap(0, 4);
        heap.display();
    }

}