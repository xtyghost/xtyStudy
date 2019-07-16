/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MutilThreadOfForkJoinPool
 * Author:   xutong
 * Date:     2019-06-20 11:20
 * Description: 尝试使用forkJoinPool
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.conconrrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 〈一句话功能简述〉<br>
 * 〈尝试使用forkJoinPool〉
 *
 * @author xutong
 * @create 2019-06-20
 * @since 1.0.0
 */
public class MutilThreadOfForkJoinPool implements Calculator {
    private ForkJoinPool pool;


    public MutilThreadOfForkJoinPool() {
        this.pool = ForkJoinPool.commonPool();
    }

    @Override
    public long sumUp(int[] calcData) {
        SumTask task = new SumTask(calcData, 0, calcData.length - 1);
        return pool.invoke(task);
    }


    public static class SumTask extends RecursiveTask<Long> {
        private int[] numbers;
        private int start;
        private int end;

        private SumTask(){}

        public SumTask(int[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            // 当需要计算的数字小于 10万 时，直接计算结果
            if (end - start < 1000000) {
                long total = 0;

                // 此句代码只是为了延长程序运行时间，和程序逻辑无关
                List<SumTask> tasks = new ArrayList<SumTask>();
                for (int i = start; i <= end; i++) {
                    total += numbers[i];
                    // 此句代码只是为了延长程序运行时间，和程序逻辑无关
                    tasks.add(new SumTask());
                }
                return total;
            } else {  // 否则，把任务一分为二，递归计算
                int middle = (start + end) / 2;
                SumTask taskLeft = new SumTask(numbers, start, middle);
                SumTask taskRight = new SumTask(numbers, middle + 1, end);
                taskLeft.fork();
                taskRight.fork();
                return taskLeft.join() + taskRight.join();
            }
        }
    }

}