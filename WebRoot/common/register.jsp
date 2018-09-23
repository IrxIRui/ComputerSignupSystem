<%@page import="org.apache.struts2.ServletActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="com.opensymphony.xwork2.ActionSupport"%>
<%@ page import="java.util.Map"%>
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
<style>
.code {
	position: absolute;
	top: 0px;
	right: 1px;
	line-height: 1.4;
	padding: 1px 0px;
	height: 34px;
	cursor: pointer;
	right: 1px;
	line-height: 1.4;
	padding: 1px 0px;
}
</style>
<script type="text/javascript">
	function refreshImage(obj) {
		obj.src = "image?randNum=" + Math.random();
	}
</script>
</head>

<body>
	<div id="register" class="f" data-backdrop="static" role="dialog"
		tabindex="-1" id="register" aria-hidden="false">
		<div class="modal-dialog ">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">
						<i class="glyphicon glyphicon-link"></i> 注册
					</h4>
				</div>
				<div class="modal-body"
					style="overflow-x: hidden; overflow-y: auto; max-height:482px;">
					<form class="form-horizontal" role="form" method="post"
						action="user_register">
						<div class="form-group">
							<label class="col-md-3 control-label">注册用户</label>
							<div class="col-md-8">
								<input type="hidden" name="id" value=""> <input
									type="text" class="form-control" id="username" name="username"
									value="${username}" placeholder="输入手机号注册" required="">
								<p class="help-block">请输入手机号注册</p>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">电子邮箱</label>
							<div class="col-md-8">
								<input type="email" class="form-control" name="email"
									placeholder="输入邮箱地址,用于找回密码" value="${email}" required="">
								<p class="help-block">输入正确的邮箱地址,用于找回密码</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">设置密码</label>
							<div class="col-md-8">
								<input type="password" class="form-control" id="password"
									value="${password}" name="password" placeholder="密码"
									required="required">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">确认密码</label>
							<div class="col-md-8">
								<input type="password" class="form-control" name="password2"
									value="${passord2}" placeholder="再次输入密码" required="required">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">验证码</label>
							<div class="col-md-8">
								<p style="position: relative;">
									<input type="text" id="userCode" class="form-control"
										name="userCode" placeholder="输入验证码" required=""> <img
										src="image" alt="看不清,点击更换" onclick="refreshImage(this)"
										class="code">
								</p>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-8 col-md-offset-3">
								<button class="btn btn-primary btn-lg">
									<i class="glyphicon glyphicon-ok"></i> 确 定
								</button>

								<i calss="error" style="margin-left:10px;color:#f00">${error}</i>
							</div>
						</div>
					</form>
					<div style="float:right">
						<!-- 						<button class="btn btn-default btn-lg"> -->
						<a href="index.jsp" style="text-decoration:none">返回登陆</a>
						<!-- 						</button> -->
					</div>

				</div>
			</div>
		</div>
	</div>
	<br>
</body>
</html>
