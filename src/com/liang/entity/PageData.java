package com.liang.entity;

public class PageData {
	private int pageSize = 5; // ҳ���СĬ��Ϊ10
	private int currentPage;// ��ǰҳ��
	private int maxCount;// ����¼��
	private int sumPage;// ���ҳ��

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public int getSumPage() {
		if((maxCount%pageSize)==0){
			sumPage=maxCount/pageSize;
		}else{
			sumPage=(maxCount/pageSize)+1;
		}
		return sumPage;
	}

}
