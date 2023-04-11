<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/7/5
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临网上书城</span>
    <a href="orderServlet?action=showMyOrders&userId=${sessionScope.user.id}">查看个人订单</a>
    <a href="orderServlet?action=showAllOrders">订单</a>
    <a href="manager/bookServlet?action=logOut">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>

