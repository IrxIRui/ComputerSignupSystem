package com.liang.action;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.Request;

import com.liang.dao.UserDao;
import com.liang.entity.UserInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Upload extends ActionSupport {
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();

	private File uploadImage; // 得到上传的文件
	private String uploadImageContentType; // 得到文件的类型
	private String uploadImageFileName; // 得到文件的名称

	private String realName = null;
	private String IDNumber = null;
	private String sex = null;
	private String year = null;
	private String month = null;
	private String day = null;
	private String nation = null;
	private String degreeOfEducation = null;
	private String contactTelephone = null;
	private String contactAddress = null;
	private String pastCode = null;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getDegreeOfEducation() {
		return degreeOfEducation;
	}

	public void setDegreeOfEducation(String degreeOfEducation) {
		this.degreeOfEducation = degreeOfEducation;
	}

	public String getContactTelephone() {
		return contactTelephone;
	}

	public void setContactTelephone(String contactTelephone) {
		this.contactTelephone = contactTelephone;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getPastCode() {
		return pastCode;
	}

	public void setPastCode(String pastCode) {
		this.pastCode = pastCode;
	}

	public File getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getUploadImageContentType() {
		return uploadImageContentType;
	}

	public void setUploadImageContentType(String uploadImageContentType) {
		this.uploadImageContentType = uploadImageContentType;
	}

	public String getUploadImageFileName() {
		return uploadImageFileName;
	}

	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}

	public String UserSignInfo() {
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload/userImage");

		System.out.println("fileName:" + this.getUploadImageFileName());
		System.out.println("contentType:" + this.getUploadImageContentType());
		System.out.println("File:" + this.getUploadImage());
		System.out.println("realpath: " + realpath);
		System.out.println("realName: " + realName);
		System.out.println("IDNumber: " + IDNumber);
		System.out.println("sex: " + sex);
		System.out.println("year: " + year);
		System.out.println("month: " + month);
		System.out.println("day: " + day);
		System.out.println("nation: " + nation);
		System.out.println("degreeOfEducation: " + degreeOfEducation);
		System.out.println("contactTelephone: " + contactTelephone);
		System.out.println("contactAddress: " + contactAddress);
		System.out.println("pastCode: " + pastCode);

		// 判断输入格式是否有错
		String tem = judgeID(IDNumber);
		if (tem.equals("IDNumberError")) {//身份证号判断
			request.setAttribute("IDNumberError", "身份证号格式错误");
			System.out.println("IDNumberError");
			return "UserSignInfoError";
		}
		if (isMobileNO(contactTelephone) == false) {
			request.setAttribute("contactTelephoneError", "联系方式格式错误");
			System.out.println("contactTelephoneError");
			return "UserSignInfoError";
		}

		// 格式没错，存入数据库，修改照片名字
		Date date = newDate(year, month, day);
		System.out.println("date:" + date);

		degreeOfEducation = getDegreeOfEducation(degreeOfEducation);

		UserDao userDao = new UserDao();
		UserInfo loginUser = (UserInfo) session.getAttribute("loginUser");
		UserInfo userinfo = new UserInfo(loginUser.getId(), realName, IDNumber, Integer.parseInt(sex), date, nation,
				degreeOfEducation, contactTelephone, contactAddress, Integer.parseInt(pastCode), uploadImageFileName);
		userDao.uploadSignInfo(userinfo);

		if (uploadImage != null) {
			File savefile = new File(new File(realpath), uploadImageFileName);
			System.out.println(savefile);

			if (savefile.getParentFile().exists()) {
				try {
					savefile.getParentFile().mkdirs();
					FileUtils.copyFile(uploadImage, savefile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				ActionContext.getContext().put("message", "信息上传成功");
			}
		}
		return "UserSignInfoSuccess";
	}

	private String getDegreeOfEducation(String degreeOfEducation2) {
		if (degreeOfEducation2 == null) {
			return degreeOfEducation2;
		}
		int degreeOfEducation = Integer.parseInt(degreeOfEducation2);
		switch (degreeOfEducation) {
		case 1:
			degreeOfEducation2 = "初中以下";
			break;
		case 2:
			degreeOfEducation2 = "初中";
			break;
		case 3:
			degreeOfEducation2 = "专科";
			break;
		case 4:
			degreeOfEducation2 = "中专(中技)";
			break;
		case 5:
			degreeOfEducation2 = "高中(职高)";
			break;
		case 6:
			degreeOfEducation2 = "大专(专科)";
			break;
		case 7:
			degreeOfEducation2 = "本科";
			break;
		case 8:
			degreeOfEducation2 = "硕士";
			break;
		case 9:
			degreeOfEducation2 = "博士";
			break;
		}
		return degreeOfEducation2;
	}

	private Date newDate(String year, String month, String day) {
		if (year == null || month == null || day == null) {
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String monthString = null;
		String dayString = null;
		if (month.length() < 2) {
			monthString = "0" + month;
		} else {
			monthString = month;
		}
		if (day.length() < 2) {
			dayString = "0" + day;
		} else {
			dayString = day;
		}
		String dateString = year + "-" + monthString + "-" + dayString+" 00:01:00";
		Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return date;
	}

	// 判断是否是手机号
	public boolean isMobileNO(String mobiles) {
		if (mobiles == null) {
			return false;
		}
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	// 判断是否是身份证号
	public String judgeID(String IDNumber) {
		String wrongMsg = "IDNumberError";
		String correctMsg = "IDNumberRight";
		if (IDNumber == null) {
			return wrongMsg;
		}
		int len = IDNumber.length();

		char lastChar = IDNumber.charAt(len - 1);

		if (len != 18) {
			return wrongMsg;
		}
		for (int i = 0; i < len - 1; i++) {
			if (IDNumber.charAt(i) < '0' || IDNumber.charAt(i) > '9') {
				return wrongMsg;
			}
		}
		if (lastChar != 'X' && (lastChar < '0' || lastChar > '9')) {
			return wrongMsg;
		}

		/*
		 * year = IDNumber.substring(6,10); month = IDNumber.substring(10,12);
		 * day = IDNumber.substring(12,14);
		 */
		return correctMsg;
	}
}