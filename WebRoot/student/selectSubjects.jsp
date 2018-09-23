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
<title>武汉科技大学计算机等级考试 报名系统</title>
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link
	href="/try/bootstrap/twitter-bootstrap-v2/docs/assets/css/bootstrap.css"
	rel="stylesheet">
<link
	href="https://cdn.bootcss.com/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.js"></script>
</head>
<script type="text/javascript">
	var signupSubjectCount = 3;
	function selectSubject(obj) {
		if (obj.checked == true) {
			if (signupSubjectCount > 0) {
				signupSubjectCount -= 1;
			} else {
				alert("本次报名数达到上限");
				obj.checked = false;
			}
		} else {
			signupSubjectCount++;
		}
	}
</script>

<script type="text/javascript">
	function submitSubuject() {
		var checkBox = document.getElementsByName("checkBox1");
		var firstSelect = "";
		var secondSelect = "";
		var thirdSelect = "";
		var count = 1;
		for (i = 0; i < checkBox.length; i++) {
			if (checkBox[i].checked) {
				if (count <= 1) {
					firstSelect = checkBox[i].value;
					count++;
				} else if (count <= 2) {
					secondSelect = checkBox[i].value;
					count++;
				} else if (count <= 3) {
					thirdSelect = checkBox[i].value;
					count++;
				}
			}
		}
		var select = { //设置数据源
			first : firstSelect,
			second : secondSelect,
			third : thirdSelect
		}
		$.ajax({
			type : "POST",
			url : 'Subject_select',
			data : select,
			dataType : 'json',
			success : function(data) {
				window.location.href="http://localhost/ComputerSignupSystem/student/pay.jsp";
			}
		});
	}
</script>

