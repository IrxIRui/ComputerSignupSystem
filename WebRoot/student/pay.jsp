<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="com.liang.entity.ShowSignInfo"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
	//可以不用js，在jsp页面中直接使用EL语句获取值,例如<p>:${sessionScope.pageData.getPageSize()}</p>
	//以后的开发都这么做
	List<ShowSignInfo> showSignInfos = (List<ShowSignInfo>) session.getAttribute("showSignInfos");
%>
<script type="text/javascript">
	function load() {
		$.ajax({
			type : "POST",
			url : 'Subject_show'
		});
	}
</script>
</head>

<body onload="load">
	<jsp:include page="navigationBar.jsp"></jsp:include>
	<jsp:include page="leftNavigation.jsp"></jsp:include>

	<div class="col-md-9">
		<div class="modal-header" align="center">
			<h4 class="modal-title">当前已选择的考试科目</h4>
		</div>

		<div class="modal-body"
			style="overflow-x: hidden; overflow-y: auto; max-height:482px;">
			<div>
				<ul class="nav nav-tabs">
					<li class="active"><a href="Subject_show">刷新</a></li>
				</ul>
			</div>

			<div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>准考证号</th>
							<th>姓名</th>
							<th>选择科目</th>
							<th>报名时间</th>
							<th>考试地点</th>
							<th>是否支付</th>
							<th>考试时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<%
							if (showSignInfos != null && showSignInfos.size() > 0) {
								for (int i = 0; i < showSignInfos.size(); i++) {
						%>
						<tr>
							<td><%=showSignInfos.get(i).getExamRegistrationNumbers()%></td>
							<td><%=showSignInfos.get(i).getUserName()%></td>
							<td><%=showSignInfos.get(i).getExamName()%></td>
							<td><%=showSignInfos.get(i).getSignDate()%></td>
							<td><%=showSignInfos.get(i).getExmaAddress()%></td>
							<td><%=showSignInfos.get(i).getPay()%></td>
							<td><%=showSignInfos.get(i).getDate()%></td>
							<%
								if ("未支付".equals(showSignInfos.get(i).getPay())) {
							%>
							<td><a
								href="Subject_delete?examRegistrationNumbers=<%=showSignInfos.get(i).getExamRegistrationNumbers()%>">删除选择</a></td>
							<%
								}
							%>

						</tr>
						<%
							}
							}
						%>
					</tbody>
				</table>
			</div>
			<div style="text-align: center">
				<button class="btn btn-primary btn-lg" data-toggle="modal"
					data-target="#payCode">
					<i class="glyphicon glyphicon-ok"></i>确认并缴费
				</button>
			</div>
		</div>
	</div>

	<div class="modal fade" id="payCode" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">二维码支付</h4>
				</div>
				<div class="modal-body" style="text-align: center">
					<img alt="" src="image/paycode.jpg" wedth="150" height="150">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="window.location.href='pay_success'">支付成功</button>
					<!--  -->
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>


</body>
</html>
