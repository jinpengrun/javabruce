package com.jd.www.jdk_eight.extendAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/21 下午1:47</li>
 * <li>function:</li>
 * </ul>
 */
public class AnnotationsTest {
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE_USE,ElementType.TYPE_PARAMETER})
    public @interface NonEmpty{

    }

    public static class Holder<@AnnotationsTest.NonEmpty T> extends @AnnotationsTest.NonEmpty Object{
        public void method() throws @NonEmpty Exception{

        }
    }

    public static void main(String[] args) {
        final  Holder<String> holder = new @NonEmpty Holder<>();
        @NonEmpty Collection<@NonEmpty String> strings = new ArrayList<>();
    }


}
