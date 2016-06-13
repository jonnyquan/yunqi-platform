<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html>

<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8" />
	<title>企信</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<meta name="MobileOptimized" content="320">
	<link rel="shortcut icon" href="static/images/watchmen.ico" />
	<link rel="stylesheet" href="static/plugs/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/login.css">
</head>

<body>

	<div class="container">

		<form class="form-signin" action="login" method="post">
			<h2 class="form-signin-heading">请登录</h2>
			<label for="inputEmail" class="sr-only">Email address</label> 
			<input type="text" id="username" name="username" value="admin" class="form-control" placeholder="用户名" required autofocus> 
			<label for="inputPassword" class="sr-only">Password</label> 
			<input type="password" id="password" name="password" value="123456" class="form-control" placeholder="密码" required>
			<div class="checkbox">
				<label> 
					<input type="checkbox" value="remember-me">记住我
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>

	</div>
	<!-- /container -->

</body>

</html>