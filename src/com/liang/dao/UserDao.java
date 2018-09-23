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
				// ע���û�������Ϣ
				int _id = rs.getInt(1);
				String userName = rs.getString(2);
				String password = rs.getString(3);
				int isAdmin = rs.getInt(4);// Ĭ�ϲ��ǹ���Ա
				String telephone = rs.getString(5);
				String email = rs.getString(6);

				// �����û�ѡ����Ϣ
				String realName = rs.getString(7);
				String IDNumber = rs.getString(8);
				int sex = rs.getInt(9);// Ĭ������
				Date data = rs.getDate(10);
				String nation = rs.getString(11);
				String degreeOfEdecation = rs.getString(12);
				String contactTelephone = rs.getString(13);
				String contactAddress = rs.getString(14);
				String contactEmail = rs.getString(15);
				int pastCode = rs.getInt(16);// ��λ��
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

				// ע���û�������Ϣ
				int _id = rs.getInt(1);
				String userName = rs.getString(2);
				String password = rs.getString(3);
				int isAdmin = rs.getInt(4);// Ĭ�ϲ��ǹ���Ա
				String telephone = rs.getString(5);
				String email = rs.getString(6);

				// �����û�ѡ����Ϣ
				String realName = rs.getString(7);
				String IDNumber = rs.getString(8);
				int sex = rs.getInt(9);// Ĭ������
				Date data = rs.getDate(10);
				String nation = rs.getString(11);
				String degreeOfEdecation = rs.getString(12);
				String contactTelephone = rs.getString(13);
				String contactAddress = rs.getString(14);
				String contactEmail = rs.getString(15);
				int pastCode = rs.getInt(16);// ��λ��
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
	 * ��idΪ������ѯ
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
	 * ���û���Ϊ������ѯ
	 * 
	 * @param username
	 * @return
	 */
	public UserInfo getUserByName(String username) {
		sql = "select * from user where user_name='" + username + "'";
		return singleUser(sql);
	}

	/**
	 * ��ѯȫ���û�
	 * 
	 * @return
	 */
	public List<UserInfo> getUser() {
		sql = "select * from user";
		return MultiUserInfo(sql);
	}

	/**
	 * ����һ��Ԫ��
	 * 
	 * @return
	 */
	public boolean insert(UserInfo user) {
		if (user == null) {
			return false;
		}
		// �û����Լ���ռ�ã����ܴ���
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
			System.out.println("UserInfo.insert(UserInfo user):����ʧ��");
		} finally {
			JDBCCloss.close(conn, stmt, pstmt, rs);
		}
		return false;
	}

	/**
	 * ��������
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
		// �����ļ������������ͨ���޸��ļ�����ʵ�֣���ʱ��������
		if (userInfo == null) {
			return false;
		}
		// �����û�ѡ����Ϣ
		int id = userInfo.getId();

		String realName = userInfo.getRealName();
		String IDNumber = userInfo.getINNumber();
		int sex = userInfo.getSex();// Ĭ������
		Date date = userInfo.getData();
		Timestamp birthday = new Timestamp(date.getTime());//ת�������ݿ��datetime ��ʽ
		String nation = userInfo.getNation();
		String degreeOfEducation = userInfo.getDegreeOfEdecation();
		String contactTelephone = userInfo.getContactTelephone();
		String contactAddress = userInfo.getContactAddress();
		int pastCode = userInfo.getPastCode();// ��λ��
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
	 * ������
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
				System.out.println("��ѯ�ɹ�id=" + i + " userName" + user.getUserName());
			} else {
				System.out.println("��ѯʧ��");
			}
		}
		System.out.println("getUser(id)///////////////////////////");
		System.out.println("getUserByName(name)-------------------");
		UserInfo user = userDao.getUserByName("18827669410");
		if (user != null) {
			System.out.println("��ѯ�ɹ�id=" + user.getId() + " userName" + user.getUserName());
		} else {
			System.out.println("��ѯʧ��");
		}
		System.out.println("getUserByName(name)//////////////////");

		System.out.println("insert(userInfo)-------------------");
		boolean flag = userDao.insert(new UserInfo("13799999999", "123", 0, "09876543211", "ahsdf@qq.com"));
		if (flag != false) {
			System.out.println("����ɹ� ");
		} else {
			System.out.println("����ʧ��");
		}
		System.out.println("insert(userInfo)//////////////////");

		System.out.println("updatePassword-------------------");
		user = userDao.getUserById(1);
		flag = userDao.updatePassword(user, "1");
		if (flag != false) {
			System.out.println("���³ɹ� ");
		} else {
			System.out.println("����ʧ��");
		}
		System.out.println("updatePassword//////////////////");
	}
}
