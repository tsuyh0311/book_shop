package com.atguigu.web;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.WEBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class OrderServlet extends BaseServlet{
   OrderService orderService = new OrderServiceImpl();
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取cart购物车对象
       Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取userId，此时要判断是否登陆了
        User loginUser = (User) req.getSession().getAttribute("user");
        if(loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = loginUser.getId();

        String orderId = orderService.createOrder(cart,userId);


        req.getSession().setAttribute("orderId",orderId);
            //调用orderService.createOrder(Cart,userId);生成订单
        cart.clear();
        //重定向
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }


    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取所有订单对象
        List<Order> orders = orderService.showAllOrders();

        //将订单集合存入seesion域中
        req.getSession().setAttribute("orders",orders);

        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }


    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取所有订单对象
        List<Order> orders = orderService.showAllOrders();
        req.getSession().setAttribute("orders",orders);


        orderService.sendOrder(req.getParameter("orderId"));

        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前用户的id
        int userId = WEBUtils.parseInt(req.getParameter("userId"),0);
        //通过用户id查询订单
        List<Order> myOrders = orderService.showMyOrders(userId);
        req.getSession().setAttribute("myOrders",myOrders);

        req.getRequestDispatcher("/pages/order/myOrder.jsp").forward(req,resp);
    }


    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取订单orderId
       String orderId = req.getParameter("orderId");
       //通过orderId查询该订单的购物车信息
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        req.getSession().setAttribute("orderItems",orderItems);

        req.getRequestDispatcher("/pages/order/orderItem.jsp").forward(req,resp);
    }

    protected void deleteOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取订单orderId
        String orderId = req.getParameter("orderId");
        //通过orderId删除订单
        orderService.deleteOrder(orderId);

        User loginUser = (User) req.getSession().getAttribute("user");
        Integer id = loginUser.getId();
        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showMyOrders&userId="+id);
    }

}
