package com.jd.www.book.effective_java.six.thirtythree;

import com.google.common.collect.Sets;

import java.util.EnumSet;
import java.util.Set;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/22 下午2:39</li>
 * <li>function:</li>
 * </ul>
 */
public class Test {
    public class Text{
        public static final int STYLE_BLOB = 1 << 0;
        public static final int STYLE_ITALIC = 1 << 1;
        public static final int STYLE_UNDERLINE = 1 << 2;

        public void applyStyles(int styles){

        }


    }

    public enum Style{BOLD,ITALIC,UNDERLINE,STRIKETHROUGH}

    public void applyStyles(Set<Style> styles){}

    public static void main(String[] args) {
         Text text = new Test().new Text();
         //OR位运算将几个常量合并到一个集合中，称作位与  比较复杂
         text.applyStyles(Text.STYLE_BLOB|Text.STYLE_ITALIC);

        EnumSet.of(Style.BOLD,Style.ITALIC);



    }


}
