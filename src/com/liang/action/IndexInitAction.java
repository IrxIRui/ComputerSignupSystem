package com.liang.action;

import java.util.List;
import java.util.Map;

import com.liang.dao.NoticesDao;
import com.liang.entity.NoticesInfo;
import com.liang.entity.PageData;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexInitAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ActionContext actionContext = ActionContext.getContext();
	Map<String, Object> session=actionContext.getSession();
	PageData pageData = null;
	NoticesDao noticesDao = null;
	
	public String execute() {
		
		//��һ��ˢ�� notices==null�������ȣ���˵���������ݣ������Զ���ȡ
		if(session.get("notices")!=null){
			return "success";
		}
		PageData pageData = new PageData();
		noticesDao = new NoticesDao();
		
		// ��ѯ��һҳ,1��ʾ��һҳ
		List<NoticesInfo> notices = noticesDao.getNoticesByPage(1, pageData.getPageSize());
		//���÷�ҳ��Ϣ
		pageData.setCurrentPage(1);
		pageData.setMaxCount(noticesDao.getNoticesCount());
		pageData.getPageSize();//Ĭ��pagesize
		
		if(session!=null){
			session.remove("notices");
			session.put("notices", notices);
			session.remove("pageData");
			session.put("pageData", pageData);
			session.remove("partName");
			session.put("partName", "");
		}
		return "success";
	}
	
	/**����
	 * @param args
	 */
/*	public static void main(String[] args){
		IndexInitAction indexInitAction=new IndexInitAction();
		indexInitAction.execute();
		if(indexInitAction.actionContext!=null){
			System.out.println("actionContext is not null");
			indexInitAction.session = indexInitAction.actionContext.getSession();
		}
		else{
			System.out.println("actionContext is null");
		}
	}*/

}
