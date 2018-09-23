package com.liang.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.liang.dao.UserDao;
import com.liang.entity.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class PersonInfo extends ActionSupport {

	private UserDao userDao = null;
	private UserInfo userInfo = null;

	public String show() {
		System.out.println("显示个人信息---");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		userDao = new UserDao();
		userInfo = (UserInfo) session.getAttribute("loginUser");
		userInfo = userDao.getUserById(userInfo.getId());
		if (userInfo != null) {
			session.setAttribute("loginUser", userInfo);
		}
		return "success";
	}
}
