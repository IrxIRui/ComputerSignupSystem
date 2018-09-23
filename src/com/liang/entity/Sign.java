package com.liang.entity;

import java.sql.Timestamp;

public class Sign {
	private int sign_id;
	private String examRegistrationNumbers=null;
	private int user_id;
	private int exam_id;
	private Timestamp sign_date;
	private Timestamp date;
	private String exam_address;
	private double pay;
	private int state;
	private double score;
	public int getSign_id() {
		return sign_id;
	}
	public void setSign_id(int sign_id) {
		this.sign_id = sign_id;
	}
	public String getExamRegistrationNumbers() {
		return examRegistrationNumbers;
	}
	public void setExamRegistrationNumbers(String examRegistrationNumbers) {
		this.examRegistrationNumbers = examRegistrationNumbers;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}
	public Timestamp getSign_date() {
		return sign_date;
	}
	public void setSign_date(Timestamp sign_date) {
		this.sign_date = sign_date;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getExam_address() {
		return exam_address;
	}
	public void setExam_address(String exam_address) {
		this.exam_address = exam_address;
	}
	public double getPay() {
		return pay;
	}
	public void setPay(double pay) {
		this.pay = pay;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
}
