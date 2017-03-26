package com.jd.www.base.study;

/**
 * Created by zhujinpeng on 15/12/14.
 */
public class HashCodeClass
{
    private String str0;
    private double dou0;
    private int    int0;

    public boolean equals(Object obj)
    {
        if (obj instanceof HashCodeClass)
        {
            HashCodeClass hcc = (HashCodeClass)obj;
            if (hcc.str0.equals(this.str0) &&
                    hcc.dou0 == this.dou0 &&
                    hcc.int0 == this.int0)
            {
                return true;
            }
            return false;
        }
        return false;
    }


    public static void main(String[] args)
    {
        System.out.println(new HashCodeClass().hashCode());
        System.out.println(new HashCodeClass().hashCode());
        System.out.println(new HashCodeClass().hashCode());
        System.out.println(new HashCodeClass().hashCode());
        System.out.println(new HashCodeClass().hashCode());
        System.out.println(new HashCodeClass().hashCode());
        // 系统库
        System.out.println(System.getProperty("java.library.path"));
    }
}
