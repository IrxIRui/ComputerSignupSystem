<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta name="description" content="武汉科技大学 - 计算机等级考试 报名系统">
<!--为了让 Bootstrap 开发的网站对移动设备友好，确保适当的绘制和触屏缩放，需要在网页的 head 之中添加 viewport meta 标签-->
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<title>武汉科技大学计算机等级考试 报名系统</title>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>

	<!-- 导航栏 -->
	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid" style="background-color: #214895">
		<dir class="navbar-header">
			<a href="index.jsp"><img alt="网站首页" style="height:40px"
				src="image/indexhead.png"></a>
			<a href="student/search.jsp"><img alt="查询成绩" style="height:40px"
				src="image/search.png"></a>
		</dir>
	</div>
	<!-- 内容显示 -->
	    <!-- 如果登陆直接显示，如果未登录，则查询 -->
	
</body>
</html>
