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
	// 变量声明
	private String userName = null;
	private String password = null;
	private String password2 = null;
	private int isAdmin = 0;
	private String telephone = null;
	private String email = null;
	private String error = "error";

	// 判断是否是手机号
	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public String register() {
		UserDao userDao = new UserDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		// 获取session中的数据，不区分大小写
		String verificationCode = ((String) request.getSession().getAttribute("verificationCode"));
		// 获取客户端请求的数据
		userName = request.getParameter("username");
		password = request.getParameter("password");
		password2 = request.getParameter("password2");
		isAdmin = 0;// 注册用户都是普通用户
		telephone = request.getParameter("username");// 用手机号注册
		email = request.getParameter("email");
		userCode = request.getParameter("userCode");

		if (userCode != null && verificationCode != null) {
			userCode.toLowerCase();
			verificationCode = verificationCode.toLowerCase();
		} else {
			request.setAttribute("error", "验证码错误");
			return "registerError";
		}

		// 返回注册页面，减少用户输入
		request.setAttribute("username", userName);
		request.setAttribute("password", password);
		request.setAttribute("isAdmin", isAdmin);
		request.setAttribute("telephone", telephone);
		request.setAttribute("email", email);
		request.setAttribute("userCode", userCode);

		// 验证码错误
		if (!verificationCode.equals(userCode)) {
			request.setAttribute("error", "验证码错误");
			return "registerError";
		}
		// 非手机号注册
		if (!isMobileNO(userName)) {
			request.setAttribute("error", "请用正确的手机号注册");
			return "registerError";
		}
		// 密码不一样
		if (!password.equals(password2)) {
			request.setAttribute("error", "密码不一致");
			return "registerError";
		}
		if (userDao.getUserByName(userName) != null) {
			request.setAttribute("error", "改手机号已经注册");
			return "registerError";
		}

		// 注册成功
		UserInfo user = new UserInfo(userName, password, isAdmin, telephone, email);
		boolean flag = userDao.insert(user);

		if (flag == true) {
			System.out.println("注册成功...");
			session.setAttribute("userName", userName);
			request.setAttribute("error", "注册成功，请返回登录...");
			return "user_registerSuccess";
		}
		request.setAttribute("error", "系统原因注册失败");
		return "registerError";
	}

	public String login() {

		UserDao userDao = new UserDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		// 获取session中的数据，不区分大小写
		String verificationCode = ((String) request.getSession().getAttribute("verificationCode"));
		// 获取客户端请求的数据
		userName = request.getParameter("username");
		password = request.getParameter("password");
		userCode = request.getParameter("userCode");

		String isSave = request.getParameter("checkbox1");

		// 返回注册页面，减少用户输入
		request.setAttribute("username", userName);
		request.setAttribute("password", password);
		request.setAttribute("userCode", userCode);

		if (userCode != null && verificationCode != null) {
			userCode.toLowerCase();
			verificationCode = verificationCode.toLowerCase();
		} else {
			request.setAttribute("error", "验证码错误");
			return "loginError";
		}
		if (!verificationCode.equals(userCode)) {
			request.setAttribute("error", "验证码错误");
			return "loginError";
		}
		user = userDao.getUserByName(userName);
		if (user == null) {
			request.setAttribute("error", "用户不存在");
			return "loginError";
		}
		if (!password.equals(user.getPassword())) {
			request.setAttribute("error", "密码错误");
			return "loginError";
		}

		session.setAttribute("loginUser", user);// 登陆成功

		setCookie(response, isSave);

		if (user.getIsAdmin() == 1) {// 管理员用户
			return "admin_loginSuccess";
		} else {// 普通用户
			return "user_loginSuccess";
		}

	}

	private void setCookie(HttpServletResponse response, String isSave) {
		Cookie ck1 = new Cookie("userName", userName);
		Cookie ck2 = new Cookie("password", password);
		if (isSave == null) {// 不记住密码
			ck1.setMaxAge(0);
			ck2.setMaxAge(0);
		} else {// 记住密码
			ck1.setMaxAge(60 * 60 * 24 * 30);// 记住一个月
			ck2.setMaxAge(60 * 60 * 24 * 7);// 密码记住一个星期
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
			 * 测试类
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
