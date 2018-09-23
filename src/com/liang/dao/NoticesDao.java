package com.liang.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liang.entity.NoticesInfo;
import com.liang.util.*;

public class NoticesDao extends BaseDao {

	private String sql ="";
	private List<NoticesInfo> noticeList=null;
	private NoticesInfo notice;
	/**查询公用的模块
	 * @return
	 */
	private List<NoticesInfo> MultiNotice(String sql) {
		noticeList = new ArrayList<NoticesInfo>();
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				notice = new NoticesInfo();
				notice.setNoticesId(rs.getInt(1));
				notice.setNoticeName(rs.getString(2));
				notice.setNoticeContext(rs.getString(3));
				notice.setNoticeDate(rs.getTimestamp(4));
				noticeList.add(notice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCCloss.close(conn, stmt, pstmt, rs);
		return noticeList;
	}

	/**查询所有通知
	 * @return
	 */
	public List<NoticesInfo> getNotices() {
		sql = "select * from notices order by notice_date DESC";//按时间降序排列
		return MultiNotice(sql);
	}

	/**查询by id
	 * @param id
	 * @return
	 */
	public NoticesInfo getNoticeById(int id) {
		sql = "select * from notices where notice_id=" + id;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				notice=new NoticesInfo();
				notice.setNoticesId(rs.getInt(1));
				notice.setNoticeName(rs.getString(2));
				notice.setNoticeContext(rs.getString(3));
				notice.setNoticeDate(rs.getTimestamp(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCCloss.close(conn, stmt, pstmt, rs);
		return notice;
	}

	/**
	 * 名字模糊查询
	 * @param partName
	 * @return
	 */
	public List<NoticesInfo> getNoticesByPartName(String partName) {
		//按时间降序排列
		sql = "select * from notices where notice_Name like '%" + partName + "%'" +"order by notice_date DESC";
		return MultiNotice(sql);
	}
	
	/**
	 * 查询一页notices，第一个参数标记页，第二个参数标识页的大小
	 * @param partName
	 * @return
	 */
	public List<NoticesInfo> getNoticesByPage(int pageNum,int pageSize) {
		//计算从第几条开始取数据，
		int count=(pageNum-1)*pageSize;
		//按时间降序排列
		sql="select * from (select * from notices order by notice_date desc) tep where notice_id limit " + count + ","+pageSize;
		return MultiNotice(sql);
	}
	
	/**查询记录数
	 * @return
	 */
	public int getNoticesCount(){
		noticeList=getNotices();
		if(noticeList!=null){
			return noticeList.size();
		}
		return 0;
	}
	
	public String getNoticePath(int id){
		String path=null;
		notice=getNoticeById(id);
		if(notice!=null){
			path=notice.getNoticeContext();
		}
		return path;
	}
	


	/**
	 * 测试类
	 */
	public static void main(String[] srgs) {
		NoticesDao noticeDao = new NoticesDao();
		System.out.println("test-----------------------------getNoticeById()");
		NoticesInfo te = noticeDao.getNoticeById(2);
		System.out.println(te.getNoticeName() + "   \n  " + te);

		List<NoticesInfo> test = null;		
	 	test = noticeDao.getNotices();
	 	testshow(test);
		
		test = noticeDao.getNoticesByPartName("2016");
		System.out.println("2016---------------");
		testshow(test);
		test = noticeDao.getNoticesByPage(2, 3);
		System.out.println("2--------------3");
		testshow(test);
		
		System.out.println("test-----------------------------getNoticePath()");
	}

	private static void testshow(List<NoticesInfo> test) {
		// 增强for 迭代list
		for (NoticesInfo tep : test) {
			System.out.print(tep.getNoticeName());
			System.out.print(tep.getNoticeContext());
			System.out.print(tep.getNoticesId());
			System.out.println(tep.getNoticeDate());
		}
	}
}
