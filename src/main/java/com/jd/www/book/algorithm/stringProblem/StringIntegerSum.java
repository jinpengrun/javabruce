package com.jd.www.book.algorithm.stringProblem;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/7 下午5:48</li>
 * <li>function:</li>
 * </ul>
 * 字符串中数字子串求和
 * 要求，忽略小数点字符，a1.3其中包含数字1和3
 * A-1bc--12 包含数字  -1 和 12
 *
 * 时间复杂度 为on 额外空间复杂度为o1
 * 解过程
 * 三个变量
 * 1 sum 累加和  num 当前收集到的数字，posi 正负值
 * 2 从左到右 遍历到字符 char
 * 3 如果char 0 - 9
 */
public class StringIntegerSum {

    public static  int numSum(String str){
        if(str == null){
            return 0;
        }


        char[] charArr = str.toCharArray();
        int res = 0 ; //总和
        int num = 0 ; //当前值
        boolean posi = true;

        int cur = 0 ;

        for(int i =0;i< charArr.length;i++){
            cur = charArr[i] - '0';
            if(cur < 0||cur > 9){
                res += num;
                num = 0; //重设当前值
                if(charArr[i] == '-'){
                    if(i-1 > -1&& charArr[i-1] == '-'){
                        posi=!posi;
                    }else{
                        posi = false;
                    }
                }else{
                    posi = true;
                }

            //如果是数字
            }else{
               num= num*10+(posi ? cur :-cur);
            }
        }
        res+=num;
        return res;
    }


    public static void main(String[] args) {
        char i = 'r';

        int io = i-'0';
        char so = '9';
        int soi = so-'0';
        char h = '汗';
        //操作目的 就是让h 显示出 int 的值
        int y = h -'0';
        System.out.println(i+"----"+io+"----"+so+"-----"+soi+"-----"+y);


        String ss = "3333";
        System.out.println(numSum(ss));
    }

}
