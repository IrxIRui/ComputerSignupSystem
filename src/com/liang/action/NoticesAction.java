package com.liang.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.liang.dao.NoticesDao;
import com.liang.entity.NoticesInfo;
import com.liang.entity.PageData;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class NoticesAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String partName=null;
	private String page;
	

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}
	

	ActionContext actionContext=ActionContext.getContext();
	Map<String, Object> se=actionContext.getSession();
	NoticesDao noticesDao=null;
	List<NoticesInfo> notices=null;
	PageData pageData=null;
	
	public String execute(){
		return "success";   
	}
	
	public String ByPartName(){//searchNoticeByPartName
		pageData=(PageData)se.get("pageData");
		/*如果直接从地址栏输入http://localhost/ComputerSignupSystem/searchNoticesByPartName访问ByPartName Action
		则返回首页
		否则说明从index.jsp页面跳转过来的，执行查询操作*/
		if(pageData==null||partName==null){
			return "success";
		}
		noticesDao=new NoticesDao();
		//去前后空格
		partName=partName.trim();
		if(partName.length()<=0){
			notices=noticesDao.getNoticesByPage(1, new PageData().getPageSize());
			pageData.setMaxCount(noticesDao.getNoticesCount());
		}else{
			notices=noticesDao.getNoticesByPartName(partName);
			pageData.setMaxCount(notices.size());//设置查询的记录数
		}
		
		//查询完后设置pageData
		pageData.setCurrentPage(1);//每次模糊查询后设为第一页
		
		se.remove("notices");
		se.put("notices", notices);
		se.remove("pageData");
		se.put("pageData", pageData);
		se.remove("partName");
		se.put("partName", partName);
		return "success";
		
	}
	
	public String ByPageAndPartName(){
		//获取request
		HttpServletRequest request = ServletActionContext.getRequest();
		String page=request.getParameter("page");
		pageData=(PageData)se.get("pageData");
		partName=(String)se.get("partName");
		
		/*如果直接从地址栏输入http://localhost/ComputerSignupSystem/searchNoticesByPageAndPartName?page=2访问ByPageAndPartName Action
		则返回首页
		否则说明从index.jsp页面跳转过来的，执行查询操作*/
		if(pageData==null){
			return "success";
		}
		
		noticesDao=new NoticesDao(); 
		List<NoticesInfo> newNotices=new ArrayList<NoticesInfo>();
		int pageNum=pageData.getCurrentPage();
		int pageSize=pageData.getPageSize();
		
		try {
			pageNum = Integer.parseInt(page);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("----------------------页码获取失败----------------------");
		}
		
		//不带搜索的下一页
		if(partName==null || partName.length()<=0){
			notices=noticesDao.getNoticesByPage(pageNum, pageSize);
		}else{//带partName的下一页搜索
			notices=noticesDao.getNoticesByPartName(partName);
			for(int i=(pageNum-1)*pageSize;i<pageNum*pageSize;i++){
				if(i<notices.size()){
					newNotices.add(notices.get(i));
				}
			}
			notices=newNotices;
		}
		//设置当前页，newNotices和pageData存入session,返回
		
		pageData.setCurrentPage(pageNum);
		
		se.remove("notices");
		se.put("notices", notices);
		se.remove("pageData");
		se.put("pageData", pageData);
		
		return "success";
	}
	
	public String Detail(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String tempId=request.getParameter("id");
		int id=Integer.parseInt(tempId);
		noticesDao=new NoticesDao();		
		String path=noticesDao.getNoticePath(id);
		
		//计算文件在磁盘上的路径，读取文件
		
		return "noticesdetail";
	}
}
