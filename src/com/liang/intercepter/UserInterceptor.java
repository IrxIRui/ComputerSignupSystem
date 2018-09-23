package com.liang.intercepter;

import javax.servlet.http.HttpSession;

import com.liang.entity.UserInfo;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// ȡ��������ص�ActionContextʵ��  
        HttpSession session=(HttpSession) invocation.getInvocationContext().getSession();
        UserInfo user=(UserInfo) session.getAttribute("LoginUser");
        if(user==null){
			return "login";
		}else if(user.getIsAdmin()!=1){
			return "login";
		}
        return invocation.invoke();
	}

}
