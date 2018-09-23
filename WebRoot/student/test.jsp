<%-- <%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.liang.entity.Download"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
<link rel='stylesheet' href='css/style.css'>
<script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>



</head>
<body>
	<div>
		<nav class="navbar navbar-inverse navbar-fixed-top"
			id="sidebar-wrapper" role="navigation">
		<ul class="nav sidebar-nav">
			<li class="sidebar-brand"><a href="#"> Bootstrap 3 </a></li>
			<li><a href="#"> <i class="fa fa-fw fa-home"> </i> Home
			</a></li>
			<li><a href="#"> <i class="fa fa-fw fa-folder"> </i> Page
					one
			</a></li>
			<li><a href="#"> <i class="fa fa-fw fa-file-o"> </i> Second
					page
			</a></li>
			<li><a href="#"> <i class="fa fa-fw fa-cog"> </i> Third page
			</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="fa fa-fw fa-plus"> </i>
					Dropdown <span class="caret"> </span>
			</a>
				<ul class="dropdown-menu" role="menu">
					<li class="dropdown-header">Dropdown heading</li>
					<li><a href="#"> Action </a></li>
					<li><a href="#"> Another action </a></li>
					<li><a href="#"> Something else here </a></li>
					<li><a href="#"> Separated link </a></li>
					<li><a href="#"> One more separated link </a></li>
				</ul></li>
			<li><a href="#"> <i class="fa fa-fw fa-bank"> </i> Page four
			</a></li>
			<li><a href="#"> <i class="fa fa-fw fa-dropbox"> </i> Page 5
			</a></li>
			<li><a href="#"> <i class="fa fa-fw fa-twitter"> </i> Last
					page
			</a></li>
		</ul>
		</nav>
	</div>
</body> --%>