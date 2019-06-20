/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ArrayStack
 * Author:   xutong
 * Date:     2019-01-10 13:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.structure;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xutong
 * @create 2019-01-10
 * @since 1.0.0
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;

    public ArrayStack(int capacity) {
        array=new Array<>(capacity);
    }

    @Override
    public int getSize() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.isempty();
    }

    @Override
    public void push(E e) {
       array.add(e);
    }

    @Override
    public E pop() {
        return array.remove(array.size()-1);
    }

    @Override
    public E peek() {
        return array.get(array.size()-1);
    }
}