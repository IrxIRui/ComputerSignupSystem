package com.liang.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liang.entity.Sign;
import com.liang.util.DBUtil;
import com.liang.util.JDBCCloss;

public class SignDao extends BaseDao {
	
	private List<Sign> signs=null;
	private Sign sign=null;

	public boolean addSign(Sign sign) {
		if (sign == null) {
			return false;
		}

		String sql = "insert into sign(examRegistrationNumbers,user_id,exam_id,sign_date,date,exam_address,pay,state,score) values(?,?,?,?,?,?,?,?,?)";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sign.getExamRegistrationNumbers());
			pstmt.setInt(2, sign.getUser_id());
			pstmt.setInt(3, sign.getExam_id());
			pstmt.setTimestamp(4, sign.getSign_date());
			pstmt.setTimestamp(5, sign.getDate());
			pstmt.setString(6, sign.getExam_address());
			pstmt.setDouble(7, sign.getPay());
			pstmt.setInt(8, sign.getState());
			pstmt.setDouble(9, sign.getScore());
			pstmt.executeUpdate();
			JDBCCloss.close(conn, stmt, pstmt, rs);
			System.out.println("œÚ ˝æ›ø‚ÃÌº”≥…π¶");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SignInfo:≤Â»Î ß∞‹");
		} finally {
			JDBCCloss.close(conn, stmt, pstmt, rs);
		}
		return false;
	}
	
	public List<Sign> getSignByUserId(int id){
		String sql="select * from sign where user_id="+id;
		signs=new ArrayList<Sign>();
		
		try{
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				sign=new Sign();
				sign.setSign_id(rs.getInt(1));
				sign.setExamRegistrationNumbers(rs.getString(2));
				sign.setUser_id(rs.getInt(3));
				sign.setExam_id(rs.getInt(4));
				sign.setSign_date(rs.getTimestamp(5));
				sign.setDate(rs.getTimestamp(6));
				sign.setExam_address(rs.getString(7));
				sign.setPay(rs.getDouble(8));
				sign.setState(rs.getInt(9));
				sign.setScore(rs.getDouble(10));
				signs.add(sign);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JDBCCloss.close(conn, stmt, pstmt, rs);
		return signs;
	}
	
	public Sign getSignByExamRegistrationNumbers(String ExamRegistrationNumbers){
		String sql="select * from sign where examRegistrationNumbers='"+ExamRegistrationNumbers+"'";
		try{
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				sign=new Sign();
				sign.setSign_id(rs.getInt(1));
				sign.setExamRegistrationNumbers(rs.getString(2));
				sign.setUser_id(rs.getInt(3));
				sign.setExam_id(rs.getInt(4));
				sign.setSign_date(rs.getTimestamp(5));
				sign.setDate(rs.getTimestamp(6));
				sign.setExam_address(rs.getString(7));
				sign.setPay(rs.getDouble(8));
				sign.setState(rs.getInt(9));
				sign.setScore(rs.getDouble(10));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JDBCCloss.close(conn, stmt, pstmt, rs);
		return sign;
	}
	
	public boolean deleteSign(String examRegistrationNumbers){
		String sql="delete from sign where examRegistrationNumbers='"+examRegistrationNumbers+"'";
		Sign sign=getSignByExamRegistrationNumbers(examRegistrationNumbers);
		if(sign==null){
			return false;
		}
		try{
			conn=DBUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		JDBCCloss.close(conn, stmt, pstmt, rs);
		return true;
	}
	
	public String paySign(int userId){
		String sql="update sign set pay=1 where user_id="+userId;
		try{
			conn=DBUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		JDBCCloss.close(conn, stmt, pstmt, rs);
		return "success";
	}
	
	/**	≤‚ ‘
	 * @param args
	 */
	public static void main(String args[]){
		SignDao signDao=new SignDao();
		System.out.println("≤‚ ‘delectSign:"+signDao.deleteSign("188464104"));
		System.out.println("≤‚ ‘psyDign:"+signDao.paySign(1));
	}
}
