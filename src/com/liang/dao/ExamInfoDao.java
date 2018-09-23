package com.liang.dao;

import com.liang.entity.ExamInfo;
import com.liang.util.DBUtil;

public class ExamInfoDao extends BaseDao{
	
	private ExamInfo examInfo=null;
	
	public int  getExmaIdByExmaName(String examName){
		String sql="select exam_id from exam where exam_name='"+examName+"'";
		int examId=-1;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				examId=rs.getInt(1);
			}
		} catch (Exception e) {
				// TODO: handle exception
			}
		return examId;
	}
	public String getExamNameById(int id){
		return (getExmaInfoByExmaId(id)).getExam_name();
	}
	
	public ExamInfo getExmaInfoByExmaId(int id){
		String sql="select * from exam where exam_id='"+id+"'";
		examInfo=new ExamInfo();
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				examInfo.setExam_id(rs.getInt(1));
				examInfo.setExam_name(rs.getString(2));
				examInfo.setExmam_Grade(rs.getString(3));
				examInfo.setAccountKey(rs.getInt(4));
				examInfo.setLengthOfExam(rs.getInt(5));
				examInfo.setStartTime(rs.getTimestamp(6));
			}
		} catch (Exception e) {
				// TODO: handle exception
			System.out.println(e);
			}
		return examInfo;
	}
	
/*	
	*//**测试
	 * @param args
	 *//*
	public static void main(String args[]){
		ExamInfoDao examInfoDao=new ExamInfoDao();
		
		System.out.println(examInfoDao.getExmaIdByExmaName("计算机基础及Photoshop应用"));
		System.out.println(examInfoDao.getExmaInfoByExmaId(2).getAccountKey());
	}*/
}
