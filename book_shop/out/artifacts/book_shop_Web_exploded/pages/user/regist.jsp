<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<!--base标签，永久固定相对路径     -->
	<%@ include file="/pages/common/header.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>

	<script type="text/javascript">
		$(function (){

			$("#username").blur(function (){
				var username = this.value;


				$.getJSON("http://localhost:8080/book_shop/userServlet","action=ajaxExistsUsername&username="+username,function (data){
                       if(data.existsUsername){
						   $("span.errorMsg").text("用户名已存在！");
					   }else{
						   $("span.errorMsg").text("用户名可以使用");
					   }
				})

			})

			$("#code_img").click(function (){
				<%--加上时间戳，越过浏览器缓存 --%>
				this.src = "${pageContext.getAttribute("basePath")}kaptcha.jpg?date="+new Date();
			})

			$("#sub_btn").click(function (){
				//验证用户名格式是否正确
				var patt = /^\w{5,12}$/;
				var usernametext = $("#username").val();
                if(!patt.test(usernametext)){
					$("span.errorMsg").text("用户名格式不规范！");
					return false;
				}
               //验证密码格式是否正确

				var passwordtext = $("#password").val();
				if(!patt.test(passwordtext)){
					$("span.errorMsg").text("密码格式不规范！");
					return false;
				}
              //验证两次密码是否一致
                var repasswordtext = $("#repwd").val();
				if(repasswordtext!=passwordtext){
					$("span.errorMsg").text("密码输入不一致！");
					return false;
				}

				//验证码不能为空
				// var codetext = $("#code").val();
				// codetext = $.trim(codetext); //去空格
				// if(codetext==null||codetext==""){
				// 	$("span.errorMsg").text("验证码不能为空！");
				// 	return false;
				// }

				$("span.errorMsg").text(" ");

			})


		})

	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册会员</h1>
								<span class="errorMsg">${requestScope.get("msg")}</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username" value="${requestScope.get("username")}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" value="${requestScope.email}"/>
									<br />
									<br />
<%--									<label>验证码：</label>--%>
<%--									<input class="itxt" type="text" style="width: 150px;" name="code" id="code"/>--%>
<%--									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; height: 40px;width: 80px">--%>
<%--									<br />--%>
<%--									<br />--%>
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>