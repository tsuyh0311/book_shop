package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;


public interface OrderService {

    public String createOrder(Cart cart, Integer userId);


    public List<Order> showAllOrders();

    public int sendOrder(String orderId);


    List<Order> showMyOrders(int userId);

    List<OrderItem> showOrderDetail(String orderId);

    void deleteOrder(String orderId);
}
