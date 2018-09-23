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
				// ����name���id
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
		// �������
		System.out.println("ѡ���Կ�Ŀ��" + "1:" + first + "2:" + second + "3:" + third);
		String selcets[] = { first, second, third };
		int count = getExamId(selcets);

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession se = request.getSession();
		user = (UserInfo) se.getAttribute("loginUser");
		// ����ܵı����������������
		List<Sign> signs = signDao.getSignByUserId(user.getId());
		boolean notfull = notFull(user.getId(), count);
		if (notfull == false) {// ������������¼
			request.setAttribute("error", "�ܱ�����Ŀ����������ѡ��ʧ��");
			return "selecterror";
		}
		sign = new Sign();
		int userID = user.getId();
		// ��ӱ�����Ŀ
		for (int i = 0; i < count; i++) {
			String examRegistrationNumbers = getExamRegistrationNumbers(user.getUserName(), exams[i]);
			sign.setExamRegistrationNumbers(examRegistrationNumbers);
			sign.setUser_id(userID);
			sign.setExam_id(exams[i]);
			sign.setSign_date(new Timestamp(System.currentTimeMillis()));
			sign.setDate(getExamDate(exams[i]));
			sign.setExam_address("�人�Ƽ���ѧ");
			sign.setPay(0);// û֧��
			sign.setState(0);// û�쳣
			sign.setScore(-3);// -3û�гɼ���-2������-1���ס�
			signDao.addSign(sign);
		}
		request.setAttribute("error", "�����ɹ�");
		System.out.println("�����ɹ�----");
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
		System.out.println("----���˱�����¼����----" + signs.size());
		Sign temp = null;

		if (signs != null) {
			showSignInfos = new ArrayList<ShowSignInfo>();
			for (int i = 0; i < signs.size(); i++) {
				temp = signs.get(i);
				showSignInfo = new ShowSignInfo();
				showSignInfo.setExamRegistrationNumbers(temp.getExamRegistrationNumbers());
				System.out.println("----׼��֤��:----" + showSignInfo.getExamRegistrationNumbers());
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
		System.out.println("----���˱�����¼����----" + signs.size());
		Sign temp = null;
		if (signs != null) {
			signList = new ArrayList<ShowSignInfo>();
			for (int i = 0; i < signs.size(); i++) {
				temp = signs.get(i);
				showSignInfo = new ShowSignInfo();
				showSignInfo.setExamRegistrationNumbers(temp.getExamRegistrationNumbers());
				System.out.println("----׼��֤��:----" + showSignInfo.getExamRegistrationNumbers());
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
		System.out.println("----��ȡjson�ɹ�");
		return "show2success";
	}

	public String show3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession se = request.getSession();
		
		// ��һ��ˢ�� showSignInfos==null�������ȣ���˵���������ݣ������Զ���ȡ
		if (se.getAttribute("showSignInfos") != null) {
			return "success";
		}

		user = (UserInfo) se.getAttribute("loginUser");
		List<Sign> signs = signDao.getSignByUserId(user.getId());
		System.out.println("----���˱�����¼����----" + signs.size());
		Sign temp = null;

		if (signs != null) {
			showSignInfos = new ArrayList<ShowSignInfo>();
			for (int i = 0; i < signs.size(); i++) {
				temp = signs.get(i);
				showSignInfo = new ShowSignInfo();
				showSignInfo.setExamRegistrationNumbers(temp.getExamRegistrationNumbers());
				System.out.println("----׼��֤��:----" + showSignInfo.getExamRegistrationNumbers());
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
			return "δ֧��";
		} else {
			return "֧���ɹ�";
		}
	}

	public String delete() {
		System.out.println("ɾ��ѡ��Ŀ�Ŀ---");

		HttpServletRequest request = ServletActionContext.getRequest();
		String examRegistrationNumbers = request.getParameter("examRegistrationNumbers").trim();
		signDao = new SignDao();
		boolean flag = signDao.deleteSign(examRegistrationNumbers);
		if (flag == true) {
			show();
			// ɾ���ɹ�
			return "deletesuccess";
		}
		// ɾ��ʧ��
		return "deleteerror";
	}
}
