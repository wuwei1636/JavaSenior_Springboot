package com.li.javasenior_springboot;

import com.li.javasenior_springboot.controller.bookListController;
import com.li.javasenior_springboot.entity.bookList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.li.javasenior_springboot.mapper.bookListMapper;

@SpringBootTest
class JavaSeniorSpringbootApplicationTests {

    @Autowired
    private bookListMapper bookListMapper;


    @Test
    void contextLoads() throws ClassNotFoundException, SQLException {



        List<bookList> listList =
                bookListMapper.getBooks(null,
                        null,
                        null);

        System.out.println(listList);
        System.out.println("====================");
        String url = "jdbc:mysql://localhost:3306/seniorjava?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123456";


        // 1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.连接数据库,代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "select * from seniorjava.bookList";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<bookList> bookLists = new ArrayList<>();
        // 执行SQL
        ResultSet re = preparedStatement.executeQuery();
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
        System.out.println(bookLists);
        preparedStatement.close();
        connection.close();

        assertTrue(listList.equals(bookLists));

    }

    @Test
    void add(){
        String dd = date();
        bookList bo = new bookList(10,10010,
                "兄弟","余华",
                10,"兄弟",dd);
        bookListMapper.addBook(bo);
    }

    @Test
    void delete(){
        bookListMapper.deleteBook(10010);
    }

    @Test
    void change(){
        String dd = date();
        bookList bo = new bookList(10,10010,
                "兄弟","余华",
                10,"mkabaka",dd);
        bookListMapper.changeBook(bo);
    }


    @Test
    void showFunctions(){
        Class bookListControllerClass = bookListController.class;
//        Class booklist = bookList.class;

//        List<Map<String,String>> mapList = new ArrayList<>();
//        Field[] fields = booklist.getDeclaredFields();
//        for (Field field : fields) {
//            Map<String,String> map = new HashMap<>();
//
//            map.put("name",field.getName());
//
//            String fieldClass = field.getDeclaringClass().getName();
//            String[] classPart = fieldClass.split("\\.");
//            map.put("class",classPart[classPart.length - 1]);
//            map.put("type",field.getType().getName());
//            map.put("Modifier",Modifier.toString(field.getModifiers()));
//            System.out.println(map);
//            mapList.add(map);
//            System.out.println(mapList);
//            System.out.println("========================");
//        }
//
//        for(Map<String, String> list : mapList){
//            for(String key:list.keySet()){
//                System.out.println(list.get(key));
//            }
//        }
//
        List<Map<String, Object>> mapList2 = new ArrayList<>();
        Method[] methods = bookListControllerClass.getDeclaredMethods();
        System.out.println(methods.length);
        for(Method method: methods){
            Map<String,Object> map = new HashMap<>();
//            System.out.println("名称：" + method.getName());
//            System.out.println("所在类：" + method.getDeclaringClass().getName());
//            System.out.println("返回类型：" + method.getReturnType().getName());
//            System.out.println("修饰符：" + Modifier.toString(method.getModifiers()));
//            System.out.println("参数类型：");

            map.put("name",method.getName());

            String methodClass = method.getDeclaringClass().getName();
            String[] classPart1 = methodClass.split("\\.");
            map.put("class",classPart1[classPart1.length - 1]);

//            map.put("class",method.getDeclaringClass().getName());
            map.put("resultType",method.getReturnType().getName());
            map.put("modifier", Modifier.toString(method.getModifiers()));
            Parameter[] parameters = method.getParameters();
            List<String> parameterss = new ArrayList<>();
            for (Parameter parameter : parameters) {
                String[] classPart = parameter.getType().getName().split("\\.");
                parameterss.add(classPart[classPart.length - 1]);
            }
            map.put("patamters",parameterss);

            Class[] exceptions = method.getExceptionTypes();
            List<String> exceptionn = new ArrayList<>();
            for (Class exception : exceptions) {
                String[] classPart = exception.getName().split("\\.");
                exceptionn.add(classPart[classPart.length - 1]);
            }

            map.put("exceptions",exceptionn);

//            System.out.println(map);
            mapList2.add(map);
        }
        System.out.println(mapList2);
    }


    String date(){
        java.util.Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dd = formatter.format(date);
        System.out.println(dd);
        return dd;
    }

}
