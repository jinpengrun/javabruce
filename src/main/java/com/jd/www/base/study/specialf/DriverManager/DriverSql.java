package com.jd.www.base.study.specialf.DriverManager;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Created by zhujinpeng on 16/3/4.
 *
 * 这是 mysql 实现的 driver 接口
 */
public class DriverSql {

    public DriverSql() throws SQLException {
    }


    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }





    public static void main(String[]args) throws ClassNotFoundException {
        //让里面的static初始化类 进行 初始化
        Class.forName("com.mysql.jdbc.Driver");

        //DriverManager里有一个 特别好的 方法
        //即就是ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
        //Iterator driversIterator = loadedDrivers.iterator();  拿到所有 META-INF 里的
    }
}
