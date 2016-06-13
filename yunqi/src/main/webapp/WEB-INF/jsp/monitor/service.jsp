<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="static/images/watchmen.ico">
	<link href="static/plugs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/common.css" rel="stylesheet">
	<style type="text/css">
		table {
			margin-top: 10px;
		}
		table th {
			text-align: center;
		}
	</style>
</head>

<body>

	<jsp:include page="../header.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-md-10" id="main">
				<div class="row">
					<div class="col-xs-6 col-md-12">
						<div class="server">
							<h2 class="page-header">
								<span>SERVICE接口用量统计</span> <small style="margin-left: 10px"></small>
							</h2>
							<div class="btn-group" role="group" style="margin-bottom: 5px">
								<button type="button" class="btn btn-default active" name="chartType" value="MEMORY">堆内存</button>
								<button type="button" class="btn btn-default" name="chartType" value="CPU">CPU</button>
								<button type="button" class="btn btn-default" name="chartType" value="THREAD">线程</button>
							</div>

							<div class="btn-group pull-right action" role="group" style="margin-bottom: 5px">
								<button type="button" class="btn btn-default active" name="chartLevel" value="1">一小时</button>
								<button type="button" class="btn btn-default" name="chartLevel" value="2">六小时</button>
								<button type="button" class="btn btn-default dn" name="chartLevel" value="3">一天</button>
								<button type="button" class="btn btn-default dn" name="chartLevel" value="4">一周</button>
								<div class="btn-group">
									<button class="btn btn-default dropdown-toggle action" data-toggle="dropdown">
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu pull-right">
										<li><a href="javascript:void(0)" class="add">添加</a></li>
										<li><a href="javascript:void(0)" class="modify">修改</a></li>
										<li class="divider"></li>
										<li><a href="javascript:void(0)" class="delete">删除</a></li>
									</ul>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<table class="table table-bordered table-striped">
										<tr>
											<th>接口</th>
											<th>总数</th>
											<th>缓存</th>
											<th>总时</th>
											<th>平均</th>
											<th>最长</th>
											<th>首次记录时间</th>
											<th>最后记录时间</th>
										</tr>
										<c:forEach var="mi" items="${mis}" varStatus="status">
											<tr>
												<fmt:formatNumber var="averageTime" value="${mi.sumTime/mi.callCount}" pattern="#" />
												<td><span class="interfaceName"><a title="${ mi.className }">${ mi.simpleClassName }.${ mi.methodName }</a></span></td>
												<td>${ mi.callCount }</td>
												<td>${ mi.useCache }</td>
												<td>${ mi.sumTime }</td>
												<td>${ averageTime }</td>
												<td>${ mi.maxTime }</td>
												<td><fmt:formatDate value="${ mi.firstTime }" pattern="yyyy/MM/dd HH:mm:ss" /></td>
												<td><fmt:formatDate value="${ mi.lastTime }" pattern="yyyy/MM/dd HH:mm:ss" /></td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-2" id="javaapp-list">
				<div class="list-group" style="margin-top: 150px"></div>
			</div>
		</div>
		<hr>
	</div>
	
	<jsp:include page="../footer.jsp" />

	<script src="static/js/jquery/jquery.min.js"></script>
	<script src="static/plugs/bootstrap/js/bootstrap.min.js"></script>
	<script src="static/js/web/monitor/service.js"></script>
	
</body>

</html>