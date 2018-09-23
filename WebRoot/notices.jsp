<%@page import="com.liang.entity.PageData"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.liang.entity.NoticesInfo"%>
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
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<%
	//可以不用js，在jsp页面中直接使用EL语句获取值,例如<p>:${sessionScope.pageData.getPageSize()}</p>
	//以后的开发都这么做
	ActionContext actionContext = ActionContext.getContext();
	Map<String, Object> se = actionContext.getSession();

	List<NoticesInfo> notices = null;
	PageData pageData = null;
	String partName = "";
	if (se != null) {
		notices = (List<NoticesInfo>) se.get("notices");
		pageData = (PageData) se.get("pageData");
		partName = (String) se.get("partName");
	}
%>

</head>
<body>
	<!-- 内容 -->
	<!-- 内容左侧 通知 -->
	<div>
		<form id="search" action="searchNoticesByPartName" method="post">
			<div class="input-group col-md-3" style="margin-top:0px;float:right">
				<!-- <input type="hidden" name="method value="searchByPartName" /> -->
				<input type="text" name="partName" class="form-control"
					value="<%=partName%>"> <span class="input-group-btn">
					<button class="btn btn-info btn-search">go</button>
				</span>
			</div>
		</form>

	</div>
	<div>
		<ul class="nav nav-tabs">
			<li class="active"><a href="">通知</a></li>
		</ul>
	</div>

	<div>
		<%
			if (notices != null && notices.size() > 0) {
		%>
		<ul class="list-group">
			<%
				//查询记录条数大于pageSize的时候count=pageSize;否则count=记录数
					int count = notices.size() > pageData.getPageSize() ? pageData.getPageSize() : notices.size();
					for (int i = 0; i < count; i++) {
					String NoticeContext=notices.get(i).getNoticeContext();
					NoticeContext=NoticeContext.trim();
			%>
			<li class="list-group-item"><span class="badge"><%=notices.get(i).getNoticeDate()%>
			</span> <a href="http://localhost:80/upload/notices/<%=NoticeContext%>"><%=notices.get(i).getNoticeName()%></a>
			</li>
			<%
				}
			%>
		</ul>
		<div style="text-align: center;">
			<ul class="pagination">
				<li><a href=" ">&laquo;</a></li>
				<%
					//设置现实的最大记录数
						int maxPage = pageData.getSumPage() > 10 ? 10 : pageData.getSumPage();
						for (int i = 1; i <= maxPage; i++) {
							if (pageData.getCurrentPage() == i) {
				%>
				<li class="active"><a href=" "><%=i%></a></li>
				<%
					} else {
				%>
				<li><a href="searchNoticesByPageAndPartName?page=<%=i%>"><%=i%></a></li>
				<%
					}
						}
				%>
				<li><a href=" ">&raquo;</a></li>
			</ul>
		</div>
		<%
			} else {
		%>
		<p>无数据</p>
		<%
			}
		%>
	</div>
</body>
</html>