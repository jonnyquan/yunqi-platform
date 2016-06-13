<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>  

<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">WATCHMEN</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="monitor dropdown active">
					<a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">接口监控<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="monitor/controller">CONTROLLER</a></li>
						<li><a href="monitor/service">SERVICE</a></li>
					</ul>
				</li>
				<li class="cache dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">缓存管理<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="cache/controller">CONTROLLER</a></li>
						<li><a href="cache/service">SERVICE</a></li>
						<li><a href="cache/mapper">MAPPER</a></li>
					</ul>
				</li>
				<li><a href="project/${defProject.id}/sysprocess">系统进程</a></li>
				<li><a href="project/${defProject.id}/javaapp">JAVA程序</a></li>
				<li style="display: none;"><a href="#">数据库</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">消息中心</a></li>
				<li style="display: none;"><a href="#">登录</a></li>
				<li><a href="logout">注销</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>