package com.liang.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.liang.dao.DownloadDao;
import com.liang.entity.Download;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {
	DownloadDao downloadDao = new DownloadDao();
	List<Download> downloadsList = null;

	ActionContext actionContext = ActionContext.getContext();
	Map<String, Object> se = actionContext.getSession();

	public String execute() {
		downloadsList = downloadDao.getDownloads();
		if (downloadsList == null) {
			return "downloadError";
		}
		se.put("downloadsList", downloadsList);
		return "downloadSuccess";
	}
	public String put(){
		downloadsList = downloadDao.getDownloads();
		if (downloadsList == null) {
			return "downloadError";
		}
		se.put("downloadsList", downloadsList);
		return "downloadSuccess";
	}
}
