<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<link
	href="/try/bootstrap/twitter-bootstrap-v2/docs/assets/css/bootstrap.css"
	rel="stylesheet">
<link href="CSS/Bootstrap/bootstrap-table.css" rel="stylesheet">
<link href="CSS/Bootstrap/bootstrap-table.min.css" rel="stylesheet">

<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="Scripts/Bootstrap/local/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript"
	src="Scripts/Bootstrap/local/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript"
	src="Scripts/Bootstrap/local/bootstrap-table.js"></script>
<script type="text/javascript"
	src="Scripts/Bootstrap/local/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="Scripts/Bootstrap/local/bootstrap-table-export.min.js"></script>
<script type="text/javascript"
	src="Scripts/Bootstrap/local/bootstrap-table-export.js"></script>
<script type="text/javascript"
	src="Scripts/Bootstrap/local/bootstrap.js"></script>

<script type="text/javascript">
	$(function() {
		$('#tb_showSigns').bootstrapTable({
			url : 'showSigns', //请求后台的URL（*）
			dataType : "json", /*数据类型*/
			pagination : true, /*是否分页*/
			method : 'get',
			sidePagination: "server",
			columns : [ /*{
				field : 'examRegistrationNumbers', //Json数据对应的字段
				title : '准考证号',
				align : 'center'
			}, {
				field : 'userName',
				title : '姓名',
				align : 'center'
			} , {
				field : 'examName',
				title : '选择科目',
				align : 'center'
			}, {
				field : 'exmaAddress',
				title : '考试地点',
				align : 'center'
			}, {
				fiel : 'pay',
				title : '是否支付',
				align : 'center'
			}, */ {
				fiel : 'date',
				title : '考试时间',
				align : 'center'
			} /*, {
				fiel : 'signDate',
				title : '报名时间',
				align : 'center'
			}, {
				title : '操作',
				field : 'examRegistrationNumbers',
				align : 'center',
				formatter : function(value, row, index) {
					var e = '<a href="test.jsp?nid=\'' + row.nid + '\'">编辑</a> ';
					var d = '<a href="test.jsp?nid=\'' + row.nid + '\'">删除</a>';
					returnl + e + d;
				}
			} */ ],
			onLoadSuccess : function() {
				showTips("chengl");},
			onLoadError : function() {
				showTips("数据加载失败！");
			},
		});
	});
</script>
<%-- 
<script type="text/javascript">
	$(function() {
		$('#tb_showSigns').bootstrapTable({
			//url : "showSigns",
			dataType : "json", /*数据类型*/
			pagination : true, /*是否分页*/


			method : 'post', //请求方式（*）
			toolbar : '#toolbar', //工具按钮用哪个容器
			cache : false,
			clickToSelect : true,
			showRefresh : true, //是否显示刷新按钮
			showPaginationSwitch : true, //是否显示选择分页数按钮
			pageNumber : 1, //初始化加载第一页，默认第一页
			pageSize : 5, //每页的记录行数（*）
			search : true,
			responseHandler : function(signList) {
				return signList.DATA;
			},
			
			columns : [ {
				checkbox : true
			}, {
				field : 'examRegistrationNumbers', /*Json数据对应的字段*/
				title : '准考证号',
				align : 'center'
			}, {
				field : 'userName',
				title : '姓名',
				align : 'center'
			}, {
				field : 'examName',
				title : '选择科目',
				align : 'center'
			}, {
				field : 'exmaAddress',
				title : '考试地点',
				align : 'center'
			}, {
				fiel : 'pay',
				title : '是否支付',
				align : 'center'
			}, {
				fiel : 'date',
				title : '考试时间',
				align : 'center'
			}, {
				fiel : 'signDate',
				title : '报名时间',
				align : 'center'
			}, {
				title : '操作',
				field : 'examRegistrationNumbers',
				align : 'center',
				formatter : function(value, row, index) {
					var e = '<a href="test.jsp?nid=\'' + row.nid + '\'">编辑</a> ';
					var d = '<a href="test.jsp?nid=\'' + row.nid + '\'">删除</a>';
					returnl + e + d;
				}
			} ]
		});
	});
</script> --%>
</head>

<body>
	<div class="col-md-9">
		<div class="modal-header" align="center">
			<h4 class="modal-title">当前已选择的考试科目</h4>
		</div>

		<div class="modal-body"
			style="overflow-x: hidden; overflow-y: auto; max-height:482px;">
			<div>
				<ul class="nav nav-tabs">
					<li class="active"><a href="showSigns">刷新</a></li>
				</ul>
			</div>

			<div>
				<table id="tb_showSigns"></table>
			</div>
		</div>
	</div>
	<br>
</body>
</html>
