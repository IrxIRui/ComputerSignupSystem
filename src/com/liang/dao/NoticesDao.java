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
	/**��ѯ���õ�ģ��
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

	/**��ѯ����֪ͨ
	 * @return
	 */
	public List<NoticesInfo> getNotices() {
		sql = "select * from notices order by notice_date DESC";//��ʱ�併������
		return MultiNotice(sql);
	}

	/**��ѯby id
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
	 * ����ģ����ѯ
	 * @param partName
	 * @return
	 */
	public List<NoticesInfo> getNoticesByPartName(String partName) {
		//��ʱ�併������
		sql = "select * from notices where notice_Name like '%" + partName + "%'" +"order by notice_date DESC";
		return MultiNotice(sql);
	}
	
	/**
	 * ��ѯһҳnotices����һ���������ҳ���ڶ���������ʶҳ�Ĵ�С
	 * @param partName
	 * @return
	 */
	public List<NoticesInfo> getNoticesByPage(int pageNum,int pageSize) {
		//����ӵڼ�����ʼȡ���ݣ�
		int count=(pageNum-1)*pageSize;
		//��ʱ�併������
		sql="select * from (select * from notices order by notice_date desc) tep where notice_id limit " + count + ","+pageSize;
		return MultiNotice(sql);
	}
	
	/**��ѯ��¼��
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
	 * ������
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
		// ��ǿfor ����list
		for (NoticesInfo tep : test) {
			System.out.print(tep.getNoticeName());
			System.out.print(tep.getNoticeContext());
			System.out.print(tep.getNoticesId());
			System.out.println(tep.getNoticeDate());
		}
	}
}