<body>
	<jsp:include page="navigationBar.jsp"></jsp:include>
	<jsp:include page="leftNavigation.jsp"></jsp:include>

	<div class="col-md-9">
		<div class="alert alert-warning">
			<a href="#" class="close" data-dismiss="alert"> &times; </a>
			计算机等级考试报考规定: 一级和二级可以直接报名，没有特定要求。
			但不能直接报考三级。三级数据库技术证书要求已经（或同时）获得二级数据库程序设计类证书；
			三级网络技术、软件测试技术、信息安全技术、嵌入式系统开发技术等四个证书要求已经（或同时）获得二级语言程序设计类证书。
			只有通过二级可以报考三级，三级 通过报考四级，或者二、三和四可以同时报，但二级必须考过才能拿到三、四级证书，
			NCRE所有级别证书均无时效限制，三、四两个级别的成绩可保留一次。考生一次考试可以同时报考多个科目。
		</div>
		<div id="register" data-backdrop="static" tabindex="-1" id="register"
			aria-hidden="false">

			<div class="modal-header" align="center">
				<h4 class="modal-title">计算机等级考试报名</h4>
			</div>

			<div class="modal-body"
				style="overflow-x: hidden; overflow-y: auto; max-height:482px;text-align: left;">
				<span><i calss="error"
					style="font-size:10px;margin-left:10px;color:#f00">${error}</i></span>
				<form class="form-horizontal" role="form" action="">
					<div style="text-align: left;">
						<h5>一级</h5>
					</div>
					<div class="form-group">
						<div>
							<div class="col-md-4">
								<label><input type="checkbox" value="计算机基础及WPS Office应用"
									name="checkBox1" onclick="selectSubject(this)">计算机基础及WPS
									Office应用</label>
							</div>
							<div class="col-md-4">
								<label><input type="checkbox" value="计算机基础及MS Office应用"
									name="checkBox1" onclick="selectSubject(this)">计算机基础及MS
									Office应用</label>
							</div>
							<div class="col-md-4">
								<label><input type="checkbox" value="计算机基础及Photoshop应用"
									name="checkBox1" onclick="selectSubject(this)">计算机基础及Photoshop应用</label>
							</div>
						</div>
						<div class="col-md-12">
							<label><input type="checkbox" value="网络安全素质教育"
								name="checkBox1" onclick="selectSubject(this)">网络安全素质教育</label>
						</div>
					</div>

					<div class="modal-header"></div>
					<div style="text-align: left;">
						<h5>二级</h5>
					</div>
					<div class="form-group">
						<div>
							<div class="col-md-4">
								<label><input type="checkbox" value="C语言程序设计"
									name="checkBox1" onclick="selectSubject(this)">C语言程序设计</label>
							</div>
							<div class="col-md-4">
								<label><input type="checkbox" value="VB语言程序设计"
									name="checkBox1" onclick="selectSubject(this)">VB语言程序设计</label>
							</div>
							<div class="col-md-4">
								<label><input type="checkbox" value="Java语言程序设计"
									name="checkBox1" onclick="selectSubject(this)">Java语言程序设计</label>
							</div>
						</div>
						<div>
							<div class="col-md-4">
								<label><input type="checkbox" value="Access数据库程序设计"
									name="checkBox1" onclick="selectSubject(this)">Access数据库程序设计</label>
							</div>
							<div class="col-md-4">
								<label><input type="checkbox" value="C++语言程序设计"
									name="checkBox1" onclick="selectSubject(this)">C++语言程序设计</label>
							</div>
							<div class="col-md-4">
								<label><input type="checkbox" value="MySQL数据库程序设计"
									name="checkBox1" onclick="selectSubject(this)">MySQL数据库程序设计</label>
							</div>
						</div>
						<div>
							<div class="col-md-4">
								<label><input type="checkbox" value="Web程序设计"
									name="checkBox1" onclick="selectSubject(this)">Web程序设计</label>
							</div>
							<div class="col-md-4">
								<label><input type="checkbox" value="MS Office高级应用"
									name="checkBox1" onclick="selectSubject(this)">MS
									Office高级应用</label>
							</div>
							<div class="col-md-4">
								<label><input type="checkbox" value="Python语言程序设计"
									name="checkBox1" onclick="selectSubject(this)">Python语言程序设计</label>
							</div>
						</div>
					</div>


					<div class="modal-header"></div>
					<div style="text-align: left;">
						<h5>三级</h5>
					</div>
					<div class="form-group">
						<div>
							<div class="col-md-4">
								<label><input type="checkbox" value="网络技术"
									name="checkBox1" onclick="selectSubject(this)">网络技术</label>
							</div>
							<div class="col-md-4">
								<label><input type="checkbox" value="数据库技术"
									name="checkBox1" onclick="selectSubject(this)"> 数据库技术</label>
							</div>
							<div class="col-md-4">
								<label><input type="checkbox" value="信息安全技术"
									name="checkBox1" onclick="selectSubject(this)">信息安全技术</label>
							</div>
						</div>
						<div class="col-md-12">
							<label><input type="checkbox" value="嵌入式系统开发技术"
								name="checkBox1" onclick="selectSubject(this)">嵌入式系统开发技术</label>
						</div>
					</div>
					<div class="modal-header"></div>
					<div style="text-align: left;">
						<h5>四级</h5>
					</div>
					<div class="form-group">
						<div>
							<div class="_18">
								<label><input type="checkbox" value="网络工程师"
									name="checkBox1" onclick="selectSubject(this)">网络工程师</label>
							</div>
							<div class="col-md-4">
								<label><input type="checkbox" value="数据库工程师"
									name="checkBox1" onclick="selectSubject(this)">数据库工程师</label>
							</div>
							<div class="col-md-4">
								<label><input type="checkbox" value="信息安全工程师"
									name="checkBox1" onclick="selectSubject(this)">信息安全工程师</label>
							</div>
						</div>
						<div class="col-md-12">
							<label><input type="checkbox" value="嵌入式系统开发工程师"
								name="checkBox1" onclick="selectSubject(this)">嵌入式系统开发工程师</label>
						</div>
					</div>
					<div class="form-group" style="text-align: center">
						<div class="col-md-12">
							<button class="btn btn-primary btn-lg" onclick="submitSubuject()">
								<i class="glyphicon glyphicon-ok"></i> 确 定 提 交
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
