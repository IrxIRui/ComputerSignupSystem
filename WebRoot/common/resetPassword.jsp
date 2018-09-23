<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.liang.entity.UserInfo"%>
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
<%
	//验证是否是从忘记密码页面跳转过来的
	UserInfo user = (UserInfo) session.getAttribute("user");
	if (user == null) {
		response.sendRedirect("forgetPassword.jsp");
	}
%>
<script type="text/javascript">
	function resetPw() {
		document.getElementById("method").value = "resetPw";
	}
	function returnIndex() {
		window.location.href="index.jsp";
	}
</script>
</head>

<body>
	<div id="register" class="f" data-backdrop="static" role="dialog"
		tabindex="-1" id="register" aria-hidden="false">
		<div class="modal-dialog ">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">重置密码</h4>
				</div>
				<div class="modal-body"
					style="overflow-x: hidden; overflow-y: auto; max-height:482px;">
					<form class="form-horizontal" role="form" method="post"
						action="forgetPassword">
						<input type="hidden" id="method" name="method" value="">
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
							<div class="col-md-8 col-md-offset-3">
								<button class="btn btn-primary btn-lg" onclick="resetPw()">确
									定</button>
								<i calss="error" style="margin-left:10px;color:#f00">${error}</i>
							</div>
						</div>
					</form>
					<div style="float:right">
						<button class="btn btn-default btn-lg" onclick="returnIndex()">返回首页登陆</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
</body>
</html>
