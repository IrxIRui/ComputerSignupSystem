package com.liang.entity;

import java.sql.Timestamp;

public class ExamInfo {
	
	private int exam_id;
	private String exam_name;
	private String exmam_Grade;
	private int accountKey;
	private int lengthOfExam;
	private Timestamp startTime;
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}
	public String getExam_name() {
		return exam_name;
	}
	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}
	public String getExmam_Grade() {
		return exmam_Grade;
	}
	public void setExmam_Grade(String exmam_Grade) {
		this.exmam_Grade = exmam_Grade;
	}
	public int getAccountKey() {
		return accountKey;
	}
	public void setAccountKey(int accountKey) {
		this.accountKey = accountKey;
	}
	public int getLengthOfExam() {
		return lengthOfExam;
	}
	public void setLengthOfExam(int lengthOfExam) {
		this.lengthOfExam = lengthOfExam;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	

}
