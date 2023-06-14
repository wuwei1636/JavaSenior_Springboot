package com.li.javasenior_springboot.controller;


import com.li.javasenior_springboot.entity.bookList;
import com.li.javasenior_springboot.utils.JdbcMethods;
import com.li.javasenior_springboot.mapper.bookListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@Component
public class bookListController {

    @Autowired
    private bookListMapper bookListMapper;

    // 获取所有图书
    @RequestMapping("/getAllBook")
    public List<bookList> getAllBook() throws SQLException{
        List<bookList> bookListList = JdbcMethods.getAllBook();
//        List<bookList> bookLists = bookListMapper.getAllBook();

        return bookListList;
    }

    // 查询图书
    @RequestMapping("/getBooks")
    public List<bookList> getBooks(@RequestParam("bookId") String bookId ,
                                   @RequestParam("bookName") String bookName,
                                   @RequestParam("bookAuthor")String bookAuthor) {
        Integer id = null;
        if(!bookId.equals("") && bookId != null){
            id = Integer.valueOf(bookId);
        }
        List<bookList> bookLists = bookListMapper.getBooks(id,bookName,bookAuthor);

        return bookLists;
    }

    // 新增图书
    @RequestMapping("/addBook")
    public boolean addBook(@RequestParam("bookId") String bookId ,
                           @RequestParam("bookName") String bookName,
                           @RequestParam("bookAuthor")String bookAuthor,
                           @RequestParam("bookNumber") int bookNumber,
                           @RequestParam("bookProfile") String bookProfile
    ){
        Integer id = null;
        if(!bookId.equals("") && bookId != null){
            id = Integer.valueOf(bookId);
        }
        try{
            check(id);
        }catch (IllegalArgumentException e){
            System.out.println(e);
            return false;
        }
        String date = date();
        bookList bo = new bookList(10,id,bookName,
                bookAuthor,bookNumber,bookProfile,date);
        boolean flag = bookListMapper.addBook(bo);
        return flag;
    }

    // 删除书籍
    @RequestMapping("/deleteBook")
    public boolean deleteBook(@RequestParam("bookId") String bookId){
        boolean flag = false;
        Integer id = null;
        if(!bookId.equals("") && bookId != null){
            id = Integer.valueOf(bookId);
        }

        flag = bookListMapper.deleteBook(id);
        return flag;

    }

    // 修改书籍
    @RequestMapping("/changeBook")
    public boolean changeBook(@RequestParam("bookId") String bookId ,
                              @RequestParam("bookName") String bookName,
                              @RequestParam("bookAuthor")String bookAuthor,
                              @RequestParam("bookNumber") int bookNumber,
                              @RequestParam("bookProfile") String bookProfile
    ){
        Integer id = null;
        if(!bookId.equals("") && bookId != null){
            id = Integer.valueOf(bookId);
        }
        try{
            check(id);
        }catch (IllegalArgumentException e){
            System.out.println(e);
            return false;
        }
        String bookname = "";
        String[] parts = bookName.split("[《,》]");
        if(parts.length > 1) bookname = parts[1];
        else bookname = bookName;
        String date = date();
        bookList bo = new bookList(10,id,bookname,
                bookAuthor,bookNumber,bookProfile,date);

        boolean flag = bookListMapper.changeBook(bo);
        return flag;
    }

    // 返回属性方法


    // 获取当前时间
    public String date(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dd = formatter.format(date);
        System.out.println(dd);
        return dd;
    }

    // 设置bookId的异常处理
    public void check(Integer bookId){
        if(bookId < 10010){
            throw new IllegalArgumentException("bookId的数值在10010以上，请输入合适的值");
        }
    }
}
