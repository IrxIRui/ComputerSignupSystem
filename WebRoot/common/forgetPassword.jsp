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
<meta name="description" content="武汉科技大学  - 计算机等级考试 报名系统">
<!--为了让 Bootstrap 开发的网站对移动设备友好，确保适当的绘制和触屏缩放，需要在网页的 head 之中添加 viewport meta 标签-->
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<title>武汉科技大学计算机等级考试 报名系统</title>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!-- 最
新的 B
ootstrap 核心 JavaScript 文件 -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min
.js"></script>
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
	function resertPw() {
		document.getElementById("method").value = "submit";
	}
	function sendSMSCode() {
		document.getElementById("method").value = "sendSMSCode";
	}
</script>
</head>

<body>
	<div id="register" class="f" data-backdrop="static" role="dialog"
		tabindex="-1" id="register" aria-hidden="false">
		<div class="modal-dialog ">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">找回密码</h4>
				</div>
				<div class="modal-body"
					style="overflow-x: hidden; overflow-y: auto; max-height:482px;">


					<form id="forgetPassword" class="form-horizontal" role="form"
						method="post" action="forgetPassword">
						<input type="hidden" id="method" name="method" value="">
						<div class="form-group">
							<div class="col-md-8 col-md-offset-4">
								<img alt="" src="image/wustImage.png" wedth="150" height="150">
							</div>
						</div>

						<div class="form-group">

							<div class="col-md-8 col-md-offset-2">
								<input type="text" class="form-control" name="username"
									value="${phone}" id="userName" placeholder="注册手机号">
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-8 col-md-offset-2">
								<p style="position: relative;">
									<input type="text" id="userCode" class="form-control"
										name="userCode" placeholder="输入验证码">
								</p>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-8 col-md-offset-3">
								<button class="btn btn-primary btn-lg" onclick="resertPw()">确定</button>
								<button class="btn btn-primary btn-lg" onclick="sendSMSCode()">获取短信验证码</button>

								<i calss="error" style="margin-left:10px;color:#f00">${error}</i>
							</div>
						</div>
						<div style="font-size:12;float:right">
							<a href="">使用绑定邮箱找回</a> <a href="index.jsp">|返回首页</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<br>

</body>
</html>
