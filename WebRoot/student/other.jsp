<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.liang.entity.Download"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%
	String pageKind = (String) request.getParameter("page");

	//获取下载列表
	ActionContext actionContext = ActionContext.getContext();
	Map<String, Object> se = actionContext.getSession();
	List<Download> downloadsList = null;
	if (se != null) {
		downloadsList = (List<Download>) se.get("downloadsList");
	}
%>
<!-- <script type="text/javascript">
	function openModel() {
		$('#myModal').modal(options)
	}
</script> -->
</head>

<body>
<jsp:include page="navigationBar.jsp"></jsp:include>
	<%
		if ("1".equals(pageKind)) {
	%>
	<div class="col-md-8 col-md-offset-2">
		<pre>
一、 项目介绍： 全国计算机等级考试（National Computer RankExamination，简称NCRE），是经原国家教育委员会（现教育部）批准,
由教育部考试中心主办，面向社会，用于考查应试人员计算机应用知识与技能的全国性计算机水平考试体系。
二、 举办NCRE的目的 计算机技术的应用在我国各个领域发展迅速，为了适应知识经济和信息社会发展的需要，操作和应用计算机已成为 人
们必须掌握的一种基本技能。许多单位、部门已把掌握一定的计算机知识和应用技能作为人员聘用、职务晋升、职称评定、上岗资格的重要
依据之一。鉴于社会的客观需求，经原国家教委批准，原国家教委考试中心于1994年面向社会推出了NCRE，其目的在于以考促学，向社会推
广和普及计算机知识，也为用人部门录用和考核工作人员时提供一个统一、客观、公正的标准。
三、 NCRE由什么机构组织实施教育部考试中心负责实施考试，制定有关规章制度，编写考试大纲及相应的辅导材料，命制试卷、答案及评分
参考，进行成绩认定，颁发合格证书，研制考试必须的计算机软件，开展考试研究和宣传、评价等。教育部考试中心在各省（自治区、直辖
市）设立省级承办机构，由省级承办机构负责本省（自治区、直辖市）考试的宣传、推广和实施等。省级承办机构根据教育部考试中心有关
规定在所属地区符合条件的单位设立考点，由考点负责考生的考试、转发合格证书等工作。教育部考试中心聘请全国著名计算机专家组成“全
国计算机等级考试委员会”，负责设计考试方案、审定考试大纲、制定命题原则、指导和监督考试的实施。
四、 NCRE等级和科目如何构成 NCRE目前共设置了四个级别，证书体系详见“下载->表1-NCRE等级和科目详情”。 
五、NCRE有没有统一的考试大纲和辅导教材教育部考试中心对所有开考科目制订了统一的考试大纲，具体规定了各等级的考试要求和内容范
围，是命题的依据。同时，为了帮助考生学习和应考，教育部考试中心还组织专家编写了全国计算机等级考试系列教程（含考试大纲），由
高等教育出版社独家出版。全国计算机等级考试课程代码对应大纲与教材名称详见“下载->表2-NCRE考试大纲和辅导教材”。
六、 NCRE采取的考试形式 NCRE考试采用全国统一命题，统一考试时间的形式，所有级别/科目全部实行上机考试。考试时间：一级、四级为
90分钟；二级、三级为120分钟。
七、 NCRE报名和考试时间NCRE所有科目每年开考两次。一般为每年3月倒数第二个周六和9月倒数第二个周六，考试持续1至3天。考生具体考
试日期时间和考场地点，由考务系统编排考场时随机确定。报名时间一般在每年的6月和12月，具体报名时间可登录“北京市全国计算机等级
考试网上报名网站”（网址：http://ncre.bjeea.cn）查询；也可登陆“北京教育考试院网站”（网址：http://www.bjeea.cn）[非学历考试]
栏目，点击[NCRE报考]进入报名网站后查询。报名采取网上报名，考生须登录“北京市全国计算机等级考试网上报名网站”自行报名缴费、打
印准考证。
 八、 什么人可以报名参加考试考生不受年龄、职业、学历等背景的限制，任何人均可根据自己学习情况和实际能力选考相应的级别和科目，
 但不得在不同省（直辖市、自治区）重复报考。
九、 考生一次可以报考几个科目2018版考试体系下，考生可以根据自己的实际情况选择一个考点，报名参加该考点组织实施的一个或多个级
别/科目的考试，但不能重复报考某个科目。各考点根据设备情况等设定本考点的考试科目及限报科次（即在本考点每位考生最多可以报考的
科目数），考生在报名前需要查询考点相关信息，根据本人情况选择考点报考。
十、如何缴纳报名考务费根据教育部考试中心及北京市教育委员会、北京市物价局、北京市财政局有关文件规定，考生在报名时应缴纳报名考
务费。考生须通过网上支付缴纳报名考务费。
十一、 NCRE各级别/科目证书获证的条件是什么 一级、二级、三级科目获证条件为该科目成绩合格，即可获得相应科目证书。四级科目获证条
件为四级科目成绩合格，并已经（或同时）获得相应三级科目证书。三级软件测试技术(代码37)证书，以及考生2013年3月及以前获得的三级
各科目证书，可以作为四级任一科目的获证条件。四级考试成绩，自考试结束之日起可保留半年（按月计算）。如：考生同时报考了三级网络
技术、四级网络工程师两个科目，结果通过了四级网络工程师考试，但没有通过三级网络技术考试，将不颁发任何证书，四级网络工程师成绩
保留半年。半年内参加考试，考生报考三级网络技术并通过，将一次获得三级网络技术、四级网络工程师两个科目的证书；若没有通过三级网
络技术，将不能获得任何证书。超过时限，四级网络工程师成绩自动失效。
十二、 如何计算成绩
NCRE考试实行百分制计分，以等第成绩通知考生。等第成绩分为不及格、及格、良好、优秀四等。0-59分为不及格，60-79分为及格，80-89分
为良好，90-100分为优秀。考试成绩在及格以上者，由教育部考试中心印发合格证书。考试成绩为及格的，合格证书上标注“合格”字样；考试
成绩为良好的，合格证书上标注“良好”字样；考试成绩为优秀的，合格证书上标注“优秀”字样。NCRE所有级别证书均无时效限制。成绩在“及格
”以上者，由教育部考试中心颁发合格证书。成绩“优秀”的，合格证书上会注明“优秀”字样。 • 十三、 成绩与证书如何下发考生可在北京市全
国计算机等级考试网上报名网站（http://ncre.bjeea.cn）进行成绩预查询，最终成绩以教育部考试中心网站――中国教育考试网
（http://www.neea.edu.cn）公布的成绩为准。合格证书将由考生参加考试的考点转发给考生。 • 十四、 证书丢失了怎么办 考生证书丢失，
可登录教育部考试中心网站补办合格证明书。
十五、 证书获得者具备什么样的能力，可以胜任什么工作NCRE合格证书式样按照国际通行证书样式设计，用中、英两种文字书写，证书编号全
国统一，证书上印有持有人身份证号码。该证书全国通用，是持有人计算机应用的证明，也可供用人部门录用和考核工作人员时参考。一级为
操作技能级。一级证书表明持有人具有计算机的基础知识和初步应用能力，掌握Office办公自动化软件的使用及因特网（Internet）应用，或
掌握基本图形图像工具软件（Photoshop）的基本技能，可以从事机关、企事业单位文秘和办公信息计算机化工作。二级为程序设计/办公软件
高级应用级。二级证书表明持有人具有计算机基础知识和基本应用能力，能够使用计算机高级语言编写程序，可以从事计算机程序的编制工作、
初级计算机教学培训工作以及企业与信息化有关的业务和营销服务工作。三级为工程师预备级。三级证书表明持有人初步掌握与信息技术有关岗
位的基本技能，能够参与软硬件系统的开发、运维、管理和服务工作。四级为工程师级。证书表明持有人掌握从事信息技术工作的专业技能，并
有系统的计算机理论知识和综合应用。</pre>
	</div>
	<div class="col-md-2">&nbsp</div>
	<%
		} else if ("2".equals(pageKind)) {
	%>
	<div class="col-md-8 col-md-offset-2">
		<pre>
1.考生按照省级承办机构公布的报名流程到考点现场报名或网上报名。
（1）考生凭有效身份证件进行报名。有效身份证件指居民身份证（含临时身份证）、军人证件（军官证、警官证、士兵证）、港澳居民来往内
地通行证、台湾居民往来大陆通行证和护照。
（2）报名时，考生应提供准确的出生日期（8位字符型），否则将导致成绩合格的考生无法进行证书编号和打印证书。
（3）现场报名的考生应在一式两联的《考生报名登记表》上（含照片）确认信息，对于错误的信息应当场提出，考点更改后再次确认，无误后
方可签字；网上报名的考生，考生自己对填报信息负责。
（4）现场报名的考生领取准考证时，应携带考生报名登记表（考生留存）和有效身份证件方能领取，并自行查看考场分布、时间；网上报名的
考生，按省级承办机构要求完成相应的工作。
2.考生应携带本人准考证和有效身份证件参加考试。
3.考生应在考前15分钟到达考场，交验准考证和有效身份证件，同时抽签决定考试的考试机号。
4.考生提前5分钟在考试系统中输入自己的准考证号，并核对屏幕显示的姓名、有效身份证件号，如不符合，由监考人员帮其查找原因。考生信
息以报名库和考生签字的《考生报名登记表》信息为准，不得更改报名信息和登录信息。
5.考试开始后，迟到考生禁止入场。
6.在系统故障、死机、死循环、供电故障等特殊情况时，考生举手由监考人员判断原因。如属于考生误操作造成，后果由考生自负，给考点造成
经济损失的，由考生个人负担。
7.对于违规考生，由教育部考试中心根据违规记录进行处理。
8.考生成绩等第分为优秀、及格、不及格三等。90－100分为优秀、60－89分为及格、0－59分为不及格。
9.证书的“成绩”项处，成绩“及格”，证书上只打印“合格”字样；成绩“优秀”的，证书上打印“优秀”字样。
10.考生领取全国计算机等级考试合格证书时，应本人持有效身份证件来领取，并填写领取登记清单。
11.考生对分数的任何疑问，应在省级承办机构下发成绩后5个工作日内，向其报名的考点提出书面申请。
12.由于个人原因将合格证书遗失、损坏等情况的，可以申请补办合格证明书的，由考生个人在育部考试中心的综合查询网（chaxun.neea.edu.cn）
申请办理。</pre>
	</div>
	<div class="col-md-2">&nbsp</div>
	<%
		} else if ("3".equals(pageKind)) {
	%>
	<div class="col-md-8 col-md-offset-2">
		<pre>
报名流程：
		1填写个人资料
		2上传电子照片
		3选择报名科目
		4核对报名信息
		5在线交费
		6提交打印登记表
		7现场确认。
	</pre>
	</div>
	<div class="col-md-2">&nbsp</div>
	<%
		} else {
	%>
	<div class="col-md-8 col-md-offset-2">
		<div>
			<%
				if (downloadsList != null && downloadsList.size() > 0) {
			%>
			<ul class="list-group">
				<%
					//查询记录条数大于pageSize的时候count=pageSize;否则count=记录数
							for (int i = 0; i < downloadsList.size(); i++) {
								String NoticeContext = downloadsList.get(i).getDownloadsPath();
								NoticeContext = NoticeContext.trim();
				%>
				<li class="list-group-item"><a
					href="http://localhost:80/upload/download/<%=NoticeContext%>"><%=downloadsList.get(i).getDownloadsPath()%></a>
				</li>
				<%
					}
				%>
			</ul>
			<%
				} else {
			%>
			<p>无下载数据</p>
			<%
				}
			%>
		</div>
	</div>
	<div class="col-md-2">&nbsp</div>
	<%
		}
	%><!-- 
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">报名流程</h4>
				</div>
				<div class="modal-body">
					<pre>报名流程：
	1 选择省份 
	2 阅读报名介绍 
	3 选择考点及考试时间
	4 登录并阅读报名须知 
	5 填写报名信息并确认 
	6 在线缴费/现场确认缴费
	</pre>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
			/.modal-content
		</div>
		/.modal
	</div> -->
</body>
</html>
