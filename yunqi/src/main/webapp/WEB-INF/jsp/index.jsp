<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>

<html>

<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8" />
	<title>企信</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="static/images/watchmen.ico">
	<link href="static/plugs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/common.css" rel="stylesheet">
</head>

<body>

	<jsp:include page="header.jsp" />
	<jsp:include page="footer.jsp" />
	
	<script src="static/js/jquery/jquery.min.js"></script>
	<script src="static/plugs/bootstrap/js/bootstrap.min.js"></script>
	
</body>

</html>