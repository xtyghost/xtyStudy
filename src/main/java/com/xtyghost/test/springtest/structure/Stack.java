package com.xtyghost.test.springtest.structure;

public interface Stack<E> {
    int getSize();
    boolean isEmpty();

    /**
     * 压张
     * @param e
     */
    void push(E e);

    /**
     * 出栈
     * @return
     */
    E pop();

    /**
     * 查看栈顶元素
     * @return
     */
    E peek();
}
