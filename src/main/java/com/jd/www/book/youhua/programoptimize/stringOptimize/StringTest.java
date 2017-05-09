package com.jd.www.book.youhua.programoptimize.stringOptimize;

import java.util.StringTokenizer;

/**
 * Created by zhujinpeng on 2017/4/20.
 */
public class StringTest {


    //字符串测试的时候 没有一个符合期望
    public static void main(String[] args) {
        String s = "1";
        String b = "1";
        String c = new String("1");
        System.out.println(s==b);//true
        System.out.println(b==c);//false
        System.out.println(b==c.intern());//true

        // 泄露问题

//        List<String> lists = Lists.newArrayList();
//
//        for(int i=0;i<100000;i++){
//            ImproveHugString hugeString = new ImproveHugString();
//            lists.add(hugeString.getSubString(1,5));
//        }


        // 字符串分割 ----------
        String sss = newString();
        long start = System.currentTimeMillis();
        //split性能
        for(int i=0;i<1000000;i++){
            sss.split(";");
        }
        System.out.println("spilt  use time "+(System.currentTimeMillis()-start));



        //字符串分割优化 ---------
        start = System.currentTimeMillis();
        StringTokenizer stringTokenizer = new StringTokenizer(sss,";");
        for(int i=0;i<1000000;i++){
            while(stringTokenizer.hasMoreTokens()){
                stringTokenizer.nextToken();
            }
            stringTokenizer = new StringTokenizer(sss,";");
        }

        System.out.println("stringTokenizer  use time "+(System.currentTimeMillis()-start));



        //字符串分割优化 ---------
        start = System.currentTimeMillis();
        String tmp = sss;
        for(int i=0;i<1000000;i++){
            while(true){
                String splitStr = null;
                int j = tmp.indexOf(";");
                if(j<0){
                    break;
                }
                splitStr = tmp.substring(0,j);
                tmp = tmp.substring(j+1);
            }
            tmp = sss;
        }

        System.out.println("index substring  use time "+(System.currentTimeMillis()-start));


        //测试 startwith
        String token = "wodetiankong";

        start = System.currentTimeMillis();
        for(int i=0;i<1000000000;i++){
            if(token.charAt(0)=='w' && token.charAt(1)=='o'&&token.charAt(2)=='d'){

            }
        }

        System.out.println("chatAt use time "+(System.currentTimeMillis()-start));

        start = System.currentTimeMillis();
        for(int i=0;i<1000000000;i++){
            if(token.startsWith("wod")){

            }
        }

        System.out.println("start use time "+(System.currentTimeMillis()-start));








    }


    //subString的内存溢出问


    static class HugeString{
        private String s = new String(new char[1000000]);
        public String getSubString(int begin,int end){
           return s.substring(begin,end) ;
        }
    }


    static class ImproveHugString{
        private String s = new String(new char[1000000]);
        public String getSubString(int begin,int end){
            return new String (s.substring(begin,end) );
        }
    }



    //字符串分割
    private static String newString(){
        String orgStr = null;
        StringBuffer stringBuffer=new StringBuffer();
        for(int i =0;i<100;i++){
            stringBuffer.append(i);
            stringBuffer.append(";");
        }
        orgStr = stringBuffer.toString();
        return orgStr;
    }







}
