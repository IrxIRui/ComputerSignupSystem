package com.liang.dao;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.liang.util.CheckSumBuilder;

public class MobileMessageSend {
	private static final String SERVER_URL = "https://api.netease.im/sms/sendcode.action";// �����URL
	private static final String APP_KEY = "cda4dbef60a178e7c3fb4cbc6331fbb3";// �˺�
	private static final String APP_SECRET = "d7d44de433ca";// ����
	private static final String MOULD_ID = "3892812";// ģ��ID
	private static final String NONCE = "123456";
	
	private String obj=null;//���ɵ���֤��
	

	public String getObj() {
		return obj;
	}

	public String sendSMS(String phone) throws IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(SERVER_URL);
		String curTime = String.valueOf((new Date().getTime() / 1000L));
		String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);

		// ���������header
		post.addHeader("AppKey", APP_KEY);
		post.addHeader("Nonce", NONCE);
		post.addHeader("CurTime", curTime);
		post.addHeader("CheckSum", checkSum);		
		post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		//�����������
        List<NameValuePair> nameValuePairs =new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("mobile",phone));
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));

		// ִ������
		HttpResponse response = httpclient.execute(post);
		String responseEntity = EntityUtils.toString(response.getEntity(), "utf-8");

		// �ж��Ƿ��ͳɹ������ͳɹ�����true
		String code = JSON.parseObject(responseEntity).getString("code");
		obj=JSON.parseObject(responseEntity).getString("obj");
		if (code.equals("200")) {
			System.out.println("-------------��֤�룺"+obj);
			return "success";
		}
		return "error";
	}

	/**���Գɹ�
	 * @param args
	 */
	public static void main(String[] args) {
		String mobileNumber = "18827669410";// ������֤����ֻ�����
		try {
			MobileMessageSend test = new MobileMessageSend();
			String str = test.sendSMS(mobileNumber);
			if ("success".equals(str)) {
				System.out.println("���ͳɹ���");
			} else {
				System.out.println("����ʧ�ܣ�");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
