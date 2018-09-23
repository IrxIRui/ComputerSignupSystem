package com.liang.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.liang.dao.ExamInfoDao;
import com.liang.dao.SignDao;
import com.liang.dao.UserDao;
import com.liang.entity.ExamInfo;
import com.liang.entity.ShowSignInfo;
import com.liang.entity.Sign;
import com.liang.entity.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class SelectSubjectAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String first = null;
	private String second = null;
	private String third = null;
	private Sign sign = null;
	private UserInfo user = null;
	private int exams[] = new int[3];
	private ExamInfoDao examInfoDao = new ExamInfoDao();
	private SignDao signDao = new SignDao();
	private List<ShowSignInfo> showSignInfos = null;
	private UserDao userDao = null;
	private ShowSignInfo showSignInfo = null;

	private List<ShowSignInfo> signList = new ArrayList<ShowSignInfo>();

	public List<ShowSignInfo> getSignList() {
		return signList;
	}

	public void setSignList(List<ShowSignInfo> signList) {
		this.signList = signList;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public String getThird() {
		return third;
	}

	public void setThird(String third) {
		this.third = third;
	}

	public int getExamId(String names[]) {
		int count = 0;
		for (int i = 0; i < 3; i++) {
			if (names[i].length() > 0) {
				// 根据name查出id
				exams[count] = examInfoDao.getExmaIdByExmaName(names[i]);
				count++;
			}
		}
		return count;
	}

	public Timestamp getExamDate(int id) {
		ExamInfo examInfo = examInfoDao.getExmaInfoByExmaId(id);
		return examInfo.getStartTime();
	}

	public String getExamRegistrationNumbers(String phoneNumber, int examId) {
		Date date = new Date();
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDay();
		String partPhone = phoneNumber.substring(8);
		return "" + year + month + day + partPhone + examId;
	}

	public String select() {
		// 输出测试
		System.out.println("选择考试科目：" + "1:" + first + "2:" + second + "3:" + third);
		String selcets[] = { first, second, third };
		int count = getExamId(selcets);

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession se = request.getSession();
		user = (UserInfo) se.getAttribute("loginUser");
		// 如果总的报名数超过三则不添加
		List<Sign> signs = signDao.getSignByUserId(user.getId());
		boolean notfull = notFull(user.getId(), count);
		if (notfull == false) {// 超过三个不记录
			request.setAttribute("error", "总报名数目超过三个，选报失败");
			return "selecterror";
		}
		sign = new Sign();
		int userID = user.getId();
		// 添加报考科目
		for (int i = 0; i < count; i++) {
			String examRegistrationNumbers = getExamRegistrationNumbers(user.getUserName(), exams[i]);
			sign.setExamRegistrationNumbers(examRegistrationNumbers);
			sign.setUser_id(userID);
			sign.setExam_id(exams[i]);
			sign.setSign_date(new Timestamp(System.currentTimeMillis()));
			sign.setDate(getExamDate(exams[i]));
			sign.setExam_address("武汉科技大学");
			sign.setPay(0);// 没支付
			sign.setState(0);// 没异常
			sign.setScore(-3);// -3没有成绩，-2弃考，-1作弊。
			signDao.addSign(sign);
		}
		request.setAttribute("error", "报名成功");
		System.out.println("报名成功----");
		return "selectsuccess";
	}

	private boolean notFull(int userId, int selected) {
		signDao = new SignDao();
		List<Sign> signs = signDao.getSignByUserId(userId);
		if ((signs.size() + selected) <= 3) {
			return true;
		}
		return false;
	}

	public String show() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession se = request.getSession();

		user = (UserInfo) se.getAttribute("loginUser");
		List<Sign> signs = signDao.getSignByUserId(user.getId());
		System.out.println("----个人报名记录数：----" + signs.size());
		Sign temp = null;

		if (signs != null) {
			showSignInfos = new ArrayList<ShowSignInfo>();
			for (int i = 0; i < signs.size(); i++) {
				temp = signs.get(i);
				showSignInfo = new ShowSignInfo();
				showSignInfo.setExamRegistrationNumbers(temp.getExamRegistrationNumbers());
				System.out.println("----准考证号:----" + showSignInfo.getExamRegistrationNumbers());
				showSignInfo.setUserName(getUserRealName(temp.getUser_id()));
				System.out.println(showSignInfo.getUserName());
				showSignInfo.setExamName(getExamName(temp.getExam_id()));
				System.out.println(showSignInfo.getExamName());
				showSignInfo.setSignDate(temp.getSign_date());
				System.out.println(showSignInfo.getSignDate());
				showSignInfo.setDate(temp.getDate());
				System.out.println(showSignInfo.getDate());
				showSignInfo.setExmaAddress(temp.getExam_address());
				showSignInfo.setPay(payInfo(temp.getPay()));
				showSignInfo.setState(String.valueOf(temp.getState()));
				showSignInfos.add(showSignInfo);
			}
		}
		se.setAttribute("showSignInfos", showSignInfos);
		//request.setAttribute("showSignInfos", showSignInfos);
		System.out.println("showSignInfos:" + showSignInfos.size());
		return "showsuccess";
	}

	public String show2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession se = request.getSession();
		user = (UserInfo) se.getAttribute("loginUser");
		List<Sign> signs = signDao.getSignByUserId(user.getId());
		System.out.println("----个人报名记录数：----" + signs.size());
		Sign temp = null;
		if (signs != null) {
			signList = new ArrayList<ShowSignInfo>();
			for (int i = 0; i < signs.size(); i++) {
				temp = signs.get(i);
				showSignInfo = new ShowSignInfo();
				showSignInfo.setExamRegistrationNumbers(temp.getExamRegistrationNumbers());
				System.out.println("----准考证号:----" + showSignInfo.getExamRegistrationNumbers());
				showSignInfo.setUserName(getUserRealName(temp.getUser_id()));
				System.out.println(showSignInfo.getUserName());

				showSignInfo.setExamName(getExamName(temp.getExam_id()));
				System.out.println(showSignInfo.getExamName());

				showSignInfo.setSignDate(temp.getSign_date());
				System.out.println(showSignInfo.getSignDate());
				showSignInfo.setDate(temp.getDate());
				System.out.println(showSignInfo.getDate());
				showSignInfo.setExmaAddress(temp.getExam_address());
				showSignInfo.setPay(payInfo(temp.getPay()));
				showSignInfo.setState(String.valueOf(temp.getState()));
				signList.add(showSignInfo);
			}
		}
		request.setAttribute("showSignInfos", signList);
		System.out.println("showSignInfos:" + signList.size());
		System.out.println("----获取json成功");
		return "show2success";
	}

	public String show3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession se = request.getSession();
		
		// 第一次刷新 showSignInfos==null，若不等，侧说明已有数据，不再自动获取
		if (se.getAttribute("showSignInfos") != null) {
			return "success";
		}

		user = (UserInfo) se.getAttribute("loginUser");
		List<Sign> signs = signDao.getSignByUserId(user.getId());
		System.out.println("----个人报名记录数：----" + signs.size());
		Sign temp = null;

		if (signs != null) {
			showSignInfos = new ArrayList<ShowSignInfo>();
			for (int i = 0; i < signs.size(); i++) {
				temp = signs.get(i);
				showSignInfo = new ShowSignInfo();
				showSignInfo.setExamRegistrationNumbers(temp.getExamRegistrationNumbers());
				System.out.println("----准考证号:----" + showSignInfo.getExamRegistrationNumbers());
				showSignInfo.setUserName(getUserRealName(temp.getUser_id()));
				System.out.println(showSignInfo.getUserName());
				showSignInfo.setExamName(getExamName(temp.getExam_id()));
				System.out.println(showSignInfo.getExamName());
				showSignInfo.setSignDate(temp.getSign_date());
				System.out.println(showSignInfo.getSignDate());
				showSignInfo.setDate(temp.getDate());
				System.out.println(showSignInfo.getDate());
				showSignInfo.setExmaAddress(temp.getExam_address());
				showSignInfo.setPay(payInfo(temp.getPay()));
				showSignInfo.setState(String.valueOf(temp.getState()));
				showSignInfos.add(showSignInfo);
			}
		}
		se.setAttribute("showSignInfos", showSignInfos);
		//request.setAttribute("showSignInfos", showSignInfos);
		System.out.println("showSignInfos:" + showSignInfos.size());
		return "showsuccess";
	}

	private String getUserRealName(int id) {
		userDao = new UserDao();
		return userDao.getUserRealNameById(id);
	}

	private String getExamName(int id) {
		return examInfoDao.getExamNameById(id);
	}

	private String payInfo(double pay) {
		if (pay == 0) {
			return "未支付";
		} else {
			return "支付成功";
		}
	}

	public String delete() {
		System.out.println("删除选择的科目---");

		HttpServletRequest request = ServletActionContext.getRequest();
		String examRegistrationNumbers = request.getParameter("examRegistrationNumbers").trim();
		signDao = new SignDao();
		boolean flag = signDao.deleteSign(examRegistrationNumbers);
		if (flag == true) {
			show();
			// 删除成功
			return "deletesuccess";
		}
		// 删除失败
		return "deleteerror";
	}
}
