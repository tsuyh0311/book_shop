package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;


public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int SaveOrder(Order order) {
        String sql = "insert into t_order( `order_id` , `create_time` , `price` , `status` , `user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryForOrders() {
        String sql = "select order_id orderId,create_time createTime,price,status,user_id userId from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public int changeOrderStatus(String orderId,int status) {
        String sql ="update t_order set status=? where order_id=?";
        return update(sql,status,orderId);
    }

    @Override
    public List<Order> queryOrdersByUserId(int userId) {
        String sql = "select order_id orderId,create_time createTime,price,status,user_id userId from t_order where user_id=?";
        return queryForList(Order.class,sql,userId);
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "select id,name,count,price from t_order_item where order_id=?";
        return queryForList(OrderItem.class,sql,orderId);
    }

    @Override
    public void deleteOrderItemByOrderId(String orderId) {
        String sql ="delete from t_order_item where order_id=?";
        update(sql,orderId);
    }

    @Override
    public void deleteOrderByOrderId(String orderId) {
        String sql ="delete from t_order where order_id=?";
        update(sql,orderId);
    }


}
