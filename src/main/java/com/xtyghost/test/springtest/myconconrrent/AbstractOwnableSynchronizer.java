/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: AbstractOwnableSynchronizer
 * Author:   xutong
 * Date:     2019-06-28 18:00
 * Description: 抽象的排他同步生成器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.myconconrrent;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈抽象的排他同步生成器〉
 *
 * @author xutong
 * @create 2019-06-28
 * @since 1.0.0
 */
public class AbstractOwnableSynchronizer implements Serializable {


    private static final long serialVersionUID = -7861809391080056143L;

    protected AbstractOwnableSynchronizer() {
    }
    private transient Thread exclusiveOwnerThread;
}