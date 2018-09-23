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
	UserInfo userInfo = (UserInfo) session.getAttribute("loginUser");
	int sexIndex = userInfo.getSex();
	System.out.println(sexIndex);

	int year = userInfo.getYear();
	int month = userInfo.getMonth();
	int day = userInfo.getDay();
%>

</head>

<body>
	<jsp:include page="navigationBar.jsp"></jsp:include>
	<div class="col-md-12">
		<div>
			<div align="center" style="margin-top: 50px">
				<h4 class="modal-title">个人报名信息</h4>
			</div>
			<div style="text-align: left">
				<i calss="error" style="margin-left:10px;color:#f00">${message}</i>
			</div>
			<div>
				<form class="form-horizontal" role="form" method="post"
					enctype="multipart/form-data" action="upload_UserSignInfo">
					<div class="form-group">
						<div class="col-md-8 col-md-offset-3">
							<img alt="" src="upload/userImage/${sessionScope.loginUser.getMajorGrade() }" wedth="150" height="150">
						</div>
						<label class="col-md-3 control-label col-md-offset-1">登记照片</label>
						<!-- <div class="col-md-6">
							<input type="file" class="" name="uploadImage">
						</div> -->
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">姓名</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="realName"
								name="realName" value="${sessionScope.loginUser.getRealName() }"
								readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">身份证号</label>
						<div class="col-md-6">
							<input type="hidden" name="id" value=""> <input
								type="text" class="form-control" id="IDNumber" name="IDNumber"
								value="${sessionScope.loginUser.getINNumber()}"
								readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">性别</label>
						<div class="col-md-6">
							<select class="form-control" name="sex" id="sex">
								<option value="0"
									<%if (sexIndex == 0) {
				out.print("selected='selected'");
			}%>>女</option>
								<option value="1"
									<%if (sexIndex == 1) {
				out.print("selected='selected'");
			}%>>男</option>
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
								<option value="<%=i%>"
									<%if (year == i) {
					out.print("selected='selected'");
				}%>><%=i%></option>
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
								<option value="<%=i%>"
									<%if (month == i) {
					out.print("selected='selected'");
				}%>><%=i%></option>
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
								<option value="<%=i%>"
									<%if (day == i) {
					out.print("selected='selected'");
				}%>><%=i%></option>
								<%
									}
								%>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">名族</label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="nation"
								value="${sessionScope.loginUser.getNation() }">
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

					<div class="modal-header"></div>
					<div style="text-align: left;">
						<h5>联系信息</h5>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">联系电话</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="contactTelephone"
								name="contactTelephone"
								value="${sessionScope.loginUser.getContactTelephone() }">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">联系地址</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="contactAddress"
								name="contactAddress"
								value="${sessionScope.loginUser.getContactAddress() }">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">邮编</label>
						<div class="col-md-6">
							<input type="text" class="form-control" id="pastCode"
								name="pastCode" value="${sessionScope.loginUser.getPastCode() }">
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-8 col-md-offset-3">
							<!-- 保存功能暂不实现 -->
							<!-- <button class="btn btn-primary btn-lg">
								<i class="glyphicon glyphicon-ok"></i>保存
							</button> -->
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
