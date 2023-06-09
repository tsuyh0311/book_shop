package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;

import java.util.List;


public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();

        //设置每页数量
        page.setPageSize(pageSize);
      //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
       //求总页码
        Integer pageTotal = pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0){
            pageTotal++;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        //边界限制，放在javabean中更好

        //设置当前页码
        page.setPageNo(pageNo);
        //求当前页数据的开始索引
        Integer begin=(page.getPageNo()-1)*pageSize;
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        //设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBook(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBook(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();

        //设置每页数量
        page.setPageSize(pageSize);
        //求价格区间总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        //求价格区间总页码
        Integer pageTotal = pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0){
            pageTotal++;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        //边界限制，放在javabean中更好

        //设置当前页码
        page.setPageNo(pageNo);
        //求当前页数据的开始索引
        Integer begin=(page.getPageNo()-1)*pageSize;
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        //设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByName(String bookName) {
        Page<Book> page = new Page<Book>();

        //设置每页数量
        page.setPageSize(4);

        //设置总页码
        page.setPageTotal(1);
        //边界限制，放在javabean中更好

        //设置当前页码
        page.setPageNo(1);

        List<Book> items = bookDao.queryForPageItemsByName(bookName);
        //设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pagePreSale(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();

        //设置每页数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0){
            pageTotal++;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        //边界限制，放在javabean中更好

        //设置当前页码
        page.setPageNo(pageNo);
        //求当前页数据的开始索引
        Integer begin=(page.getPageNo()-1)*pageSize;
        List<Book> items = bookDao.queryForPageItemsPreSale(begin,pageSize);
        //设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public void addBookForPreSale(Book book) {
        bookDao.addBookForPreSale(book);
    }

    @Override
    public void updateBookPreSale(Book book) {
        bookDao.updateBookPreSale(book);
    }

    @Override
    public Book queryBookByIdPreSale(Integer id) {
        return bookDao.queryBookPreSale(id);
    }

    @Override
    public void deleteBookByIdPreSale(int id) {
        bookDao.deleteBookPreSale(id);
    }
}
