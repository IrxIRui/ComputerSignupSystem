package com.liang.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.liang.entity.UserInfo;

public class UserFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("loginUser");
		if (user == null) {
			request.setAttribute("error", "非法操作，请先登录");
			request.getRequestDispatcher("index.jsp").forward(req, resp);
		} else if (user.getIsAdmin() != 0) {
			request.setAttribute("error", "非用户权限操作失败");
			request.getRequestDispatcher("index.jsp").forward(req, resp);
		} else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
