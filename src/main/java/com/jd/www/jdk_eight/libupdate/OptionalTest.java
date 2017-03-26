package com.jd.www.jdk_eight.libupdate;

import java.util.Optional;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/21 下午2:27</li>
 * <li>function:</li>
 * </ul>
 */
public class OptionalTest {
    public static void main(String[] args) {

        Optional<String> fullName = Optional.ofNullable(null);

        System.out.println("full name is set ?"+fullName.isPresent());
        System.out.println("full name:"+fullName.orElseGet(()->"[none]"));
        System.out.println(fullName.map(s->"hey"+s+"!").orElse("hey stranger!"));


        Optional<String> firstName = Optional.of("Tom");
        System.out.println("first name is set?"+firstName.isPresent());
        System.out.println("first name:"+firstName.orElseGet(()->"[none!]"));
        System.out.println(firstName.map(s->"hey "+s+"!").orElse("hey stranger!"));
    }
}
