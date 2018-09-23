package com.liang.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.liang.entity.UserInfo;
import com.liang.util.DBUtil;
import com.liang.util.JDBCCloss;;

public class UserDao extends BaseDao {
	private String sql = null;
	private List<UserInfo> users = null;
	private UserInfo user = null;

	private List<UserInfo> MultiUserInfo(String sql) {
		users = new ArrayList<UserInfo>();
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// 注册用户基本信息
				int _id = rs.getInt(1);
				String userName = rs.getString(2);
				String password = rs.getString(3);
				int isAdmin = rs.getInt(4);// 默认不是管理员
				String telephone = rs.getString(5);
				String email = rs.getString(6);

				// 报名用户选填信息
				String realName = rs.getString(7);
				String IDNumber = rs.getString(8);
				int sex = rs.getInt(9);// 默认男性
				Date data = rs.getDate(10);
				String nation = rs.getString(11);
				String degreeOfEdecation = rs.getString(12);
				String contactTelephone = rs.getString(13);
				String contactAddress = rs.getString(14);
				String contactEmail = rs.getString(15);
				int pastCode = rs.getInt(16);// 四位数
				String studentID = rs.getString(17);
				String majorGrade = rs.getString(18);
				user = new UserInfo(_id, userName, password, isAdmin, telephone, email, realName, IDNumber, sex, data,
						nation, degreeOfEdecation, contactTelephone, contactAddress, contactEmail, pastCode, studentID,
						majorGrade);
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCCloss.close(conn, stmt, pstmt, rs);
		return users;
	}

