package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WEBUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数，商品编号
        int id = WEBUtils.parseInt(req.getParameter("id"),0);
        //调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);
        //把图书的信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        System.out.println(req.getHeader("Referer"));
        System.out.println(cart);
        req.getSession().setAttribute("bookName",book.getName());
        //重定向回商品列表页面
        resp.sendRedirect(req.getHeader("Referer"));
    }


    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取要删除商品的id
        int id = WEBUtils.parseInt(req.getParameter("id"),0);

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.deleteItem(id);
        }
        //重定向回商品列表页面
        resp.sendRedirect(req.getHeader("Referer"));
    }


    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
        //重定向回商品列表页面
        resp.sendRedirect(req.getHeader("Referer"));

    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        int id = WEBUtils.parseInt(req.getParameter("id"),0);
        int count = WEBUtils.parseInt(req.getParameter("count"),0);

        if(cart!=null){
            cart.updateCount(id,count);
        }
        //重定向回商品列表页面
        resp.sendRedirect(req.getHeader("Referer"));

    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数，商品编号
        int id = WEBUtils.parseInt(req.getParameter("id"),0);
        //调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);
        //把图书的信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getDiscount(),book.getDiscount());
        //调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);


        System.out.println(cart);
        req.getSession().setAttribute("bookName",book.getName());
        //重定向回商品列表页面
        Map<String,Object> resultMap = new HashMap<>();

        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);



    }
}
