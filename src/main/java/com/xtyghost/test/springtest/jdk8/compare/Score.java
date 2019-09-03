/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Comparator
 * Author:   xutong
 * Date:     2019-08-02 16:33
 * Description: 自定义比较器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.jdk8.compare;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Comparator;

import static java.util.Comparator.comparingInt;

/**
 * 〈一句话功能简述〉<br>
 * 〈自定义比较器〉
 *
 * @author xutong
 * @create 2019-08-02
 * @since 1.0.0
 */

@Builder
@AllArgsConstructor
public class Score implements Comparable {
    public static void main(String[] args) {
        Score build = Score.builder().math(10).chinese(11).english(12).build();
        Score score = new Score(10, 11, 10);
        System.out.println(score.compareTo(build));
    }
    private static final Comparator<Score> COMPARATOR = comparingInt((Score score) -> score.math).thenComparingInt(score -> score.chinese).thenComparingInt(score -> score.english);
    private Integer math;
    private Integer chinese;
    private Integer english;

    @Override
    public int compareTo(Object o) {
        return COMPARATOR.compare(this, (Score) o);
    }
}