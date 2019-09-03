/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SegmentTree
 * Author:   xutong
 * Date:     2019-08-12 15:43
 * Description: 线段树
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.structure.segmenttree;

import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 * 〈线段树〉
 *
 * @author xutong
 * @create 2019-08-12
 * @since 1.0.0
 */
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Objects[arr.length * 4];
        this.merger = merger;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return data[index];
    }

    //在treeindex的位置创建表示区间【l..r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    //在以treeeIndex为根的线段树中[l..r]的范围里，搜索取区间[queryl...queryR]的值
    private E query(int treeIndex, int l, int r, int queryl, int queryR) {
        if (l == queryl && r == queryR) {
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rigthTreeIndex = rightChild(treeIndex);
        if (queryl >= mid + 1) {
            return query(rigthTreeIndex, mid + 1, r, queryl, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryl, queryR);
        }
        E leftResult = query(leftTreeIndex, l, mid, queryl
                , mid);
        E rightResult = query(rigthTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    //完全二叉书的数组表示，一个索引所展示的左孩子节点的索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    //完全二叉书的数组表示，一个索引所展示的右孩子节点的索引
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                stringBuilder.append(tree[i]);
            } else {
                stringBuilder.append("null");
            }
            if (i != tree.length) {
                stringBuilder.append(',');
            }
        }
        return stringBuilder.toString();
    }
}