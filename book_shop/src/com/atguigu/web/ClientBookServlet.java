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

public class ClientBookServlet extends BaseServlet{
     BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的参数pageNo和pageSize
        int pageNo = WEBUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize =WEBUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用BookSerive.page(pageNo,pageSize):page对象
        Page<Book> page = bookService.page(pageNo,pageSize);

        page.setUrl("client/bookServlet?action=page");
        //保存page对象到request域中
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的参数pageNo和pageSize
        int pageNo = WEBUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize =WEBUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        int min = WEBUtils.parseInt(req.getParameter("min"),0);
        int max = WEBUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        //调用BookSerive.page(pageNo,pageSize):page对象
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        if(req.getParameter("min")!=null){
            sb.append("&min="+req.getParameter("min"));
        }
        if(req.getParameter("max")!=null){
            sb.append("&max="+req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        //保存page对象到request域中
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }


    protected void pageByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的参数pageName
        String bookName = req.getParameter("bookName");

        //调用BookSerive.pageByName(bookName):page对象
        Page<Book> page = bookService.pageByName(bookName);

        //保存page对象到request域中
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }

    protected void pagePreSale(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的参数pageNo和pageSize
        int pageNo = WEBUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize =WEBUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用BookSerive.page(pageNo,pageSize):page对象
        Page<Book> page = bookService.pagePreSale(pageNo,pageSize);

        page.setUrl("client/bookServlet?action=pagePreSale");
        //保存page对象到request域中
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/client/presale.jsp").forward(req,resp);

    }

}
