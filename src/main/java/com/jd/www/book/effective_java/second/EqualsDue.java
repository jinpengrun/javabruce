package com.jd.www.book.effective_java.second;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/16 下午5:32</li>
 * <li>function:</li>
 * </ul>
 * 覆盖equals 总要覆盖 hashcode 否则无法和 散列集合一起共用 包括 hashset  hashmap hashtable
 * object hashcode 通用约定
 * 两个对象equals 相等 那么 返回hashcode 值也一定要相等
 */
public class EqualsDue {
    public static class PhoneNumber{
        private final short areacode;
        private final short prefix;
        private final short linenumber;

        public PhoneNumber(int areacode, int prefix, int linenumber) {
            this.areacode = (short)areacode;
            this.prefix = (short)prefix;
            this.linenumber = (short)linenumber;
        }

        public short getAreacode() {
            return areacode;
        }

        public short getPrefix() {
            return prefix;
        }

        public short getLinenumber() {
            return linenumber;
        }

        //当只覆盖 equals 方法

        @Override
        public boolean equals(Object obj) {
            if(obj == this){
                return true;
            }else if(!(obj instanceof PhoneNumber)){
                return false;
            }

            PhoneNumber objtwo = (PhoneNumber)obj;
            return objtwo.getAreacode() == areacode && objtwo.getLinenumber()== linenumber &&objtwo.getPrefix() == prefix;
        }


        //覆盖hashcode方法


        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + areacode;
            result = 31 * result + prefix;
            result = 31 * result + linenumber;
            return result;
        }

        //用散列去取值
        public static void main(String[] args) {
            PhoneNumber phoneNumber = new PhoneNumber(123,345,567);
            System.out.println(phoneNumber.hashCode());
            Map map = Maps.newHashMap();
            map.put(phoneNumber, "bruce");
            //如果没有覆盖hashcode方法 两个hashcode 是不同的 从不同的桶中取 是娶不到的
            System.out.println(map.get(new PhoneNumber(123, 345, 567)));
        }
        //散列值的计算法则
        //第一步 保存某个 非零的常数值  定义为 result
        //第二部 每个域的散列值计算
          //如果是 boolean 则 f ？1：0
          // byte char int 则 （int）f
          //如果是long 则（int）(f^(f>>>32))
           //如果是float 则是 Float.floatToIntBits()
            //如果是double 则是 Double.doubleToLongBits()然后根据long型计算
            //如果是对象引用  递归调用该对象的 hashcode方法 如果为null 返回0
            //如果是数组 每个元素当作单独的 yu 来处理， 也可以使用Arrays.hashCode方法来生成
        //按照公式 result = 31 * result +c 返回result

    }

}

