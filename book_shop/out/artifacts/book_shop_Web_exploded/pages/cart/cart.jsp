	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@ include file="/pages/common/header.jsp"%>

	<script type="text/javascript">
		$(function (){

			$("a.deleteItem").click(function (){
				confirm("你确定要删除【"+ $(this).parent().parent().find("td:first").text()+"】吗")

			})

			$("a.clear").click(function (){
				confirm("您确定要清空购物车吗？")
			})

			$(".updateCount").change(function (){
				var name = $(this).parent().parent().find("td:first").text();
				var count =$(this).val();
				var id =$(this).attr("bookId");
				if(confirm("你是否要将【"+ name +"】的数量更改为"+count+"吗？")){
                      location.href ="cartServlet?action=updateCount&count="+count+"&id="+id;
				}else{
					this.value = this.defaultValue;
				}

			})


		})

	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>		
      <c:if test="${empty sessionScope.cart.items}">
		  <tr>
			  <td colspan="5"><a href="index.jsp">亲，购物车没有商品，点击此处浏览商品吧！</a></td>
		  </tr>

	  </c:if>
			<c:if test="${not empty sessionScope.cart.items}">
			<c:forEach items="${sessionScope.cart.items}" var="cartItem">
				<tr>
					<td>${cartItem.value.name}</td>
					<td><input class="updateCount" style="width: 50px" type="text" bookId="${cartItem.value.id}"  value="${cartItem.value.count}"></td>
					<td>${cartItem.value.price}</td>
					<td>${cartItem.value.totalPrice}</td>
					<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${cartItem.value.id}">删除</a></td>
				</tr>

			</c:forEach>
				<tr>
					<div class="cart_info">
						<td><span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span></td>
						<td><span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span></td>
						<td><span class="cart_span"><a class="clear" href="cartServlet?action=clear">清空购物车</a></span></td>
						<td><span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span></td>
					</div>
				</tr>

			</c:if>
			
		</table>

	</div>
	
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>