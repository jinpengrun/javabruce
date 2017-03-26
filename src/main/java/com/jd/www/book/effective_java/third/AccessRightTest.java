package com.jd.www.book.effective_java.third;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/17 上午11:18</li>
 * <li>function:</li>
 * </ul>
 */
public class AccessRightTest {
    //合理控制访问  看 readme 第一条
    public static final String[] VALUES = {"1","2","3"};

    //不支持修改 删除  添加
    public static final List<String> PRIVATE_VALUES =
            Collections.unmodifiableList(Arrays.asList(VALUES));
    public static void main(String[] args) {
        //还是可以修改
        VALUES[2]=null;
        System.out.println(VALUES[2]);

        //对原值修改后 对 private——values 生效
        System.out.println(PRIVATE_VALUES);
        //不能进行操作
        PRIVATE_VALUES.add("sdfds");
    }
}
