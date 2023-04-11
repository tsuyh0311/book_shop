package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

public interface BookDao {


    public int addBook(Book book);


    public int deleteBook(Integer id);


    public int updateBook(Book book);


    public Book queryBook(Integer id);

    public List<Book> queryBooks();

    public Integer queryForPageTotalCount();

    public List<Book> queryForPageItems(Integer begin, int pageSize);


    List<Book> queryForPageItemsByPrice(Integer begin, int pageSize, int min, int max);


    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByName(String bookName);

    List<Book> queryForPageItemsPreSale(Integer begin, int pageSize);

    int addBookForPreSale(Book book);

    int updateBookPreSale(Book book);

    public Book queryBookPreSale(Integer id);

    int deleteBookPreSale(int id);
}
