package com.jd.www.book.algorithm.stringProblem;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/7 下午6:49</li>
 * <li>function:</li>
 * </ul>
 */
public class StringAll {

    //去掉字符串中连续出现k个0的子串
    // 时间复杂度为on  空间复杂度 o1
    public static String removeKZeros(String str,int k){
        if(str==null || k<1){
            return str;
        }

        char[] chas = str.toCharArray();
        int count = 0,start =-1;
        for(int i=0;i!=chas.length;i++){
            if(chas[i] == '0'){
                count++;
                start = start == -1 ? i:start;
            }else{
                if(count==k){
                    while(count-- != 0){
                        chas[start++] = 0;
                    }

                }
                count = 0;
                start = -1;
            }
        }

        if(count == k){
            while(count-- != 0){
                chas[start++]=0;
            }
        }

        return String.valueOf(chas);
    }

    //判断互为旋转词
    //要求：a 和 b 长度不一样，那么a 和b 必然不为旋转词直接返回false；当a和b长度一样，都是n时，要求解法的时间复杂度为 on
    //解法  b+b 令为 c 看看c 是否包含 a  如果包含就互为旋转词
    public static boolean isRotation(String a,String b ){
        if(a == null || b==null || a.length()!=b.length()){
            return false;
        }

        String b2 = b+b;
        return b2.indexOf(a)!=-1;
    }



    //字符串中所有字符都只出现过一次
    //要求1 实现时间复杂度为on的方法
    //要求2 在保证额外空间复杂度为o1的前提下，请实现时间复杂度尽量低的方法

    //这是要求1
    public static boolean isUniquel1(char[] chas){
        if(chas==null){
            return true;
        }

        boolean[] map = new boolean[256];

        for(int i = 0;i<chas.length;i++){
            if(map[chas[i]]){
                return false;
            }
            map[chas[i]] = true;
        }

        return true;
    }


    //堆排序  额外空间复杂度为o1   时间复杂度稳定在 onlogn
    public static boolean isUniquel2(char[] chas){
        return false;
    }


    //翻转字符串
    //先整体做调整
    public static void reverse(char[] chas,int start,int end){
        char tmp =0;
        while(start < end ){
            tmp = chas[start];
            chas[start] = chas[end];
            chas[end] = tmp;
            start++;
            end--;
        }
    }

    public static void rotateWord(char[] chas){
        if(chas == null || chas.length == 0 ){
            return ;
        }

        reverse(chas,0,chas.length-1);
        int l = -1;
        int r = -1;

        for(int i =0;i<chas.length;i++){
            if(chas[i] != ' '){
                l =i ==0 ||chas[i-1] == ' ' ? i:l;
                r = i ==chas.length-1 || chas[i+1] == ' ' ? i : r;
            }
            if(l != -1 && r!=-1){
                reverse(chas,l,r);
                l=-1;
                r =-1;
            }
        }

        System.out.println(String.valueOf(chas));
    }


    public static void main(String[] args) {
        System.out.println(removeKZeros("skdjfk0000x000",3));
        System.out.println(isRotation("abc","cab"));
        System.out.println(isUniquel1("abcdefghijkl".toCharArray()));
         rotateWord("hello wo de shi jie".toCharArray());
    }

}
