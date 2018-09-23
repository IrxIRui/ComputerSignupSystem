package com.liang.entity;

import java.sql.Timestamp;
import java.util.Date;

public class NoticesInfo {
	private int noticeId;
	private String noticeName;
	private String noticeContext;
	private Timestamp  noticeDate;
	public int getNoticesId() {
		return noticeId;
	}
	public void setNoticesId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeName() {
		return noticeName;
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	public String getNoticeContext() {
		return noticeContext;
	}
	public void setNoticeContext(String noticeContext) {
		this.noticeContext = noticeContext;
	}
	public Date getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(Timestamp noticeDate) {
		this.noticeDate = noticeDate;
	}
	

}
