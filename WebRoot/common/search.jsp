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
<script type="text/javascript">
	function refreshCode(obj){
		obj.src="image?r="+Math.random();
	}
</script>

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
	</nav>
	
	<div style="margin-left:30px">
		<ul class="nav nav-tabs">
			<li class="active"><a href="student/search.jsp">成绩查询</a></li>
		</ul>
	</div>
	<!-- 内容显示 -->
	<div class="tab-content" style="margin-left:30px;margin-top:20px">
		<div id="student_score" class="tab-pane fade active in">
			<form class="form-inline" role="form"
				data-form-action="student.score">
				<div class="form-group">
					<label>考试时间：</label> <select class="form-control" name="time">
						<option value="201703">201703</option>
						<option value="201609">201609</option>
						<option value="201603">201603</option>
						<option value="201509">201509</option>
						<option value="201503">201503</option>
						<option value="201409">201409</option>
						<option value="201403">201403</option>
						<option value="201309">201309</option>
						<option value="201303">201303</option>
						<option value="201209">201209</option>
						<option value="201203">201203</option>
						<option value="201109">201109</option>
						<option value="201103">201103</option>
						<option value="201009">201009</option>
						<option value="201003">201003</option>
					</select>
				</div>
				<div class="form-group">
					<label>查询方式：</label> <select class="form-control" name="type">
						<option value="0">准考证</option>
						<option value="1">身份证</option>
						<option value="2">报名号</option>
					</select> <input type="text" class="form-control" name="value"
						placeholder="输入对应的号码">
				</div>
				<div class="form-group has-feedback">
					<label>验证码：</label>
							<input type="text" name="code" class="form-control" placeholder="验证码" size="4">
							<img src="image" class="login_code" onclick="refreshCode(this)">
				</div>
				<button type="submit" class="btn btn-default">查 询</button>
			</form>
			<br>
			<div class="content"></div>
		</div>
	</div>
</body>
</html>
