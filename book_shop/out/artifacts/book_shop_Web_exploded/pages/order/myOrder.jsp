<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%@ include file="/pages/common/header.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
		<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>操作</td>
			</tr>		
		<c:forEach items="${sessionScope.myOrders}" var="myOrder">
			<tr>
				<td><fmt:formatDate value="${myOrder.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${myOrder.price}</td>
				<td>
					<c:if test="${myOrder.status==0}">未发货</c:if>
					<c:if test="${myOrder.status==1}">已发货</c:if>
					<c:if test="${myOrder.status==2}">已完成</c:if>
				</td>
				<td><a href="orderServlet?action=showOrderDetail&orderId=${myOrder.orderId}">查看详情</a></td>
				<td><a href="orderServlet?action=deleteOrder&orderId=${myOrder.orderId}">退货</a></td>
			</tr>

		</c:forEach>

		</table>
		
	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>