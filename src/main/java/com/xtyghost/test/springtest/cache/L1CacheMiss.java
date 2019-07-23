/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: dsf
 * Author:   xutong
 * Date:     2019-07-17 15:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.cache;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutong
 * @create 2019-07-17
 * @since 1.0.0
 */
public class L1CacheMiss {
    private static final int RUNS = 10;
    private static final int DIMENSION_1 = 1024 * 1024;
    private static final int DIMENSION_2 = 62;

    private static long[][] longs;

    public static void main(String[] args) throws Exception {
        Thread.sleep(10000);
        longs = new long[DIMENSION_1][];
        for (int i = 0; i < DIMENSION_1; i++) {
            longs[i] = new long[DIMENSION_2];
            for (int j = 0; j < DIMENSION_2; j++) {
                longs[i][j] = 0L;
            }
        }
        System.out.println("starting....");

        final long start = System.nanoTime();
        long sum = 0L;
        for (int r = 0; r < RUNS; r++) {
            {
//                          for (int j = 0; j < DIMENSION_2; j++) {
//              for (int i = 0; i < DIMENSION_1; i++) {
//                  sum += longs[i][j];
//              }
//          }
            }

            {
                for (int i = 0; i < DIMENSION_1; i++) {
                    for (int j = 0; j < DIMENSION_2; j++) {
                        sum += longs[i][j];
                    }
                }

            }
        }
        System.out.println("duration = " + (System.nanoTime() - start));
//        duration = 572517327
//     duration=   15041605784
        //perf stat -e L1-dcache-load-misses java L1CacheMiss
        //验证缓存丢失
        // cache满, 一般情况下我们需要减少操作的数据大小, 尽量按数据的物理顺序访问数据.
    }
}