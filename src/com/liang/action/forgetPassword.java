package com.liang.action;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.liang.dao.MobileMessageSend;
import com.liang.dao.UserDao;
import com.liang.entity.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class forgetPassword extends ActionSupport {
	// 向用户发送验证短信
	private String msg = "6666";// 发送的验证码
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();

	UserInfo user = null;
	private String userSMSCode = null;
	// 变量声明
	private String userName = null;
	private String error = "error";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() {
		// 获取客户端请求的数据
		String method = request.getParameter("method");
		System.out.println("---------------method:" + method);
		if ("sendSMSCode".equals(method)) {
			return sendSMSCode();
		}
		if ("submit".equals(method)) {
			return submit();
		}
		if ("resetPw".equals(method)) {
			return resetPw();
		}
		return "error";
	}

	// 判断是否是手机号
	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public String sendSMSCode() {
		UserDao userDao = new UserDao();
		// 获取客户端请求的数据
		userName = request.getParameter("username");
		if (userName == null || !isMobileNO(userName) || userDao.getUserByName(userName) == null) {
			request.setAttribute("error", "输入注册手机号");
			return "sendSMSCodeError";
		}
		return sendSMSCodeing(userName.trim());
	}

	public String sendSMSCodeing(String phone) {
		
		 MobileMessageSend messageSend = new MobileMessageSend(); 
		 String res="error"; 
		 try {
			 res = messageSend.sendSMS(phone);
		 }catch (IOException e) { // TODO Auto-generated catch block
			 e.printStackTrace();
		 } 
		 if ("success".equals(res)){
			 msg=messageSend.getObj();//获取接口生成的验证码，存入session
			 session.setAttribute("SMSCode", msg);
			 request.setAttribute("error","已发送至您的手机");
			 request.setAttribute("phone", phone);
			 return  "sendSMSCodeSuccess";
		}
		 
		
		request.setAttribute("error", "验证码获取失败"); return "sendSMSCodeError";
		 
		
		 /*		
		session.setAttribute("SMSCode", "6666");// 测试用的
		request.setAttribute("phone", phone);
		request.setAttribute("error", "已送至您的手机");
		System.out.println("----------------SMSCode:6666");		
		return "sendSMSCodeSuccess";*/
	}

	public String submit() {
		System.out.println("-----------------------submit");
		UserDao userDao = new UserDao();
		// 获取客户端请求的数据
		userName = request.getParameter("username");
		userSMSCode = request.getParameter("userCode");
		String SMSCode = (String) session.getAttribute("SMSCode");

		if (userName == null || !isMobileNO(userName) || userDao.getUserByName(userName) == null) {
			request.setAttribute("error", "输入注册手机号");
			return "sendSMSCodeError";
		}
		if (userSMSCode == null) {
			request.setAttribute("error", "验证码错误");
			return "submitError";
		}
		if (userSMSCode.equals(SMSCode)) {
			// 验证成功跳转重置密码
			user=userDao.getUserByName(userName);
			session.setAttribute("user", user);
			return "submitSuccess";
		}
		request.setAttribute("error", "验证码失败");
		return "submitError";
	}

	private String resetPw() {
		UserDao userDao = new UserDao();
		String password1 = request.getParameter("password");
		String password2 = request.getParameter("password2");
		if(password1==null||password2==null){
			request.setAttribute("error", "密码不能为空");
			return "resetPwError";
		}
		if(password1.equals(password2)){
			//更新密码
			UserInfo user=(UserInfo)session.getAttribute("user");
			boolean flag=userDao.updatePassword(user, password1);
			if (flag != false) {
				request.setAttribute("error", "更新成功 ");
				return "resetPwSuccess";
			} else {
				request.setAttribute("error", "更新失败 ");
				return "resetPwError";
			}
			
		}
		request.setAttribute("error", "两次输入密码不同");
		return "resetPwError"; 
	}

}
