package com.liang.entity;

import java.sql.Timestamp;

public class Download {
	private int DownloadsId;
	private String DownloadsName;
	private String DownloadsPath;
	private Timestamp  DownloadsDate;
	public int getDownloadsId() {
		return DownloadsId;
	}
	public void setDownloadsId(int downloadsId) {
		DownloadsId = downloadsId;
	}
	public String getDownloadsName() {
		return DownloadsName;
	}
	public void setDownloadsName(String downloadsName) {
		DownloadsName = downloadsName;
	}
	public String getDownloadsPath() {
		return DownloadsPath;
	}
	public void setDownloadsPath(String downloadsPath) {
		DownloadsPath = downloadsPath;
	}
	public Timestamp getDownloadsDate() {
		return DownloadsDate;
	}
	public void setDownloadsDate(Timestamp downloadsDate) {
		DownloadsDate = downloadsDate;
	}
	

}
