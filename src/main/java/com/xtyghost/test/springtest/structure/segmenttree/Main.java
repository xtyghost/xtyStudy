/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Main
 * Author:   xutong
 * Date:     2019-08-12 16:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.structure.segmenttree;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutong
 * @create 2019-08-12
 * @since 1.0.0
 */
public class Main {
    public static void main(String[] args) {
        Integer[] nums = {-2, 3, 5, 7, 9, -1};
        SegmentTree<Integer> integerSegmentTree = new SegmentTree<>(nums, Integer::sum);
    }

}