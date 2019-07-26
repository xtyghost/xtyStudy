/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MinHeap
 * Author:   xutong
 * Date:     2019-07-23 10:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.structure.heap.v3;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xutong
 * @create 2019-07-23
 * @since 1.0.0
 */
public abstract class AbstractHeap<E extends Comparable<E>>  {
    protected List<E> data;

    public AbstractHeap(int capacity) {
        this.data = new ArrayList<>(capacity);
    }
    public AbstractHeap() {
        this.data = new ArrayList<>();
    }

    public Integer size(){
        return data.size();
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
    protected int parent(int index){
        if(index==0){
            throw new IllegalArgumentException("index-0 doesn't have parent");
        }
        return (index-1)/2;
    }

    protected int leftChild(int index){
        return index*2+1;
    }
    protected int rightChild(int index){
        return index*2+2;
    }
}