package com.li.javasenior_springboot.utils;



import com.li.javasenior_springboot.entity.bookList;
import com.li.javasenior_springboot.utils.JdbcConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.li.javasenior_springboot.utils.JdbcConnect.connection;


public class JdbcMethods {

    // 检索全部
    public static List<bookList> getAllBook() throws SQLException {

        // 连接对象
        Connection connection = null;
        // 预编译对象 PreparedStatement
        PreparedStatement preparedStatement = null;
        // 结果集
        ResultSet re = null;
        List<bookList> bookLists = new ArrayList<>();

        try{
            connection = connection();
            String sql = "select * from seniorjava.bookList";
            preparedStatement = connection.prepareStatement(sql);
            // 执行SQL
            re = preparedStatement.executeQuery();
            while(re.next()){
                bookList bookList = new bookList();
                bookList.setId(re.getInt("id"));
                bookList.setBookId(re.getInt("bookId"));
                bookList.setBookName(re.getString("bookName"));
                bookList.setBookAuthor(re.getString("bookAuthor"));
                bookList.setBookNumber(re.getInt("bookNumber"));
                bookList.setBookProfile(re.getString("bookProfile"));
                bookList.setInsertDate(re.getString("insertDate"));
                bookLists.add(bookList);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            // 释放资源
            if (re != null)
                re.close();

            if (preparedStatement != null)
                preparedStatement.close();

            if (connection != null)
                connection.close();

            return bookLists;
        }


    }





}

