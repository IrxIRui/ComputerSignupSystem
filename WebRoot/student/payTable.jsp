<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.liang.entity.ShowSignInfo"%>
<%
	//可以不用js，在jsp页面中直接使用EL语句获取值,例如<p>:${sessionScope.pageData.getPageSize()}</p>
	//以后的开发都这么做
	List<ShowSignInfo> showSignInfos = (List<ShowSignInfo>) session.getAttribute("showSignInfos");
%>
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
							<td><a href="Subject_delete?examRegistrationNumbers=<%=showSignInfos.get(i).getExamRegistrationNumbers()%>">删除选择</a></td>
						</tr>
						<%
							}
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
