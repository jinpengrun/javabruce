package com.jd.www.base.study.new1dot8;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Predicate;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/26 下午5:17</li>
 * <li>function:</li>
 * </ul>
 *
 * 函数式接口
 * java.util.function 包下
 */
public class FunctionInterfaceTest {


    public static void main(String[] args) {
        List<Integer> sy = Lists.newArrayList(5,6,8,3,1);

        System.out.println("输出所有数据：");

        eval(sy,n->true);

        System.out.println("输出大于1的数");

        eval(sy,n->n>1);

        System.out.println("输出偶数");

        eval(sy,n->n%2==0);

    }


    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for(Integer n: list) {

            if(predicate.test(n)) {
                System.out.print(n + " ");
            }
        }
    }

}
