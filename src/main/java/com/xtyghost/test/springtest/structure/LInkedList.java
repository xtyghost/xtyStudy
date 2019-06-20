/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: LInkedList
 * Author:   xutong
 * Date:     2019-04-01 17:51
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
 * @create 2019-04-01
 * @since 1.0.0
 */
public class LInkedList<E> {
    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this.e = e;
            this.next = null;
        }

        public Node() {
            this.e = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
    private Node head;
    int size;

    public LInkedList() {
    }

    public LInkedList(Node head, int size) {
        this.head = head;
        this.size = size;
    }
}