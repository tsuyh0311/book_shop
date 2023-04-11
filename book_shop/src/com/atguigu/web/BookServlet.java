package com.atguigu.web;


import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WEBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class BookServlet extends BaseServlet{


    BookService bookService = new BookServiceImpl();

    protected void pageForPreSale(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数pageNo和pageSize
        int pageNo = WEBUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize =WEBUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //调用BookSerive.page(pageNo,pageSize):page对象
        Page<Book> page = bookService.pagePreSale(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=pagePreSale");
        //保存page对象到request域中
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/manager/book_managerPre.jsp").forward(req,resp);

    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数pageNo和pageSize
        int pageNo = WEBUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize =WEBUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //调用BookSerive.page(pageNo,pageSize):page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //保存page对象到request域中
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }


    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        BigDecimal price = WEBUtils.parseBigDemical(req.getParameter("price"),null);
        BigDecimal discount = WEBUtils.parseBigDemical(req.getParameter("discount"),null);
        String author = req.getParameter("author");
        String imgPath = req.getParameter("imgPath");
        Integer sales = WEBUtils.parseInt(req.getParameter("sales"), 100);
        Integer stock = WEBUtils.parseInt(req.getParameter("stock"),200);

        Book book = new Book(null,name,author,price,discount,sales,stock,imgPath);
        //Book book = WEBUtils.copyParamToBean(req.getParameterMap(),new Book());
         bookService.addBook(book);
         int pageNo = WEBUtils.parseInt(req.getParameter("pageNo"),0);
         pageNo+=1;
         req.setAttribute("book",book);
         //list(req,resp);刷新浏览器会导致表单重复提交
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void addPreSale(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        BigDecimal price = WEBUtils.parseBigDemical(req.getParameter("price"),null);
        BigDecimal discount = WEBUtils.parseBigDemical(req.getParameter("discount"),null);
        String author = req.getParameter("author");
        String imgPath = req.getParameter("imgPath");
        Integer sales = WEBUtils.parseInt(req.getParameter("sales"), 100);
        Integer stock = WEBUtils.parseInt(req.getParameter("stock"),200);
        Book book = new Book(null,name,author,price,discount,sales,stock,imgPath);
        //Book book = WEBUtils.copyParamToBean(req.getParameterMap(),new Book());
        bookService.addBookForPreSale(book);
        int pageNo = WEBUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        req.setAttribute("book",book);
        //list(req,resp);刷新浏览器会导致表单重复提交
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=pageForPreSale&pageNo="+1);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WEBUtils.parseInt(req.getParameter("id"),0);
        bookService.deleteBookById(id);
        int pageNo = WEBUtils.parseInt(req.getParameter("pageNo"),0);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void deletePreSale(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WEBUtils.parseInt(req.getParameter("id"),0);
        bookService.deleteBookByIdPreSale(id);
        int pageNo = WEBUtils.parseInt(req.getParameter("pageNo"),0);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=pageForPreSale&pageNo="+pageNo);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book =WEBUtils.copyParamToBean(req.getParameterMap(),new Book());
        bookService.updateBook(book);
        int pageNo = WEBUtils.parseInt(req.getParameter("pageNo"),0);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void updatePreSale(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book =WEBUtils.copyParamToBean(req.getParameterMap(),new Book());
        bookService.updateBookPreSale(book);
        int pageNo = WEBUtils.parseInt(req.getParameter("pageNo"),0);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=pageForPreSale&pageNo="+pageNo);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WEBUtils.parseInt(req.getParameter("id"),0);

        req.setAttribute("book",bookService.queryBookById(id));
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);

    }

    protected void getBookPreSale(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WEBUtils.parseInt(req.getParameter("id"),0);

        req.setAttribute("book",bookService.queryBookByIdPreSale(id));
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);

    }



    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           //通过bookservice查询所有图书
           List<Book> books = bookService.queryBooks();
           //将查询到的books存入req域中
          req.setAttribute("books",books);
          //请求转发
          req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }

    protected void logOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁session
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());

    }

}
