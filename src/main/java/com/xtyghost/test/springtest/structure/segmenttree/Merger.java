/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Merge
 * Author:   xutong
 * Date:     2019-08-12 16:13
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
public interface Merger<E> {
    E merge(E a, E b);

}