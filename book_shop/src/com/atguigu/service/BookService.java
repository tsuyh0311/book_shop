package com.atguigu.service;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

public interface BookService {

    public Page<Book> page(int pageNo, int pageSize);


    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);

    Page<Book> pageByName(String bookName);

    Page<Book> pagePreSale(int pageNo, int pageSize);

    void addBookForPreSale(Book book);

    void updateBookPreSale(Book book);

    public Book queryBookByIdPreSale(Integer id);

    void deleteBookByIdPreSale(int id);
}
