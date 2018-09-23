package com.liang.action;

import com.liang.dao.VerificationCodeDao;
import com.opensymphony.xwork2.ActionSupport;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;

public class ImageAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//生成一个验证码类，包括图片以及文本
		VerificationCodeDao vc = new VerificationCodeDao(); 
		BufferedImage image = vc.getImage(); // 获取验证码图片	
		String text=vc.getText();
		request.getSession().setAttribute("verificationCode", text); // 将验证码的文本存在session
		try {
			VerificationCodeDao.output(image, response.getOutputStream());// 将验证码图片响应给客户端
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*自动返回到原来的页面，必须设成null返回，不能使用常规的String类型，否则虽然能正常输出显示但是后台报错会*/
		return null;
	}

}