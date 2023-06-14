package com.li.javasenior_springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class bookList {

    int id;
    Integer bookId;
    String bookName;
    String bookAuthor;
    int bookNumber;
    String bookProfile;
    String insertDate;

}
