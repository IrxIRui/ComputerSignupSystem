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
	<jsp:include page="navigationBar.jsp"></jsp:include>
	<jsp:include page="leftNavigation.jsp"></jsp:include>
	<div class="col-md-9">
		<div id="register" data-backdrop="static" tabindex="-1" id="register"
			aria-hidden="false">

			<div>
				<div class="modal-header" align="center">
					<h4 class="modal-title">计算机等级考试报名信息填写</h4>
				</div>
				<div style="text-align: left">
					<h5>&nbsp&nbsp&nbsp基本信息</h5>
					<i calss="error" style="margin-left:10px;color:#f00">${message}</i>
				</div>

				<div class="modal-body"
					style="overflow-x: hidden; overflow-y: auto; max-height:482px;">

					<form class="form-horizontal" role="form" method="post"
						enctype="multipart/form-data" action="upload_UserSignInfo">

						<div class="form-group">
							<label class="col-md-3 control-label">姓名</label>
							<div class="col-md-6">
								<input type="text" class="form-control" id="realName"
									name="realName" value="" placeholder="姓名" required="required">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">身份证号</label>
							<div class="col-md-6">
								<input type="hidden" name="id" value=""> <input
									type="text" class="form-control" id="IDNumber" name="IDNumber"
									value="" placeholder="身份证号" required="required">
							</div>
							<i calss="error" style="margin-left:10px;color:#f00">${IDNumberError}</i>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">性别</label>
							<div class="col-md-6">
								<select class="form-control" name="sex">
									<option value="1">男</option>
									<option value="0">女</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">* 出身日期</label>
							<div class="col-md-2">
								<select class="form-control" name="year">
									<%
										int i = currentYear - 9;
										while (i > (currentYear - 50)) {
											i--;
									%>
									<option value="<%=i%>"><%=i%></option>
									<%
										}
									%>
								</select>
							</div>
							<div class="col-md-2">
								<select class="form-control" name="month">
									<%
										for (i = 1; i <= 12; i++) {
									%>
									<option value="<%=i%>"><%=i%></option>
									<%
										}
									%>
								</select>
							</div>
							<div class="col-md-2">
								<select class="form-control" name="day">
									<%
										for (i = 1; i <= 31; i++) {
									%>
									<option value="<%=i%>"><%=i%></option>
									<%
										}
									%>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">名族</label>
							<div class="col-md-6">
								<input type="text" class="form-control" name="nation" value=""
									placeholder="名族" required="required">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">学历</label>
							<div class="col-md-6">
								<select class="form-control" name="degreeOfEducation">
									<option value="1">初中以下</option>
									<option value="2">初中</option>
									<option value="3">专科</option>
									<option value="4">中专(中技)</option>
									<option value="5">高中(职高)</option>
									<option value="6">大专(专科)</option>
									<option value="7">本科</option>
									<option value="8">硕士</option>
									<option value="9">博士</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">选择证件照图片</label>
							<div class="col-md-6">
								<input type="file" class="form-control" name="uploadImage">
							</div>
						</div>

						<div class="modal-header"></div>
						<div style="text-align: left;">
							<h5>联系信息</h5>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">联系电话</label>
							<div class="col-md-6">
								<input type="text" class="form-control" id="contactTelephone"
									name="contactTelephone" value="" placeholder="联系电话"
									required="required">
							</div>
							<i calss="error" style="margin-left:10px;color:#f00">${contactTelephoneError}</i>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">联系地址</label>
							<div class="col-md-6">
								<input type="text" class="form-control" id="contactAddress"
									name="contactAddress" value="" placeholder="联系地址"
									required="required">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">邮编</label>
							<div class="col-md-6">
								<input type="text" class="form-control" id="pastCode"
									name="pastCode" value="" placeholder="邮编" required="required">
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-8 col-md-offset-3">
								<button class="btn btn-primary btn-lg">
									<i class="glyphicon glyphicon-ok"></i> 确 定
								</button>

							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
