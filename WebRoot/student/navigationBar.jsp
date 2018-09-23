<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<p class="navbar-text navbar-left">计算机等级考试报名系统&nbsp</p>
			<a class="navbar-brand" href="student/studentIndex.jsp">首页</a> <a
				class="navbar-brand" href="student/other.jsp?page=1">考试介绍与常见问题</a> <a
				class="navbar-brand" href="student/other.jsp?page=2">考生须知</a> <a
				class="navbar-brand" href="student/other.jsp?page=3">报名流程</a> <a
				class="navbar-brand" href="download">下载</a>
		</div>
		<div>
			<p class="navbar-text navbar-right">&nbsp&nbsp</p>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">${sessionScope.loginUser.userName}<b
						class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="personInfo">基本信息</a></li>
						<li class="divider"></li>
						<li><a href="#">成绩查询</a></li>
						<li><a href="#">打印准考证</a></li>
						<li class="divider"></li>
						<li><a href="user_logout">退出登陆</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>
