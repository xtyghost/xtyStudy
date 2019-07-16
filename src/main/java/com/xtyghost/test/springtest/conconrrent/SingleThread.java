/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: dsf
 * Author:   xutong
 * Date:     2019-06-20 11:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.conconrrent;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xutong
 * @create 2019-06-20
 * @since 1.0.0
 */
public class SingleThread implements Calculator {
    /**
     * 用单线程计算数组的和
     * @param calcData 需要求和的数组
     * @return
     * @author Rebecca 10:51 2019/6/18
     * @version 1.0
     */
    @Override
    public long sumUp(int[] calcData) {
        // 此句代码只是为了延长程序运行时间，和程序逻辑无关
        List<SingleThread> tasks = new ArrayList<SingleThread>();

        int calcDataLength = calcData.length;
        long sum = 0l;
        for (int i = 0; i < calcDataLength; i++) {
            sum += calcData[i];

            // 此句代码只是为了延长程序运行时间，和程序逻辑无关
            tasks.add(new SingleThread());
        }
        return sum;
    }
}
