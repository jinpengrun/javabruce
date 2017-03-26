package com.jd.www.book.effective_java.seven.fortythree;

import com.google.common.collect.Lists;
import com.jd.www.base.study.annotation.orm.User;

import java.util.Collections;
import java.util.List;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/24 下午9:13</li>
 * <li>function:</li>
 * </ul>
 */
public class MethodTest {
    private final List<User> userList = null;

    private static final User[] EMPTY_USER_ARRAY = new User[0];


    //尽量返回零长度的 数组 或者 集合 ，这样就不会 要求客户端必须提供额外的代码来处理null 返回值
    public User[] getUsers(){
        if(userList.size()==0){
            return null;
        }
        return null;
    }


    public User[] getUser(){
        return userList.toArray(EMPTY_USER_ARRAY);
    }

    //the right way to return a copy of a collection
    public List<User> getUserList(){
        if(userList.isEmpty()){
            return Collections.emptyList();
        }else
            return Lists.newArrayList(userList);
    }



}
