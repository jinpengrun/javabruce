package com.jd.www.book.effective_java.six.thirtysecond;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/22 下午3:05</li>
 * <li>function:</li>
 * </ul>
 */
public class Test {
   public enum Type{ANNUAL,PERENNIAL,BIENNIAL}
    private final String name;
    private final Type type;
    Test(String name,Type type){
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    public static void main(String[] args) {
        Map<Test.Type,Set<Test>> test =
               Maps.newEnumMap(Test.Type.class);
        for(Test.Type t : Test.Type.values()){
            test.put(t, Sets.<Test>newHashSet());
        }

//        for(Test t : xxxx)
//            test.get(t.type).add(t);
    }

}
