package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //生成唯一订单号
        String orderId = System.currentTimeMillis()+""+userId;
        //创建一个order对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存order对象
        orderDao.SaveOrder(order);


        //遍历购物车中的商品项保存为订单项
        for(Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()){
            CartItem item = entry.getValue();

            OrderItem orderItem = new OrderItem(null,item.getName(),item.getCount(),item.getPrice(),item.getTotalPrice(),orderId);
            orderItemDao.saveOrderItem(orderItem);
            Book book = bookDao.queryBook(item.getId());
            book.setSales(book.getSales()+item.getCount());
            book.setStock(book.getStock()-item.getCount());
            bookDao.updateBook(book);
        }

        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryForOrders();
    }

    @Override
    public int sendOrder(String orderId) {
        return orderDao.changeOrderStatus(orderId,1);
    }

    @Override
    public List<Order> showMyOrders(int userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderDao.queryOrderItemByOrderId(orderId);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderDao.deleteOrderItemByOrderId(orderId);
        orderDao.deleteOrderByOrderId(orderId);
    }
}
