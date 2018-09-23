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
	// ���û�������֤����
	private String msg = "6666";// ���͵���֤��
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();

	UserInfo user = null;
	private String userSMSCode = null;
	// ��������
	private String userName = null;
	private String error = "error";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() {
		// ��ȡ�ͻ������������
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

	// �ж��Ƿ����ֻ���
	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public String sendSMSCode() {
		UserDao userDao = new UserDao();
		// ��ȡ�ͻ������������
		userName = request.getParameter("username");
		if (userName == null || !isMobileNO(userName) || userDao.getUserByName(userName) == null) {
			request.setAttribute("error", "����ע���ֻ���");
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
			 msg=messageSend.getObj();//��ȡ�ӿ����ɵ���֤�룬����session
			 session.setAttribute("SMSCode", msg);
			 request.setAttribute("error","�ѷ����������ֻ�");
			 request.setAttribute("phone", phone);
			 return  "sendSMSCodeSuccess";
		}
		 
		
		request.setAttribute("error", "��֤���ȡʧ��"); return "sendSMSCodeError";
		 
		
		 /*		
		session.setAttribute("SMSCode", "6666");// �����õ�
		request.setAttribute("phone", phone);
		request.setAttribute("error", "�����������ֻ�");
		System.out.println("----------------SMSCode:6666");		
		return "sendSMSCodeSuccess";*/
	}

	public String submit() {
		System.out.println("-----------------------submit");
		UserDao userDao = new UserDao();
		// ��ȡ�ͻ������������
		userName = request.getParameter("username");
		userSMSCode = request.getParameter("userCode");
		String SMSCode = (String) session.getAttribute("SMSCode");

		if (userName == null || !isMobileNO(userName) || userDao.getUserByName(userName) == null) {
			request.setAttribute("error", "����ע���ֻ���");
			return "sendSMSCodeError";
		}
		if (userSMSCode == null) {
			request.setAttribute("error", "��֤�����");
			return "submitError";
		}
		if (userSMSCode.equals(SMSCode)) {
			// ��֤�ɹ���ת��������
			user=userDao.getUserByName(userName);
			session.setAttribute("user", user);
			return "submitSuccess";
		}
		request.setAttribute("error", "��֤��ʧ��");
		return "submitError";
	}

	private String resetPw() {
		UserDao userDao = new UserDao();
		String password1 = request.getParameter("password");
		String password2 = request.getParameter("password2");
		if(password1==null||password2==null){
			request.setAttribute("error", "���벻��Ϊ��");
			return "resetPwError";
		}
		if(password1.equals(password2)){
			//��������
			UserInfo user=(UserInfo)session.getAttribute("user");
			boolean flag=userDao.updatePassword(user, password1);
			if (flag != false) {
				request.setAttribute("error", "���³ɹ� ");
				return "resetPwSuccess";
			} else {
				request.setAttribute("error", "����ʧ�� ");
				return "resetPwError";
			}
			
		}
		request.setAttribute("error", "�����������벻ͬ");
		return "resetPwError"; 
	}

}
