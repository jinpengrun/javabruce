package com.jd.www.base.study.annotation.orm;

/**
 * Created by zhujinpeng on 16/1/7.
 */
public class UsertestDao extends BaseOrmSupport<User> {

    public static void main(String[]args){
        User user = new User();
        user.setAge(10);
        user.setUserId(101);
        user.setUserName("1001001");

        UsertestDao usertestDao = new UsertestDao();
        usertestDao.save(user);
    }
}
