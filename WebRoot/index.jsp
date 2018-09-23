<%@page import="org.apache.struts2.ServletActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.liang.entity.NoticesInfo"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="com.opensymphony.xwork2.ActionSupport"%>
<%@ page import="java.util.Map"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta name="description" content="武汉科技大学  - 计算机等级考试 报名系统">
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
<link href="CSS/index.css" rel="stylesheet" type="text/css">
<link href="CSS/head.css" rel="stylesheet" type="text/css">
<link href="CSS/login.css" rel="stylesheet" type="text/css">

<!-- 点击刷新页面 -->
<script type="text/javascript">
	function refreshImage(obj) {
		obj.src = "image?randNum=" + Math.random();
	}
</script>
<%
	Cookie[] cks = request.getCookies();
	String username = "";
	String password = "";
	if (cks != null) {
		for (Cookie ck : cks) {
			if (ck.getName().equals("userName")) {
				username = ck.getValue().trim();
			} else if (ck.getName().equals("password")) {
				password = ck.getValue().trim();
			}
		}
	} else {
		if ("${username}" != null) {
			username = "${username}";
		}
		if ("${password}" != null) {
			password = "${password}";
		}
	}
%>
</head>
<body>
	<!-- 打开页面访问数据库获取通知 -->

	<!-- 头部 -->
	<div id="head" class="indexhead">
		<div class="translation" align="right">
			<a href="index.jsp" title="chinese">中文 &nbsp |</a> &nbsp&nbsp <a
				href="index.jsp" title="none">ENGLISH &nbsp |&nbsp&nbsp&nbsp</a>
		</div>
		<img alt="武科大图标" src="image/indexhead.png"> <img alt="系统名"
			src="image/indexheadname.png" align="right">
	</div>
	<!-- 内容 -->
	<div class="indexcontext">
		<!-- 内容左侧 通知 -->

		<!-- <div class="notices"> -->
		<div class="col-md-8">
			<div
				style="width:100%;padding-left:50px;padding-top:30px;padding-right:30px">
				<s:action name="IndexInit" executeResult="true">
				</s:action>
			</div>
		</div>

		<!-- 内容右侧  -->

		<!-- <div class="login_print_search"> -->
		<div class="col-md-4">
			<div class="print_search"
				style="wieth:100%;padding-left:10px;padding-top:20px;padding-right:100px">
				<ul class="print"
					style="margin:0;padding:0;text-align:center;font-size:16px;list-style-type:none;">
					<li style="padding:15px;color: #999;"><a href="common/id.jsp">准考证打印</a>
					</li>
					<li style="padding:15px;color: #999;"><a
						href="common/search.jsp" style="letter-spacing: 5px;">成绩查询</a></li>
				</ul>

				<div>
					<div class="col-md-12">
						<div class="form-bg">
							<form action="user_login" class="form-horizontal" method="post">
								<!-- <span class="heading">用户登录</span> -->
								<div class="form-group">
									<input type="text" class="form-control" name="username"
										value="<%=username%>" id="userName" placeholder="用户名">
								</div>
								<div class="form-group help">
									<input type="password" class="form-control" name="password"
										value="<%=password%>" id="password" placeholder="密　码">
								</div>
								<div class="form-group">
									<!-- 验证码验证 -->
									<span> <input type="text" class="userCode"
										value="${userCode}" name="userCode" id="userCode"
										placeholder="验证码 " style="float:left;width:90px"> <img
										alt="看不清,点击更换" src="image" onclick="refreshImage(this)"
										style="cursor:hand;background-color:#999;right;margin-right:10px;width:80px;height:30px">
									</span>

								</div>

								<div class="form-group">
									<div class="main-checkbox">
										<input type="checkbox" id="checkbox1" name="checkbox1" checked="checked"/> <label
											for="checkbox1"></label>
									</div>
									<span class="text">记住密码</span>
									<button type="submit" class="btn btn-default">登录</button>

								</div>
								<span><i calss="error"
									style="font-size:10px;margin-left:10px;color:#f00">${error}</i></span>
								<div style="font-size: 12">
									<a href="common/forgetPassword.jsp">忘记密码?</a> <a href="common/register.jsp"
										calss="btn btn-secondary btn-embossed enable-btn">&nbsp注册</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 底部 -->
		<jsp:include page="common/footer.jsp"></jsp:include>
	</div>
</body>
</html>
