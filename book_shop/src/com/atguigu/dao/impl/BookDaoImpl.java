package com.atguigu.dao.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;

import java.util.List;


public class BookDaoImpl extends BaseDao implements BookDao{

    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book( `name` , `author` , `price` , `sales` , `stock` , `img_path`,`discount`) values(?,?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getDiscount());
    }

    @Override
    public int deleteBook(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=?,discount=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getDiscount(),book.getId());
    }

    @Override
    public Book queryBook(Integer id) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath,discount from t_book where id=?";
        return query(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath,discount from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) singleQuery(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(Integer begin, int pageSize) {
        String sql ="select id,name,author,price,sales,stock,img_path imgPath,discount from t_book limit ?,?";

        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql ="select count(*) from t_book where price between ? and ?";
        Number count = (Number) singleQuery(sql,min,max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByName(String bookName) {
        String sql ="select id,name,author,price,sales,stock,img_path imgPath,discount from t_book where name like concat('%',?,'%')";
        return queryForList(Book.class,sql,bookName);
    }

    @Override
    public List<Book> queryForPageItemsPreSale(Integer begin, int pageSize) {
        String sql ="select id,name,author,price,img_path imgPath,discount from p_book limit ?,?";

        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public int addBookForPreSale(Book book) {
        String sql = "insert into p_book( `name` , `author` , `price` , `img_path`,`discount`) values(?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getImgPath(),book.getDiscount());
    }

    @Override
    public int updateBookPreSale(Book book) {
        String sql = "update p_book set name=?,author=?,price=?,img_path=?,discount=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getImgPath(),book.getDiscount(),book.getId());
    }

    @Override
    public Book queryBookPreSale(Integer id) {
        String sql = "select id,name,author,price,img_path imgPath,discount from p_book where id=?";
        return query(Book.class,sql,id);
    }

    @Override
    public int deleteBookPreSale(int id) {
        String sql = "delete from p_book where id = ?";
        return update(sql,id);
    }

    @Override
    public List<Book> queryForPageItemsByPrice(Integer begin, int pageSize, int min, int max) {
        String sql="select id,name,author,price,sales,stock,img_path imgPath from t_book where price between ? and ? limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }


}
