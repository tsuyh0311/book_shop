package com.atguigu.dao;

import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;


public interface OrderDao {

    public int SaveOrder(Order order);


    public List<Order> queryForOrders();

    public int changeOrderStatus(String orderId,int status);

    List<Order> queryOrdersByUserId(int userId);

    List<OrderItem> queryOrderItemByOrderId(String orderId);

    void deleteOrderItemByOrderId(String orderId);

    void deleteOrderByOrderId(String orderId);
}