	private UserInfo singleUser(String sql) {
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				// 注册用户基本信息
				int _id = rs.getInt(1);
				String userName = rs.getString(2);
				String password = rs.getString(3);
				int isAdmin = rs.getInt(4);// 默认不是管理员
				String telephone = rs.getString(5);
				String email = rs.getString(6);

				// 报名用户选填信息
				String realName = rs.getString(7);
				String IDNumber = rs.getString(8);
				int sex = rs.getInt(9);// 默认男性
				Date data = rs.getDate(10);
				String nation = rs.getString(11);
				String degreeOfEdecation = rs.getString(12);
				String contactTelephone = rs.getString(13);
				String contactAddress = rs.getString(14);
				String contactEmail = rs.getString(15);
				int pastCode = rs.getInt(16);// 四位数
				String studentID = rs.getString(17);
				String majorGrade = rs.getString(18);
				user = new UserInfo(_id, userName, password, isAdmin, telephone, email, realName, IDNumber, sex, data,
						nation, degreeOfEdecation, contactTelephone, contactAddress, contactEmail, pastCode, studentID,
						majorGrade);
				JDBCCloss.close(conn, stmt, pstmt, rs);
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCCloss.close(conn, stmt, pstmt, rs);
		}
		return null;
	}

	/**
	 * 以id为索引查询
	 * 
	 * @param id
	 * @return
	 */
	public UserInfo getUserById(int id) {
		sql = "select * from user where user_id=" + id;
		return singleUser(sql);
	}
	
	public String getUserRealNameById(int id){
		return (getUserById(id)).getRealName();
	}

	/**
	 * 以用户名为索引查询
	 * 
	 * @param username
	 * @return
	 */
	public UserInfo getUserByName(String username) {
		sql = "select * from user where user_name='" + username + "'";
		return singleUser(sql);
	}

	/**
	 * 查询全部用户
	 * 
	 * @return
	 */
	public List<UserInfo> getUser() {
		sql = "select * from user";
		return MultiUserInfo(sql);
	}

	/**
	 * 插入一个元素
	 * 
	 * @return
	 */
	public boolean insert(UserInfo user) {
		if (user == null) {
			return false;
		}
		// 用户名以及被占用，不能存入
		if (getUserByName(user.getUserName()) != null) {
			return false;
		}

		String sql = "insert into user(user_name,password,isAdmin,telephone,email) values(?,?,?,?,?)";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getIsAdmin());
			pstmt.setString(4, user.getTelephone());
			pstmt.setString(5, user.getEmail());
			pstmt.executeUpdate();
			JDBCCloss.close(conn, stmt, pstmt, rs);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("UserInfo.insert(UserInfo user):插入失败");
		} finally {
			JDBCCloss.close(conn, stmt, pstmt, rs);
		}
		return false;
	}

	/**
	 * 更新密码
	 * 
	 * @param user
	 * @param password
	 * @return
	 */
	public boolean updatePassword(UserInfo user, String password) {
		if (user == null) {
			return false;
		}
		String sql = "update user set password='" + password + "'where user_id=" + user.getId();
		// update user set password='123' where user_id=1;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			JDBCCloss.close(conn, stmt, pstmt, rs);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCCloss.close(conn, stmt, pstmt, rs);
		}
		return false;
	}

	public boolean uploadSignInfo(UserInfo userInfo) {
		// 对于文件重名问题可以通过修改文件名来实现，暂时不做处理
		if (userInfo == null) {
			return false;
		}
		// 报名用户选填信息
		int id = userInfo.getId();

		String realName = userInfo.getRealName();
		String IDNumber = userInfo.getINNumber();
		int sex = userInfo.getSex();// 默认男性
		Date date = userInfo.getData();
		Timestamp birthday = new Timestamp(date.getTime());//转换成数据库的datetime 格式
		String nation = userInfo.getNation();
		String degreeOfEducation = userInfo.getDegreeOfEdecation();
		String contactTelephone = userInfo.getContactTelephone();
		String contactAddress = userInfo.getContactAddress();
		int pastCode = userInfo.getPastCode();// 四位数
		String majorGrade = userInfo.getMajorGrade();

		// String sql = "update user set password='" + password + "'where
		// user_id=" + user.getId();
		String sql = "update user set realName='" + realName + "',IDNumber='" + IDNumber + "',sex=" + sex
				+ " ,birthday='" + birthday + "',nation='" + nation + "',degreeOfEducation='" + degreeOfEducation
				+ "',contactTelephone='" + contactTelephone + "',contactAddress='" + contactAddress + "',pastCode="
				+ pastCode + ",majorGrade='" + majorGrade + "' where user_id=" + id;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			JDBCCloss.close(conn, stmt, pstmt, rs);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCCloss.close(conn, stmt, pstmt, rs);
		}

		return false;
	}

	/**
	 * 测试类
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		UserDao userDao = new UserDao();
		List<UserInfo> list = null;

		System.out.println("getUser(id)-------------------------");
		for (int i = 0; i < 5; i++) {
			UserInfo user = userDao.getUserById(i);
			if (user != null) {
				System.out.println("查询成功id=" + i + " userName" + user.getUserName());
			} else {
				System.out.println("查询失败");
			}
		}
		System.out.println("getUser(id)///////////////////////////");
		System.out.println("getUserByName(name)-------------------");
		UserInfo user = userDao.getUserByName("18827669410");
		if (user != null) {
			System.out.println("查询成功id=" + user.getId() + " userName" + user.getUserName());
		} else {
			System.out.println("查询失败");
		}
		System.out.println("getUserByName(name)//////////////////");

		System.out.println("insert(userInfo)-------------------");
		boolean flag = userDao.insert(new UserInfo("13799999999", "123", 0, "09876543211", "ahsdf@qq.com"));
		if (flag != false) {
			System.out.println("插入成功 ");
		} else {
			System.out.println("插入失败");
		}
		System.out.println("insert(userInfo)//////////////////");

		System.out.println("updatePassword-------------------");
		user = userDao.getUserById(1);
		flag = userDao.updatePassword(user, "1");
		if (flag != false) {
			System.out.println("更新成功 ");
		} else {
			System.out.println("更新失败");
		}
		System.out.println("updatePassword//////////////////");
	}
}
