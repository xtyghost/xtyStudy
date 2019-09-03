/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: JsoupTest
 * Author:   xutong
 * Date:     2019-08-13 09:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xtyghost.test.springtest.jsoup;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutong
 * @create 2019-08-13
 * @since 1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class JsoupTest {
    @Test
    public void test1() throws IOException {
        Document doc = Jsoup.connect("https://www.jd.com/").get();
        String title = doc.title();
        System.out.println(title);
//        System.out.println(doc.body());
        Elements href = doc.getElementsByAttribute("href");
        for (Element element : href) {
            if (!StringUtil.isBlank(element.ownText())) {
                System.out.println(element.ownText());
            }
        }
    }

}