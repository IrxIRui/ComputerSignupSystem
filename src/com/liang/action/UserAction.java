package com.liang.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.liang.dao.UserDao;
import com.liang.entity.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UserInfo user = null;
	private String userCode = null;
	// ��������
	private String userName = null;
	private String password = null;
	private String password2 = null;
	private int isAdmin = 0;
	private String telephone = null;
	private String email = null;
	private String error = "error";

	// �ж��Ƿ����ֻ���
	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public String register() {
		UserDao userDao = new UserDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		// ��ȡsession�е����ݣ������ִ�Сд
		String verificationCode = ((String) request.getSession().getAttribute("verificationCode"));
		// ��ȡ�ͻ������������
		userName = request.getParameter("username");
		password = request.getParameter("password");
		password2 = request.getParameter("password2");
		isAdmin = 0;// ע���û�������ͨ�û�
		telephone = request.getParameter("username");// ���ֻ���ע��
		email = request.getParameter("email");
		userCode = request.getParameter("userCode");

		if (userCode != null && verificationCode != null) {
			userCode.toLowerCase();
			verificationCode = verificationCode.toLowerCase();
		} else {
			request.setAttribute("error", "��֤�����");
			return "registerError";
		}

		// ����ע��ҳ�棬�����û�����
		request.setAttribute("username", userName);
		request.setAttribute("password", password);
		request.setAttribute("isAdmin", isAdmin);
		request.setAttribute("telephone", telephone);
		request.setAttribute("email", email);
		request.setAttribute("userCode", userCode);

		// ��֤�����
		if (!verificationCode.equals(userCode)) {
			request.setAttribute("error", "��֤�����");
			return "registerError";
		}
		// ���ֻ���ע��
		if (!isMobileNO(userName)) {
			request.setAttribute("error", "������ȷ���ֻ���ע��");
			return "registerError";
		}
		// ���벻һ��
		if (!password.equals(password2)) {
			request.setAttribute("error", "���벻һ��");
			return "registerError";
		}
		if (userDao.getUserByName(userName) != null) {
			request.setAttribute("error", "���ֻ����Ѿ�ע��");
			return "registerError";
		}

		// ע��ɹ�
		UserInfo user = new UserInfo(userName, password, isAdmin, telephone, email);
		boolean flag = userDao.insert(user);

		if (flag == true) {
			System.out.println("ע��ɹ�...");
			session.setAttribute("userName", userName);
			request.setAttribute("error", "ע��ɹ����뷵�ص�¼...");
			return "user_registerSuccess";
		}
		request.setAttribute("error", "ϵͳԭ��ע��ʧ��");
		return "registerError";
	}

	public String login() {

		UserDao userDao = new UserDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		// ��ȡsession�е����ݣ������ִ�Сд
		String verificationCode = ((String) request.getSession().getAttribute("verificationCode"));
		// ��ȡ�ͻ������������
		userName = request.getParameter("username");
		password = request.getParameter("password");
		userCode = request.getParameter("userCode");

		String isSave = request.getParameter("checkbox1");

		// ����ע��ҳ�棬�����û�����
		request.setAttribute("username", userName);
		request.setAttribute("password", password);
		request.setAttribute("userCode", userCode);

		if (userCode != null && verificationCode != null) {
			userCode.toLowerCase();
			verificationCode = verificationCode.toLowerCase();
		} else {
			request.setAttribute("error", "��֤�����");
			return "loginError";
		}
		if (!verificationCode.equals(userCode)) {
			request.setAttribute("error", "��֤�����");
			return "loginError";
		}
		user = userDao.getUserByName(userName);
		if (user == null) {
			request.setAttribute("error", "�û�������");
			return "loginError";
		}
		if (!password.equals(user.getPassword())) {
			request.setAttribute("error", "�������");
			return "loginError";
		}

		session.setAttribute("loginUser", user);// ��½�ɹ�

		setCookie(response, isSave);

		if (user.getIsAdmin() == 1) {// ����Ա�û�
			return "admin_loginSuccess";
		} else {// ��ͨ�û�
			return "user_loginSuccess";
		}

	}

	private void setCookie(HttpServletResponse response, String isSave) {
		Cookie ck1 = new Cookie("userName", userName);
		Cookie ck2 = new Cookie("password", password);
		if (isSave == null) {// ����ס����
			ck1.setMaxAge(0);
			ck2.setMaxAge(0);
		} else {// ��ס����
			ck1.setMaxAge(60 * 60 * 24 * 30);// ��סһ����
			ck2.setMaxAge(60 * 60 * 24 * 7);// �����סһ������
		}

		response.addCookie(ck1);
		response.addCookie(ck2);
	}

	public String logout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		user=(UserInfo) session.getAttribute("loginUser");
		if(user!=null){
			session.removeAttribute("loginUser");
		}
		return "logoutSuccess";
	}
	
	public String selectSubject(){
		
		
		return null;
	}

	/*	*//**
			 * ������
			 * 
			 * @param args
			 *//*
			 * public static void main(String args[]){ System.out.println(
			 * "register-----------------------------testStart");
			 * System.out.println("register-----------------------------testEnd"
			 * );
			 * 
			 * System.out.println(
			 * "login--------------------------------testStart");
			 * System.out.println("login--------------------------------testEnd"
			 * ); }
			 */

}
