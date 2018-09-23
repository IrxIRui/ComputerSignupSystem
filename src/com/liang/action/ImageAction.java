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
		
		//����һ����֤���࣬����ͼƬ�Լ��ı�
		VerificationCodeDao vc = new VerificationCodeDao(); 
		BufferedImage image = vc.getImage(); // ��ȡ��֤��ͼƬ	
		String text=vc.getText();
		request.getSession().setAttribute("verificationCode", text); // ����֤����ı�����session
		try {
			VerificationCodeDao.output(image, response.getOutputStream());// ����֤��ͼƬ��Ӧ���ͻ���
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*�Զ����ص�ԭ����ҳ�棬�������null���أ�����ʹ�ó����String���ͣ�������Ȼ�����������ʾ���Ǻ�̨�����*/
		return null;
	}

}