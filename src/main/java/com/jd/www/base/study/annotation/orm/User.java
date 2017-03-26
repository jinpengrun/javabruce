package com.jd.www.base.study.annotation.orm;

/**
 * Created by zhujinpeng on 16/1/7.
 */
@Table(tableName="user")
public class User {
    @TableField(name = "user_id", isPrimaryKey = true)
    private int userId;
    @TableField(name = "user_name")
    private String userName;
    @TableField(name = "user_age")
    private int age;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
