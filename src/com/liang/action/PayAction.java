package com.liang.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.liang.dao.SignDao;
import com.liang.entity.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class PayAction extends ActionSupport {
	SignDao signDao = null;

	public String pay() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("loginUser");
		System.out.println("¿ªÊ¼Ö§¸¶");
		signDao = new SignDao();
		return signDao.paySign(userInfo.getId());
	}
}
