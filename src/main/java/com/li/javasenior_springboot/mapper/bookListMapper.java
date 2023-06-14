package com.li.javasenior_springboot.mapper;


import com.li.javasenior_springboot.entity.bookList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface bookListMapper {

    // 获取全部书籍信息
    List<bookList> getAllBook();

    // 根据编号，书名，作者搜索
    List<bookList> getBooks(@Param("bookId") Integer bookId ,
                            @Param("bookName") String bookName,
                            @Param("bookAuthor")String bookAuthor
    );

    // 添加书籍
    boolean addBook(bookList bookList);

    // 删除书籍
    boolean deleteBook(@Param("bookId") Integer bookId);

    // 修改书籍
    boolean changeBook(bookList bookList);

}
