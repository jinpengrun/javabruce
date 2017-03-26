package com.jd.www.book.algorithm.stringProblem;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/7 下午4:33</li>
 * <li>function:</li>
 * </ul>
 * 查看两个字符串是否为 变形词
 * 例如 abc  cab  返回true
 *      abc  aabbc  返回 false
 *
 *  时间复杂度为 on  字符种类为M 额外空间复杂度为 om
 *
 */
public class TransferString {
    public static void main(String[] args) {
        System.out.println(isDeformatin("abc","cba"));
    }


    public static boolean isDeformatin(String str1 ,String str2){
        if(str1 ==null || str2 ==null || str1.length() != str2.length()){
            return false;
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int[] map = new int[256];

        for(int i=0;i<s1.length;i++){
            map[s1[i]]++;
        }



        for(int i=0;i<s2.length;i++){
            if(map[s2[i]]-- == 0){
                return false;
            }
        }

        //int[]
        return true;
    }



}
