<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta name="description" content="武汉科技大学  - 计算机等级考试 报名系统">
<title>武汉科技大学计算机等级考试 报名系统</title>
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link
	href="/try/bootstrap/twitter-bootstrap-v2/docs/assets/css/example-fluid-layout.css"
	rel="stylesheet">
<link
	href="/try/bootstrap/twitter-bootstrap-v2/docs/assets/css/bootstrap.css"
	rel="stylesheet">
<link
	href="https://cdn.bootcss.com/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
<%
	Calendar a = Calendar.getInstance();//出身日期时间选择列表
	int currentYear = a.get(Calendar.YEAR);
%>
</head>

<body>
	
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<p class="navbar-text navbar-left">计算机等级考试报名系统&nbsp</p>
			<a class="navbar-brand" href="student/studentIndex.jsp">管理员首页</a> <a
				class="navbar-brand" href="student/other.jsp?page=1">用户信息管理</a><a
				class="navbar-brand" href="download">首页通知管理</a>
				<a
				class="navbar-brand" href="download">下载文件添加</a>
		</div>
		<div>
			<p class="navbar-text navbar-right">&nbsp&nbsp</p>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">管理员：Gabriel<%-- ${sessionScope.loginUser.userName} --%><b
						class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="#">基本信息</a></li>
						<li class="divider"></li>
						<li><a href="user_logout">安全退出</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>
<div class="col-md-3">
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
				<div class="well sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header">报名</li>
						<li class="active"><a href="student/studentIndex.jsp">报名信息设置</a></li>
						<li><a href="student/selectSubjects.jsp">考试类型添加</a></li>
						<li><a href="student/studentIndex.jsp">考试等级添加</a></li>
						<li class="nav-header">导航信息管理</li>
						<li><a href="student/studentIndex.jsp">网站须知管理</a></li>
						<li><a href="student/studentIndex.jsp">考试介绍与常见问题管理</a></li>
					</ul>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
		</div>
	</div>
</div>

</body>
</html>
