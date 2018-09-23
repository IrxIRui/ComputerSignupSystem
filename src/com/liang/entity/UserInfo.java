package com.liang.entity;

import java.util.Calendar;
import java.util.Date;

public class UserInfo {
	// 注册用户基本信息
	private int id;
	private String userName = null;
	private String password = null;
	private int isAdmin = 0;// 默认不是管理员
	private String telephone = null;
	private String email = null;

	// 报名用户选填信息
	private String realName = null;
	private String IDNumber = null;
	private int sex = 0;// 默认男性
	private Date data = null;
	private String nation = null;
	private String degreeOfEdecation = null;
	private String contactTelephone = null;
	private String contactAddress = null;
	private String contactEmail = null;
	private int pastCode;// 四位数
	private String studentID = null;
	private String majorGrade = null;

	public UserInfo(int id, String userName, String password, int isAdmin, String telephone, String email,
			String realName, String iDNumber, int sex, Date data, String nation, String degreeOfEdecation,
			String contactTelephone, String contactAddress, String contactEmail, int pastCode, String studentID,
			String majorGrade) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.isAdmin = isAdmin;
		this.telephone = telephone;
		this.email = email;
		this.realName = realName;
		this.IDNumber = iDNumber;
		this.sex = sex;
		this.data = data;
		this.nation = nation;
		this.degreeOfEdecation = degreeOfEdecation;
		this.contactTelephone = contactTelephone;
		this.contactAddress = contactAddress;
		this.contactEmail = contactEmail;
		this.pastCode = pastCode;
		this.studentID = studentID;
		this.majorGrade = majorGrade;
	}

	public UserInfo(int id, String userName, String password, int isAdmin, String telephone, String email) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.isAdmin = isAdmin;
		this.telephone = telephone;
		this.email = email;
	}

	public UserInfo(String userName, String password, int isAdmin, String telephone, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.isAdmin = isAdmin;
		this.telephone = telephone;
		this.email = email;
	}

	public UserInfo(int id, String realName, String IDNumber, int sex, Date data, String nation,
			String degreeOfEdecation, String contactTelephone, String contactAddress,
			int pastCode,String fileName) {
		super();
		this.id = id;

		this.realName = realName;
		this.IDNumber = IDNumber;
		this.sex = sex;
		this.data = data;
		this.nation = nation;
		this.degreeOfEdecation = degreeOfEdecation;
		this.contactTelephone = contactTelephone;
		this.contactAddress = contactAddress;
		this.pastCode = pastCode;
		this.majorGrade=fileName;
	}

	public UserInfo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getINNumber() {
		return IDNumber;
	}

	public void setINNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getDegreeOfEdecation() {
		return degreeOfEdecation;
	}

	public void setDegreeOfEdecation(String degreeOfEdecation) {
		this.degreeOfEdecation = degreeOfEdecation;
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

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public int getPastCode() {
		return pastCode;
	}

	public void setPastCode(int pastCode) {
		this.pastCode = pastCode;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getMajorGrade() {
		return majorGrade;
	}

	public void setMajorGrade(String majorGrade) {
		this.majorGrade = majorGrade;
	}
	
	public int getYear(){
		Calendar c = Calendar.getInstance();
		c.setTime(getData());
		return c.get(Calendar.YEAR);
	}
	public int getMonth(){
		Calendar c = Calendar.getInstance();
		c.setTime(getData());
		return c.get(Calendar.MONTH);
	}
	public int getDay(){
		Calendar c = Calendar.getInstance();
		c.setTime(getData());
		return c.get(Calendar.DAY_OF_MONTH);
	}

}
