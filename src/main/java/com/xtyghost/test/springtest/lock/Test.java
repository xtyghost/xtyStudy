/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Test
 * Author:   xutong
 * Date:     2019-07-19 10:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.lock;

import com.xtyghost.test.springtest.lock.v1.ReadWriteLock;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xutong
 * @create 2019-07-19
 * @since 1.0.0
 */
public class Test {
    public static void main(String[] args) {
        new ReadWriteLock().unlockWrite();
    }

}