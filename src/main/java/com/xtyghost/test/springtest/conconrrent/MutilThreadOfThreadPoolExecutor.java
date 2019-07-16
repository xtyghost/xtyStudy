/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: df
 * Author:   xutong
 * Date:     2019-06-20 11:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.conconrrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutong
 * @create 2019-06-20
 * @since 1.0.0
 */
public class MutilThreadOfThreadPoolExecutor implements Calculator {

    /**
     * 用 ThreadPoolExecutor 线程池计算数组的和
     *
     * @param calcData 需要求和的数组
     * @return
     * @author Rebecca 10:51 2019/6/18
     * @version 1.0
     */
    @Override
    public long sumUp(int[] calcData) throws Exception {
        // 创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, // 线程数
                60L, TimeUnit.SECONDS,  // 超时时间
                new ArrayBlockingQueue<Runnable>(100, true),  // 线程处理数据的方式
                Executors.defaultThreadFactory(),  // 创建线程的工厂
                new ThreadPoolExecutor.CallerRunsPolicy());  // 超出处理范围的处理方式


        int calcDataLength = calcData.length;
        long sum = 0l;
        int threadSize = 5;

        for (int i = 0; i < threadSize; i++) {
            int arrStart = calcDataLength / threadSize * i;
            int arrEnd = calcDataLength / threadSize * (i + 1);

            SumTask task = new SumTask(calcData, arrStart, arrEnd);
            // 线程池处理数据
            Future<Long> future = executorService.submit(task);

            sum += future.get().longValue();
        }
        // 关闭线程池
        executorService.shutdown();

        return sum;
    }


    public static class SumTask implements Callable<Long> {
        private int[] arr;
        private int start, end;

        public SumTask() {
        }

        public SumTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        public Long call() {
            // 此句代码只是为了延长程序运行时间，和程序逻辑无关
            List<SumTask> tasks = new ArrayList<SumTask>();

            long sum = 0l;
            for (int i = start; i < end; i++) {
                sum += arr[i];
                // 此句代码只是为了延长程序运行时间，和程序逻辑无关
                tasks.add(new SumTask());
            }

            return sum;
        }
    }
}

