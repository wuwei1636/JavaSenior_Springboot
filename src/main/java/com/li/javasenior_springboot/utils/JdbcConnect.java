package com.li.javasenior_springboot.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnect {

    // 数据库连接
    public static Connection connection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/seniorjava?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123456";

        // 1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.连接数据库,代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);
        return  connection;
    }

}
