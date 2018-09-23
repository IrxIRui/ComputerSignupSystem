package com.liang.entity;

import java.sql.Timestamp;

import org.apache.struts2.json.annotations.JSON;

public class ShowSignInfo {
	private String examRegistrationNumbers=null;
	private String userName=null;
	private String examName=null;
	private Timestamp signDate=null;
	private Timestamp date=null;
	private String exmaAddress=null;
	private String pay=null;
	private String state=null;
	public String getExamRegistrationNumbers() {
		return examRegistrationNumbers;
	}
	public void setExamRegistrationNumbers(String examRegistrationNumbers) {
		this.examRegistrationNumbers = examRegistrationNumbers;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getSignDate() {
		return signDate;
	}
	public void setSignDate(Timestamp signDate) {
		this.signDate = signDate;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getExmaAddress() {
		return exmaAddress;
	}
	public void setExmaAddress(String exmaAddress) {
		this.exmaAddress = exmaAddress;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
